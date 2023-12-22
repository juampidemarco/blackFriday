package com.cbi.blackFriday.entities;

import com.cbi.blackFriday.exception.ApiException;

import java.util.ArrayList;
import java.util.List;

public class ErrorResponse {
    List<ApiException> error;

    public ErrorResponse() {
        this.error = new ArrayList<>();
    }

    public List<ApiException> getError() {
        return error;
    }

    public void setError(List<ApiException> error) {
        this.error = error;
    }
}
