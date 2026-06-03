package com.sarth.placementos.service;

import com.sarth.placementos.dto.*;
import com.sarth.placementos.entity.DailyLog;
import com.sarth.placementos.entity.Track;
import com.sarth.placementos.entity.Task;
import com.sarth.placementos.entity.Module;
import com.sarth.placementos.enums.Status;
import com.sarth.placementos.repository.*;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class AnalyticsService {

    private final DailyLogRepository dailyLogRepository;
    private final TrackRepository trackRepository;
    private final ModuleRepository moduleRepository;
    private final TaskRepository taskRepository;


    public UserAnalyticsResponse getAnalytics(Long userId) {

        List<DailyLog> logs =
                dailyLogRepository.findByUserIdOrderByDateAsc(userId);


        int totalFocus =
                logs.stream()
                        .mapToInt(DailyLog::getTotalFocusMinutes)
                        .sum();


        List<TrackAnalyticsResponse> trackResponses =
                new ArrayList<>();

        List<WeakAreaResponse> weakAreas =
                new ArrayList<>();


        int completedTasks = 0;


        List<Track> tracks =
                trackRepository.findByUserId(userId);


        for (Track track : tracks) {


            int trackTotal = 0;
            int trackCompleted = 0;


            List<ModuleAnalyticsResponse> moduleResponses =
                    new ArrayList<>();


            for (Module module :
                    moduleRepository.findByTrackId(track.getId())) {


                List<Task> tasks =
                        taskRepository.findByModuleId(module.getId());


                int total = tasks.size();


                int completed =
                        (int) tasks.stream()
                                .filter(t ->
                                        t.getStatus()
                                                == Status.COMPLETED)
                                .count();


                int progress =
                        total == 0
                                ? 0
                                : completed * 100 / total;


                if (progress < 40) {

                    weakAreas.add(

                            WeakAreaResponse.builder()
                                    .name(module.getTitle())
                                    .type("MODULE")
                                    .reason("Low completion progress")
                                    .build()
                    );
                }


                trackTotal += total;
                trackCompleted += completed;


                moduleResponses.add(

                        ModuleAnalyticsResponse.builder()
                                .moduleId(module.getId())
                                .moduleName(module.getTitle())
                                .totalTasks(total)
                                .completedTasks(completed)
                                .progressPercentage(progress)
                                .build()
                );
            }


            completedTasks += trackCompleted;


            int trackProgress =
                    trackTotal == 0
                            ? 0
                            : trackCompleted * 100 / trackTotal;


            trackResponses.add(

                    TrackAnalyticsResponse.builder()
                            .trackId(track.getId())
                            .trackName(track.getName())
                            .trackType(track.getType())
                            .totalTasks(trackTotal)
                            .completedTasks(trackCompleted)
                            .progressPercentage(trackProgress)
                            .totalFocusMinutes(totalFocus)
                            .modules(moduleResponses)
                            .build()
            );
        }


        return UserAnalyticsResponse.builder()
                .totalFocusMinutes(totalFocus)
                .totalCompletedTasks(completedTasks)
                .currentStreak(calculateCurrentStreak(logs))
                .longestStreak(calculateLongestStreak(logs))
                .tracks(trackResponses)
                .weakAreas(weakAreas)
                .build();
    }


    private int calculateCurrentStreak(List<DailyLog> logs) {

        int streak = 0;

        LocalDate expected = LocalDate.now();


        for (int i = logs.size() - 1; i >= 0; i--) {


            DailyLog log = logs.get(i);


            if (log.getDate().equals(expected)
                    && log.getTotalFocusMinutes() > 0) {

                streak++;

                expected =
                        expected.minusDays(1);
            }
        }


        return streak;
    }


    private int calculateLongestStreak(List<DailyLog> logs) {

        int best = 0;

        int current = 0;


        for (DailyLog log : logs) {


            if (log.getTotalFocusMinutes() > 0) {

                current++;

                best =
                        Math.max(best, current);

            } else {

                current = 0;
            }
        }


        return best;
    }
}