package com.example.judesys.controllers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.judesys.contracts.LoginRequest;
import com.example.judesys.contracts.RegisterUserRequest;
import com.example.judesys.contracts.RoleRequest;
import com.example.judesys.contracts.UserRoleRequest;
import com.example.judesys.interfaces.IUserService;
import com.example.judesys.models.Role;
import com.example.judesys.models.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
@Validated
public class AuthController {
    private final IUserService userService;
    private final AuthenticationManager authenticationManager;

    @GetMapping("/")
    @CrossOrigin
    public ResponseEntity<?> getUsers() {
        return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
    }

    @PostMapping("/")
    @CrossOrigin
    public ResponseEntity<?> registerUser(@RequestBody RegisterUserRequest registerUserRequest) {
        return new ResponseEntity<>(userService.saveUser(registerUserRequest), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/auth", method = RequestMethod.POST)
    public ResponseEntity<?> getAuthenticationToken(@RequestBody LoginRequest yourRequestDTO, HttpServletRequest request,
                                                    HttpServletResponse response) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            yourRequestDTO.getUsername(),
                            yourRequestDTO.getPassword()
                    )
            );
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>("pzdc", HttpStatus.BAD_REQUEST);
        }
        var x = authenticationManager.authenticate( new UsernamePasswordAuthenticationToken(
                yourRequestDTO.getUsername(),
                yourRequestDTO.getPassword()
        ));

        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User)x.getPrincipal();
        Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());

        String access_token = JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + 1 * 60 * 100000))   //1000min
                .withIssuer("Judesys")
                .withClaim("roles", user.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .sign(algorithm);

        String refresh_token = JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + 30 * 60 * 100000))   //1000min
                .withIssuer("Judesys")
                .sign(algorithm);
//        response.setHeader("access_token", access_token);
//        response.setHeader("refresh_token", refresh_token);

        Map<String, String> tokens = new HashMap<>();
        tokens.put("access_token", access_token);
        tokens.put("refresh_token", refresh_token);
        response.setContentType(APPLICATION_JSON_VALUE);
        System.out.println("Giving back token");
//        new ObjectMapper().writeValue(response.getOutputStream(), tokens);

        System.out.println(x.toString());
        return new ResponseEntity<>(tokens, HttpStatus.OK);
    }

    @PostMapping("/roles/")
    public ResponseEntity<?> saveRole(@RequestBody RoleRequest roleRequest) {
        return new ResponseEntity<>(userService.saveRole(roleRequest), HttpStatus.CREATED);
    }

    @PutMapping("/roles/")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void addRoleToUser(@RequestBody UserRoleRequest userRoleRequest) {
        userService.addRoleToUser(userRoleRequest);
    }

    @GetMapping("/token/refresh")
    @CrossOrigin
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authHeader = request.getHeader(AUTHORIZATION);
        if(authHeader != null && authHeader.startsWith("Bearer ")) {
            try {
                String refresh_token = authHeader.substring("Bearer ".length());
                Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(refresh_token);

                String username = decodedJWT.getSubject();
                User user = userService.getUser(username);

                String accessToken = JWT.create()
                        .withSubject(user.getUsername())
                        .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 100000))   //10min
                        .withIssuer("Judesys")
                        .withClaim("roles", user.getRoles().stream()
                                .map(Role::getName).collect(Collectors.toList()))
                        .sign(algorithm);

                Map<String, String> tokens = new HashMap<>();
                tokens.put("access_token", accessToken);
                tokens.put("refresh_token", refresh_token);
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), tokens);

            } catch (Exception e) {
                System.out.println("AUTHORIZATION ERROR");
                response.setHeader("error", e.getMessage());
                response.setStatus(FORBIDDEN.value());
                Map<String, String> error = new HashMap<>();
                error.put("error_message", e.getMessage());
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), error);
            }

        }
        else {
            throw new RuntimeException("Refresh token is missing");
        }
    }
}
