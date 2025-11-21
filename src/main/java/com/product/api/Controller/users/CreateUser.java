package com.product.api.Controller.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.product.api.Dtos.CreateUserDTO;
import com.product.api.Dtos.CreateUserReponseDTO;
import com.product.api.Repository.UserRepository;
import com.product.api.models.UserModel;

@RestController
@RequestMapping("/Users") 
public class CreateUser {

    @Autowired
    private UserRepository userrepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/Create")
    ResponseEntity<CreateUserReponseDTO> create(@RequestBody CreateUserDTO newuser) {

        String encodedPassword = passwordEncoder.encode(newuser.getPassword());

        UserModel user = new UserModel();
        user.setEmail(newuser.getEmail());      
        user.setPassword(encodedPassword);
        user.setRole("U");
            
        userrepository.save(user);

        CreateUserReponseDTO  response = (new CreateUserReponseDTO(
            newuser.getEmail(),
            "U"
        ));

        return ResponseEntity.status(HttpStatus.CREATED)
        .body(response);
    }
}
