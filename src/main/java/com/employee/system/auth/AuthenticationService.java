package com.employee.system.auth;

import com.employee.system.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    final private UserRepository userRepository;

  //  final private PasswordEncoder passwordEncoder;

  //  private final JwtService jwtService;

   // private final AuthenticationManager authenticationManager;


//    public AuthenticationResponse register(RegisterRequest registerRequest) {
//
//        User newUser = new User(null,registerRequest.getFirstName(),registerRequest.getLastName(),registerRequest.getUsername(),passwordEncoder.encode(registerRequest.getPassword()),Role.INSTRUCTOR);
//
//        var admin = new Admin(null, newUser.getFirstName(), newUser.getLastName(), newUser.getUsername(), passwordEncoder.encode(registerRequest.getPassword()), newUser.getRole());
//
//        adminRepository.save(admin);
//        return getAuthenticationResponse(newUser);
//    }


//    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
//
//        authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),authenticationRequest.getPassword())
//        );
//
//        User user = userRepository.findByUsername(authenticationRequest.getUsername()).orElseThrow(UserNotFoundException::new);
//        return getAuthenticationResponse(user);
//    }


//    private AuthenticationResponse getAuthenticationResponse(User user) {
//
//        String authorities = user.getAuthorities().stream()
//                .map(GrantedAuthority::getAuthority)
//                .collect(Collectors.joining(","));
//
//        Map<String,String> userAuthorities = new HashMap<>();
//
//        userAuthorities.put("authorities",authorities);
//
//        var jwtToken = jwtService.generateToken(userAuthorities, user);
//        return AuthenticationResponse.builder().jwtToken(jwtToken).build();
//    }

}
