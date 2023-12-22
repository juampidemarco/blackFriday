package com.cbi.blackFriday.service.impl;

import com.cbi.blackFriday.dao.request.SignUpRequest;
import com.cbi.blackFriday.dao.request.SigninRequest;
import com.cbi.blackFriday.dao.response.JwtAuthenticationResponse;
import com.cbi.blackFriday.entities.Role;
import com.cbi.blackFriday.entities.User;
import com.cbi.blackFriday.exception.ApiRequestException;
import com.cbi.blackFriday.exception.UserErrorException;
import com.cbi.blackFriday.repository.UserRepository;
import com.cbi.blackFriday.service.AuthenticationService;
import com.cbi.blackFriday.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    private static final Logger log = LoggerFactory.getLogger(AuthenticationServiceImpl.class);

    @Override
    public User signup(SignUpRequest request) {
        log.debug("enter signup service");
        User saveUser;

        validateRequest(request.getEmail(), request.getPassword());

        var user = User.builder().firstName(request.getFirstName()).lastName(request.getLastName())
                .email(request.getEmail()).password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER).build();
        try {
            saveUser = userRepository.save(user);
        } catch (Exception e){
            log.error("error signup user: "+ e.getMessage());
            throw new UserErrorException("error signup user");
        }

        return  saveUser;
    }

    @Override
    public JwtAuthenticationResponse signin(SigninRequest request) {
        log.debug("enter signin service");

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UserErrorException("Invalid email or password."));

        var jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }

    public boolean validateRequest(String email, String password) {
        log.debug("validating request with email: " +email);

        if (Objects.isNull(email)) {
            log.error("error validating request. Email is required");
            throw new ApiRequestException("Email is required");
        }

        if (Objects.isNull(password)) {
            log.error("error validating request. Email is required");
            throw new ApiRequestException("Password is required");
        }

        return true;
    }


}
