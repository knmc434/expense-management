package com.cg.expense.management.service;

import com.cg.expense.management.domain.UserDto;
import com.cg.expense.management.repository.UserRepository;
import com.cg.expense.management.repository.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.springframework.beans.BeanUtils.copyProperties;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public UserDto createUser(UserDto userDTO) {
        User entity = new User();
        copyProperties(userDTO, entity);
        User user = userRepository.save(entity);
        copyProperties(user, userDTO);
        return userDTO;
    }

    public Optional<?> checkUserExists(String userName) {
        return userRepository.findByUserName(userName);
    }
}
