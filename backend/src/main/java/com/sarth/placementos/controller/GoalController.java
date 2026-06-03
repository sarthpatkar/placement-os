package com.sarth.placementos.controller;


import com.sarth.placementos.dto.*;
import com.sarth.placementos.service.GoalService;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users/{userId}/goals")
public class GoalController {


private final GoalService service;



@PostMapping
@ResponseStatus(HttpStatus.CREATED)
GoalResponse create(

@PathVariable Long userId,

@RequestBody @Valid GoalRequest request

){

return service.create(
userId,
request
);

}




@GetMapping
List<GoalResponse> list(
@PathVariable Long userId
){

return service.getGoals(userId);

}


}