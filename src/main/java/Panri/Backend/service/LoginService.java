package Panri.Backend.service;

import Panri.Backend.DTOs.LoginUserDto;
import Panri.Backend.model.UserEntity;
import Panri.Backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Método que recibe como parametro 2 campos, username y password después inyectamos la dependencia de UseRepository.
 * Para encriptar la password se necesita importar la biblioteca BCryptPasswordEncoder, declaramos el objeto BCryptPasswordEncoder
 * después lo inicializamos
 *
 */

@Service
public class LoginService {

    @Autowired UserRepository userRepository;


    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserEntity authenticate(LoginUserDto loginUserDto){
        UserEntity user = userRepository.findByUsername(loginUserDto.getUsername())
                .orElseThrow(() -> new RuntimeException("El usuario no fue encontrado"));
        if(passwordEncoder.matches(loginUserDto.getPassword(), user.getPassword())){
            return user;
        }
        throw new RuntimeException("Contraseña Incorrecta");
    }

}