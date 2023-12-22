package com.cbi.blackFriday.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class ApiException {
    private final Date timestamp;
    private final int code;
    private final String message;
}
