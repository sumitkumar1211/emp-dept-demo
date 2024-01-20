package com.example.empdeptdemo.exceptions;

import lombok.Builder;

import java.util.Date;

@Builder
public record CustomErrorResponse(Date date, String msg, String details) {
}
