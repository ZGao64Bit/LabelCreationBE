package com.zack.labelcreation.services;

import com.zack.labelcreation.exceptions.UserAlreadyExistException;
import com.zack.labelcreation.models.Authority;
import com.zack.labelcreation.models.User;
import com.zack.labelcreation.models.UserRole;
import com.zack.labelcreation.repositories.AuthorityRepository;
import com.zack.labelcreation.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;


@Service
public class RegisterService {
    private UserRepo userRepo;
    private AuthorityRepository authorityRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public RegisterService(UserRepo userRepo, AuthorityRepository authorityRepository, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.authorityRepository = authorityRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void add(User user, UserRole role) throws UserAlreadyExistException {
        if (userRepo.existsById(user.getUsername())) {
            throw new UserAlreadyExistException("User already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(true);
        userRepo.save(user);
        authorityRepository.save(new Authority(user.getUsername(), role.name()));
    }

}

