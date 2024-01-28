package com.hobbyhop.domain.user.service;

import com.hobbyhop.domain.user.dto.*;
import com.hobbyhop.global.security.userdetails.UserDetailsImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface UserService {
    void signup(SignupRequestDTO signupRequestDTO);
    void login(LoginRequestDTO loginRequestDTO, HttpServletResponse response);
    void logout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse);
    void updateProfile(UpdateProfileRequestDTO updateProfileRequestDTO, UserDetailsImpl userDetails, HttpServletResponse httpServletResponse, HttpServletRequest httpServletRequest);
    MyProfileResponseDTO getMyProfile(UserDetailsImpl userDetails, HttpServletResponse httpServletResponse, HttpServletRequest httpServletRequest);
    OtherProfileResponseDTO getOtherProfile(Long otherUserId, UserDetailsImpl userDetails, HttpServletResponse httpServletResponse, HttpServletRequest httpServletRequest);
}