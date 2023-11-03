package br.com.jzpacheco.restspringbootandjavaerudio.controllers;

import br.com.jzpacheco.restspringbootandjavaerudio.services.AuthServices;
import br.com.jzpacheco.restspringbootandjavaerudio.vo.v1.security.AccountCredentialsVO;
import br.com.jzpacheco.restspringbootandjavaerudio.vo.v1.security.TokenVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@Tag(name = "Authentication Endpoint")
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthServices authServices;

    private Logger logger;

    @Operation(summary = "Authenticates an user and returns a token")
    @PostMapping(value = "/signin")
    public ResponseEntity signin(@RequestBody AccountCredentialsVO data){

        if(checkIfParamsIsNotNull(data)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request");
        }
        var token = authServices.sigin(data);

        if (token == null){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request");
        }
        return token;
    }

    private static boolean checkIfParamsIsNotNull(AccountCredentialsVO data) {
        return data == null || data.getUsername() == null || data.getPassword().isBlank() || data.getPassword() == null || data.getPassword().isBlank();
    }

}
