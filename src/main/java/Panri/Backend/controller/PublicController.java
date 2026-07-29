package Panri.Backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PublicController {

    @GetMapping("public")
    public ResponseEntity<String> getPublic(){
        return ResponseEntity.ok("Hola desde la api publica");
    }
}
