package com.sarth.placementos.service;

import com.sarth.placementos.dto.StudySessionRequest;
import com.sarth.placementos.dto.StudySessionResponse;
import com.sarth.placementos.entity.StudySession;
import com.sarth.placementos.entity.Task;
import com.sarth.placementos.entity.User;
import com.sarth.placementos.exception.ResourceNotFoundException;
import com.sarth.placementos.repository.StudySessionRepository;
import com.sarth.placementos.repository.TaskRepository;
import com.sarth.placementos.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudySessionService {

    private final StudySessionRepository studySessionRepository;
    private final UserRepository userRepository;
    private final TaskRepository taskRepository;
    private final DailyLogService dailyLogService;

    @Transactional
    public StudySessionResponse create(Long userId, StudySessionRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found: " + userId));

        if (!request.getEndTime().isAfter(request.getStartTime())) {
            throw new IllegalArgumentException("endTime must be after startTime");
        }

        Task task = null;
        if (request.getTaskId() != null) {
            task = taskRepository.findById(request.getTaskId())
                    .orElseThrow(() -> new ResourceNotFoundException("Task not found: " + request.getTaskId()));
            Long taskOwnerId = task.getModule().getTrack().getUser().getId();
            if (!taskOwnerId.equals(userId)) {
                throw new ResourceNotFoundException("Task not found: " + request.getTaskId());
            }
        }

        int durationMinutes = (int) ChronoUnit.MINUTES.between(request.getStartTime(), request.getEndTime());
        if (durationMinutes <= 0) {
            durationMinutes = 1;
        }

        StudySession session = StudySession.builder()
                .user(user)
                .task(task)
                .title(request.getTitle())
                .description(request.getDescription())
                .startTime(request.getStartTime())
                .endTime(request.getEndTime())
                .durationMinutes(durationMinutes)
                .build();

        StudySession saved = studySessionRepository.save(session);
        LocalDate sessionDate = saved.getStartTime().toLocalDate();
        dailyLogService.addFocusMinutes(userId, sessionDate, durationMinutes);

        return StudySessionResponse.from(saved);
    }

    @Transactional(readOnly = true)
    public List<StudySessionResponse> getAllByUserId(Long userId) {
        requireUser(userId);
        return studySessionRepository.findByUserId(userId).stream()
                .map(StudySessionResponse::from)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<StudySessionResponse> getTodayByUserId(Long userId) {
        requireUser(userId);
        LocalDate today = LocalDate.now();
        LocalDateTime dayStart = today.atStartOfDay();
        LocalDateTime dayEnd = today.plusDays(1).atStartOfDay();
        return studySessionRepository.findByUserIdAndDate(userId, dayStart, dayEnd).stream()
                .map(StudySessionResponse::from)
                .toList();
    }

    private void requireUser(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("User not found: " + userId);
        }
    }
}
