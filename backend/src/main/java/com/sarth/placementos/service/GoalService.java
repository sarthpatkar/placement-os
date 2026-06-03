package com.sarth.placementos.service;


import com.sarth.placementos.dto.*;
import com.sarth.placementos.entity.Goal;
import com.sarth.placementos.entity.User;
import com.sarth.placementos.exception.ResourceNotFoundException;
import com.sarth.placementos.repository.GoalRepository;
import com.sarth.placementos.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class GoalService {


private final GoalRepository goalRepository;

private final UserRepository userRepository;



public GoalResponse create(
        Long userId,
        GoalRequest request
){


User user =
 userRepository.findById(userId)
.orElseThrow(
()->new ResourceNotFoundException("User not found")
);


Goal goal =
Goal.builder()
.title(request.getTitle())
.description(request.getDescription())
.type(request.getType())
.targetDate(request.getTargetDate())
.user(user)
.build();


return GoalResponse.from(
goalRepository.save(goal)
);

}



public List<GoalResponse> getGoals(
        Long userId
){

return goalRepository
.findByUserId(userId)
.stream()
.map(GoalResponse::from)
.toList();

}


}