package com.fundamentosplatzi.springboot.fundamentos.service;

import com.fundamentosplatzi.springboot.fundamentos.entity.User;
import com.fundamentosplatzi.springboot.fundamentos.repository.UserRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserService {
    private final Log LOG = LogFactory.getLog(UserService.class);
    private UserRepository userRepository;


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    //Metodo transaccional
    @Transactional
    public void  saveUserTransaccion(List<User> users) {
        users.stream().peek(user -> LOG.info("Usuario insertado: " + user))
                .forEach(userRepository::save);
                //.forEach(user -> userRepository.save(user)) == .forEach(userRepository::save);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    public User update(User updateUser, Long id) {
       return userRepository.findById(id).map(user -> {
            user.setEmail(updateUser.getEmail());
            user.setBirthDate(updateUser.getBirthDate());
            user.setName(updateUser.getName());
            return userRepository.save(user);
        }).get();
    }
}
