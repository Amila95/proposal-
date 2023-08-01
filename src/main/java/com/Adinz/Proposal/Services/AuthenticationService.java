package com.Adinz.Proposal.Services;

import com.Adinz.Proposal.Enum.Role;
import com.Adinz.Proposal.Model.User;
import com.Adinz.Proposal.PalyLoad.AuthenticationRequest;
import com.Adinz.Proposal.PalyLoad.AuthenticationResponse;
import com.Adinz.Proposal.PalyLoad.RegisterRequest;
import com.Adinz.Proposal.Repositories.UserRepository;
import com.Adinz.Proposal.exceptions.UsernameAlreadyExistsException;
import com.Adinz.Proposal.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request){
        Optional<User> userAE= userRepository.findByEmail(request.getEmail());
        if(userAE.isPresent()){
            throw new UsernameAlreadyExistsException("Username " + request.getEmail()+ " already exists");

        }

        var user = User.builder()
                .firstName(request.getFirstname())
                .lastName(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();

    }


    public AuthenticationResponse login(AuthenticationRequest request){
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow();
        if(!passwordEncoder.matches(request.getPassword(),user.getPassword())){
            throw new UsernameNotFoundException("");
        }
        System.out.println("password login:" + user.getPassword());
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

}
