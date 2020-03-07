package com.todoapp.Todoapp.controller;


import com.todoapp.Todoapp.model.User;
import com.todoapp.Todoapp.payloads.ApiResponse;
import com.todoapp.Todoapp.payloads.LoginRequest;
import com.todoapp.Todoapp.payloads.LoginResponse;
import com.todoapp.Todoapp.payloads.SignUpRequest;
import com.todoapp.Todoapp.repository.UserRepository;
import com.todoapp.Todoapp.security.CustomUserDetailService;
import com.todoapp.Todoapp.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping({"/todo/api/auth"})
public class AuthController
{

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private CustomUserDetailService customUserDetailService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @PostMapping({"/login"})
    public ResponseEntity Login(@RequestBody LoginRequest loginRequest) throws Exception {
        try
        {
            authenticationManager.authenticate(new
                    UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword()));
            System.out.println(loginRequest.getPassword());
        }
        catch (BadCredentialsException e)
        {
            throw new Exception("BAD PASSWORD OR USERNAME", e);
        }

        final UserDetails userDetails = customUserDetailService.loadUserByUsername(loginRequest.getUserName());

        final String jwt = jwtTokenProvider.generateToken(userDetails);

        return ResponseEntity.ok(new LoginResponse(jwt));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        if(userRepository.existsByUserName(signUpRequest.getUserName())) {
            return new ResponseEntity(new ApiResponse(false, "Username is already taken!"),
                    HttpStatus.BAD_REQUEST);
        }

        // Creating user's account
//        this.userId = userId;
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.userName = userName;
//        this.password = password;
//        this.roles = roles;

        User user = new User(signUpRequest.getFirstName(), signUpRequest.getLastName(),
                signUpRequest.getUserName(), signUpRequest.getPassword(), signUpRequest.getRoles());

        user.setPassword(passwordEncoder.encode(user.getPassword()));


        User result = userRepository.save(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/users/{username}")
                .buildAndExpand(result.getUserName()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true, "User registered successfully"));
    }


}
