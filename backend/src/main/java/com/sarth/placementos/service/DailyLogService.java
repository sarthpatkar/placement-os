package com.sarth.placementos.service;

import com.sarth.placementos.dto.DailyLogResponse;
import com.sarth.placementos.entity.DailyLog;
import com.sarth.placementos.entity.User;
import com.sarth.placementos.exception.ResourceNotFoundException;
import com.sarth.placementos.repository.DailyLogRepository;
import com.sarth.placementos.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class DailyLogService {

    private final DailyLogRepository dailyLogRepository;
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public DailyLogResponse getToday(Long userId) {
        requireUser(userId);
        LocalDate today = LocalDate.now();
        return dailyLogRepository.findByUserIdAndDate(userId, today)
                .map(DailyLogResponse::from)
                .orElse(DailyLogResponse.empty(userId, today));
    }

    @Transactional
    public void addFocusMinutes(Long userId, LocalDate date, int minutes) {
        DailyLog log = getOrCreate(userId, date);
        log.setTotalFocusMinutes(log.getTotalFocusMinutes() + minutes);
        dailyLogRepository.save(log);
    }

    @Transactional
    public void incrementCompletedTasks(Long userId, LocalDate date) {
        DailyLog log = getOrCreate(userId, date);
        log.setCompletedTasks(log.getCompletedTasks() + 1);
        dailyLogRepository.save(log);
    }

    private DailyLog getOrCreate(Long userId, LocalDate date) {
        return dailyLogRepository.findByUserIdAndDate(userId, date)
                .orElseGet(() -> {
                    User user = requireUser(userId);
                    return dailyLogRepository.save(DailyLog.builder()
                            .user(user)
                            .date(date)
                            .totalFocusMinutes(0)
                            .completedTasks(0)
                            .build());
                });
    }

    private User requireUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found: " + userId));
    }
}
