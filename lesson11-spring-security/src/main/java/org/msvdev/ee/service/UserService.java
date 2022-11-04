package org.msvdev.ee.service;

import lombok.RequiredArgsConstructor;
import org.msvdev.ee.entity.User;
import org.msvdev.ee.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }


    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }


    public Page<User> findWithFilter(Integer page, Integer pageSize) {
        return userRepository.findAll(PageRequest.of(page - 1, pageSize));
    }


    public List<User> findAll() {
        return userRepository.findAll();
    }


    @Transactional
    public User save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }


    @Transactional
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

}