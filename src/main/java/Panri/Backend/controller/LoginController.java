package Panri.Backend.controller;

import Panri.Backend.model.User;
import Panri.Backend.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/Login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping()
    public ResponseEntity<?> login(@RequestBody User user){
        try{
            User authenticateUser = loginService.authenticate(user.getUsername(), user.getPassword());
            return ResponseEntity.ok(authenticateUser);
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

}
