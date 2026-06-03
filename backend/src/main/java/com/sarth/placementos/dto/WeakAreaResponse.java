package com.sarth.placementos.dto;


import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class WeakAreaResponse {


    private String name;

    private String type;

    private String reason;
}