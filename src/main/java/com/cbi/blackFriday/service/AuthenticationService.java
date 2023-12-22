package com.cbi.blackFriday.service;

import com.cbi.blackFriday.dao.request.SignUpRequest;
import com.cbi.blackFriday.dao.request.SigninRequest;
import com.cbi.blackFriday.dao.response.JwtAuthenticationResponse;

public interface AuthenticationService {
    JwtAuthenticationResponse signup(SignUpRequest request);

    JwtAuthenticationResponse signin(SigninRequest request);
}
