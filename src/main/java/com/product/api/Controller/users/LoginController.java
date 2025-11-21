package com.product.api.Controller.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.product.api.Dtos.LoginRequestDTO;
import com.product.api.Dtos.LoginResponseDTO;
import com.product.api.Repository.UserRepository;
import com.product.api.exception.IncorrectEmailOrPassordException;
import com.product.api.exception.UserNotFoundException;
import com.product.api.models.UserModel;
import com.product.api.security.JwtUtil;


@RestController
@RequestMapping("/Users") 
public class LoginController {
     @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> Login(@RequestBody LoginRequestDTO requested_User){
        String requested_user_Email = requested_User.getEmail();

        UserModel User = userRepository
            .findByEmail(requested_user_Email)
            .orElseThrow(() -> new UserNotFoundException("Invalid credentials"));

        if(!passwordEncoder.matches(requested_User.getPassword(),User.getPassword())){
            throw new IncorrectEmailOrPassordException("Senha ou nome de usuario incorreto");
        }
        
        String accessToken = jwtUtil.generateAccessToken(User);

        LoginResponseDTO dto = new LoginResponseDTO(
            "Usuario Logado com sucesso!",
            accessToken 
        );
        
        return ResponseEntity.status(HttpStatus.ACCEPTED)
            .body(dto);
    }
}
