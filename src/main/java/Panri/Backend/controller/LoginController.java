package Panri.Backend.controller;

import Panri.Backend.DTOs.LoginUserDto;
import Panri.Backend.DTOs.LoginUserResponseDto;
import Panri.Backend.model.User;
import Panri.Backend.service.LoginService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/Login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("login")
    public LoginUserResponseDto loginUsers(@RequestBody @Valid LoginUserDto loginUserDto){

        User user = loginService.authenticate(loginUserDto);
        LoginUserResponseDto loginUserResponseDto = new LoginUserResponseDto();

        loginUserResponseDto.setUsername(loginUserDto.getUsername());


        return new LoginUserResponseDto();
    }

}
