package com.cbi.blackFriday.service;

import com.cbi.blackFriday.dao.request.SignUpRequest;
import com.cbi.blackFriday.dao.request.SigninRequest;
import com.cbi.blackFriday.dao.response.JwtAuthenticationResponse;
import com.cbi.blackFriday.entities.User;

public interface AuthenticationService {
    User signup(SignUpRequest request);

    JwtAuthenticationResponse signin(SigninRequest request);
}
