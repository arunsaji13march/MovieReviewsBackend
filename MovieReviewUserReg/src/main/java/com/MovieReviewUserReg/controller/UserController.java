package com.MovieReviewUserReg.controller;

import com.MovieReviewUserReg.exception.UserAlreadyExistsException;
import com.MovieReviewUserReg.exception.UserNotFoundException;
import com.MovieReviewUserReg.model.User;
import com.MovieReviewUserReg.service.UserService;
import com.MovieReviewUserReg.service.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/user/v1")
public class UserController {

    @Autowired
    private UserService userService;


    private ResponseEntity<?> responseEntity;

    @PostMapping("/register")
    public ResponseEntity<?> saveUserHandler(@RequestBody User uobj) throws UserAlreadyExistsException
    {
        try
        {
            String newUser = this.userService.saveUser(uobj);
            responseEntity = new ResponseEntity<>(newUser, HttpStatus.CREATED);
        }catch (UserAlreadyExistsException e) {
            responseEntity = new ResponseEntity<>("User Already Exist", HttpStatus.CONFLICT);
            e.printStackTrace();
        }
        return responseEntity;
    }

    @GetMapping("/getAllUsers")
    public ResponseEntity<?> GetUserHandler()
    {
        List<User> allUsers = this.userService.getAllUsers();
        responseEntity = new ResponseEntity<>(allUsers,HttpStatus.OK);
        return responseEntity;
    }

    @PutMapping("/updateuser/{uid}")
    public ResponseEntity<?> updateUserHandler(@RequestBody User uobj, @PathVariable int uid) throws UserNotFoundException
    {
        try {
            User updateUser = this.userService.updateUser(uobj,uid);
            responseEntity = new ResponseEntity<>(updateUser, HttpStatus.CREATED);
        }catch(UserNotFoundException e) {
            responseEntity = new ResponseEntity("User Not found",HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }


    @GetMapping("/getuserbyid/{uid}")
    public ResponseEntity<?> getUserByIdHandler(@PathVariable int uid) throws UserNotFoundException
    {
        try {
            User uObj = this.userService.getUserById(uid);
            responseEntity = new ResponseEntity<>(uObj, HttpStatus.OK);
        }catch(UserNotFoundException e) {
            responseEntity = new ResponseEntity("User Not found", HttpStatus.NOT_FOUND);
        }
        return responseEntity;

    }

    @DeleteMapping("/deleteUser/{uid}")
    public ResponseEntity<?> DeleteUserByIdHandler(@PathVariable int uid) throws UserNotFoundException
    {
        try {
            boolean status= this.userService.delUser(uid);
            responseEntity = new ResponseEntity<>(status, HttpStatus.OK);
        }catch(UserNotFoundException e) {
            responseEntity = new ResponseEntity<>("User not exist",HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }


    @GetMapping("/getuserbyuserEmail/{userEmail}")
    public ResponseEntity<?> getuserByUserEmailHandler(@PathVariable String userEmail) throws UserNotFoundException
    {
        User uObj = this.userService.getuserByUserEmail(userEmail);
        responseEntity = new ResponseEntity<>(uObj, HttpStatus.OK);
        return responseEntity;
    }
}
