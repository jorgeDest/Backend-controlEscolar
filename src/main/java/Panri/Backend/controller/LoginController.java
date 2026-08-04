package Panri.Backend.controller;

import Panri.Backend.DTOs.LoginUserDto;
import Panri.Backend.DTOs.LoginUserResponseDto;
import Panri.Backend.DTOs.jwt.AuthenticationRequest;
import Panri.Backend.DTOs.jwt.AuthenticationResponse;
import Panri.Backend.model.UserEntity;
import Panri.Backend.service.LoginService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/Login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("Users")
    public AuthenticationResponse loginUsers(@RequestBody @Valid AuthenticationRequest authenticationRequest){

        AuthenticationResponse response = new AuthenticationResponse();

        return new AuthenticationResponse();

    }

}
