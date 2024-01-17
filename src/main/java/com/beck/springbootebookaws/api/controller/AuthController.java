package com.beck.springbootebookaws.api.controller;

import com.beck.springbootebookaws.api.payload.auth.AuthRequest;
import com.beck.springbootebookaws.api.payload.auth.AuthResponse;
import com.beck.springbootebookaws.api.payload.client.ClientRequest;
import com.beck.springbootebookaws.api.payload.client.ClientResponse;
import com.beck.springbootebookaws.api.payload.vendor.VendorRequest;
import com.beck.springbootebookaws.api.payload.vendor.VendorResponse;
import com.beck.springbootebookaws.config.security.JwtTokenUtil;
import com.beck.springbootebookaws.db.entity.User;
import com.beck.springbootebookaws.db.services.ClientService;
import com.beck.springbootebookaws.db.services.VendorService;
import com.beck.springbootebookaws.exceptions.ExceptionType;
import com.beck.springbootebookaws.mapper.AuthMapper;
import com.beck.springbootebookaws.repository.UserRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/public")
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@Tag(name = "Auth API", description = "Registration and authentication")
public class AuthController {

    private final JwtTokenUtil jwtTokenUtil;
    private final UserRepository repository;
    private final AuthMapper authMapper;
    private final VendorService vendorService;
    private final ClientService userService;
    private final UserDetailsService userDetailsService;


    @Operation(summary = "Login", description = "Only registered users can login")
    @PostMapping("login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        try {
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());
            User user = repository.findByEmail(authenticationToken.getName()).get();
            log.info("Inside AuthController get login method");
            return ResponseEntity.ok()
                    .body(authMapper.view(jwtTokenUtil.generateToken(user), ExceptionType.SUCCESSFULLY, user));
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(authMapper.view("", ExceptionType.LOGIN_FAILED, null));
        }
    }

    @Operation(summary = "Register vendor's", description = "Vendor registration")
    @PostMapping("register/vendor")
    public VendorResponse registration(@RequestBody VendorRequest request) {
        log.info("Inside AuthController registration vendor method");
        return vendorService.register(request);
    }

    @Operation(summary = "Register client", description = "Client registration")
    @PostMapping("register/client")
    public ClientResponse registration(@RequestBody ClientRequest request) {
        log.info("Inside AuthController registration client method");
        return userService.registration(request);
    }


}
