package com.example.spring_demo.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Collection;

@Data
@Builder
public class ErrorDetails {
    private int status;
    private String description;
    private String shortCause;
    private Collection<String> details;
}
