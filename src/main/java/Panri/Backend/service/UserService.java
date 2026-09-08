package Panri.Backend.service;

import Panri.Backend.DTOs.RegisterStudentDTO;
import Panri.Backend.model.UserEntity;
import Panri.Backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public UserEntity fieldCalculated(RegisterStudentDTO fielCalculatedDTO) {
        //concatenar los datos y devolver el resultado generado
        UserEntity userNameField = new UserEntity();

        //Extraer datos del DTO
        //Primeras iniciales
        String firstName = fielCalculatedDTO.getFirstName();
        //Primeras iniciales
        String lastName = fielCalculatedDTO.getLastName();
        //Falta agregar el campo de timestamp(milisegundos) en la entidad user
        //Agarrar los datos del grado y grupo



        return userRepository.save(userNameField);
    }

}
