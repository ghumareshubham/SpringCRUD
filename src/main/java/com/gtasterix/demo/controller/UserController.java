package com.gtasterix.demo.controller;

import com.gtasterix.demo.dto.ResponseDTO;
import com.gtasterix.demo.dto.UserDTO;
import com.gtasterix.demo.entity.User;
import com.gtasterix.demo.exceptions.BaseException;
import com.gtasterix.demo.interfaces.UserService;
import com.gtasterix.demo.utils.BaseResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserService userService;


    @GetMapping("/getByUserId")
    public ResponseEntity<?> getByUserId(@RequestParam Integer userID) {
        try {
            User user = userService.getByUserId(userID);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO("success", user));

        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDTO("unsuccess", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDTO("unsuccess", e.getMessage()));
        }
    }


    @GetMapping("/getAllUsers")
    public ResponseEntity<?> getAllUsers() {

        try {
            List<User> users = userService.getAllUsers();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO("success", users));

        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDTO("unsuccess", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDTO("unsuccess", e.getMessage()));
        }

    }


    @PostMapping("/createUser")
    public ResponseEntity<BaseResponseDTO> createUser(@RequestBody UserDTO userDTO) {

        try {


            BaseResponseDTO response = userService.createUser(userDTO);
            return ResponseEntity.status(HttpStatus.OK).body(new BaseResponseDTO("Successful", response.getMessage()));
        } catch (BaseException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new BaseResponseDTO("Unsuccessful", e.getMessage()));
        }


    }


    //DeleteUserById
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<BaseResponseDTO> deleteUserById(@PathVariable int id) {


        try {

            BaseResponseDTO response = userService.deleteUserById(id);
            return ResponseEntity.status(HttpStatus.OK).body(new BaseResponseDTO("Success", response.getMessage()));
        } catch (BaseException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new BaseResponseDTO("Unsuccessful", e.getMessage()));
        }

    }

    //DeleteAllUsers
    @DeleteMapping("/deleteAll")
    public ResponseEntity<BaseResponseDTO> deleteAllUsers() {


        try {

            BaseResponseDTO response = userService.deleteAllUsers();
            return ResponseEntity.status(HttpStatus.OK).body(new BaseResponseDTO("Successful", response.getMessage()));
        } catch (BaseException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new BaseResponseDTO("Unsuccessful", e.getMessage()));
        }

    }


    @PutMapping("editUserById/{id}")
    public ResponseEntity<?> editUserById(@RequestBody UserDTO userDTO, @PathVariable int id) {

        try {
//            User user = userService.getByUserId(userID);

            User user = userService.updateUserDetailsByID(userDTO, id);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO("success", user));

        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDTO("unsuccess", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDTO("unsuccess", e.getMessage()));
        }


    }



    @PatchMapping("/editUserByField/{id}")
    public User updateFieldsOfUserById(@PathVariable int id, @RequestBody Map<String, Optional> map) {


        return userService.updateFieldsOfUserById(id, map);


    }


}