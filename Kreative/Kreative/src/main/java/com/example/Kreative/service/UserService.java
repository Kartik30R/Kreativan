package com.example.Kreative.service;


import com.example.Kreative.model.User;
import com.example.Kreative.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepo usersRepo;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private  JWTService jwtService;
    private BCryptPasswordEncoder bCrypt= new BCryptPasswordEncoder(5);

    public User register(User user){
        user.setPassword(bCrypt.encode(user.getPassword()));
        return usersRepo.save(user);
    }

    public User login(User user) {

        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                user.getUsername(),
                                user.getPassword()
                        )
                );

        if (authentication.isAuthenticated()) {
            User dbUser = usersRepo.findByUsername(user.getUsername());
            dbUser.setToken(jwtService.generateToken(dbUser.getUsername()));
            return dbUser;
        }

        throw new RuntimeException("Invalid credentials");
    }

}
