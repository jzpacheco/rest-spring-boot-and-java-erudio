package br.com.jzpacheco.restspringbootandjavaerudio.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.jzpacheco.restspringbootandjavaerudio.vo.v1.security.AccountCredentialsVO;
import br.com.jzpacheco.restspringbootandjavaerudio.vo.v1.security.TokenVO;
import br.com.jzpacheco.restspringbootandjavaerudio.repositories.UserRepository;
import br.com.jzpacheco.restspringbootandjavaerudio.security.jwt.JwtTokenProvider;
import org.springframework.security.core.AuthenticationException;

import java.util.logging.Logger;

@Service
public class AuthServices {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private UserRepository userRepository;

    private Logger logger;

    public ResponseEntity sigin(AccountCredentialsVO data){
        try{


            var username = data.getUsername();
            var password = data.getPassword();

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));


            var user = userRepository.findByUsername(username);

            var tokenResponse = new TokenVO();
            if (user != null) {
                tokenResponse = tokenProvider.createAcessToken(username, user.getRoles());

                System.out.println("tokenResponse: "+tokenResponse);
            }else {
                throw new UsernameNotFoundException("Username: "+username+ " not found!");
            }

            return ResponseEntity.ok(tokenResponse);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username/password supplied!");
        }
    }
}
