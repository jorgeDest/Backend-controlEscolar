package Panri.Backend.DTOs.jwt;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Dto que manda un token
 * para la autorización
 */
@Data
@AllArgsConstructor
public class AuthenticationResponse {

    private String jwtToken;

}
