package Panri.Backend.service;

import Panri.Backend.model.User;
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

    @Autowired
    UserRepository userRepository;

    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public User authenticate(String username, String password){
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("El usuario no fue encontrado"));
        if(passwordEncoder.matches(password, user.getPassword())){
            return user;
        }
        throw new RuntimeException("Contraseña Incorrecta");
    }

}