package com.gtasterix.demo.interfaces;

import com.gtasterix.demo.dto.UserDTO;
import com.gtasterix.demo.entity.User;
import com.gtasterix.demo.utils.BaseResponseDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UserService {


    BaseResponseDTO createUser(UserDTO userDTO);


    User getByUserId(Integer userID);


    List<User> getAllUsers();

    BaseResponseDTO deleteUserById(int id);

    BaseResponseDTO deleteAllUsers();

    User updateUserDetailsByID(UserDTO userDTO, int id);

    User updateFieldsOfUserById(int id, Map<String, Optional> map);
}
