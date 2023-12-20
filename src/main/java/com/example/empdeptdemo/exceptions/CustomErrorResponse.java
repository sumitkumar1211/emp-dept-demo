package com.example.empdeptdemo.exceptions;

import java.util.Date;

public record CustomErrorResponse(Date date, String msg, String details) {
}
