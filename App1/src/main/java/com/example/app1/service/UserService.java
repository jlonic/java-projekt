package com.example.app1.service;
import com.example.app1.model.User;
import com.example.app1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public void addUser(User user){
        String hashPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(hashPassword);
        userRepository.save(user);
    }
    public boolean authentication(User user){
        User dbUser = userRepository.findByUsername(user.getUsername());

        return dbUser != null && bCryptPasswordEncoder.matches(user.getPassword(), dbUser.getPassword());
    }
    public boolean isUsernameTaken(String username){
        User user = userRepository.findByUsername(username);

        return user != null;
    }

    public User getUserById(Long userId){
        return userRepository.getReferenceById(userId);
    }
}
