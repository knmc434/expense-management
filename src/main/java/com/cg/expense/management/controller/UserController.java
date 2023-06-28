package com.cg.expense.management.controller;

import com.cg.expense.management.domain.JwtResponse;
import com.cg.expense.management.domain.LoginDto;
import com.cg.expense.management.domain.UserDto;
import com.cg.expense.management.security.JwtUtils;
import com.cg.expense.management.service.UserService;
import com.cg.expense.management.validator.UserValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/api/auth")
@Slf4j
public class UserController {

    @Autowired
    UserValidator userValidator;

    @Autowired
    UserService userService;

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/registration")
    public ResponseEntity<?> createUser(@RequestBody  @Valid UserDto userDTO, BindingResult bindingResult) {
        userValidator.validate(userDTO, bindingResult);

        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.
                    getAllErrors()
                    .stream().map(e -> e.getDefaultMessage()).collect(toList());
            return ResponseEntity.badRequest().body(errors);
        }

        if (userService.checkUserExists(userDTO.getUserName()).isPresent()) {
            return ResponseEntity.badRequest().body("User Already exists, please signin");
        }

        return ResponseEntity.ok(userService.createUser(userDTO));
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtResponse> loginUser(@RequestBody LoginDto loginDto) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(loginDto.getUsername());
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails.getUsername(), loginDto.getPassword(), userDetails.getAuthorities());

        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getUsername(),
                roles));
    }
}
