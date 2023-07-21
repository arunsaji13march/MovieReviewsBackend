package com.MovieReviewUserReg.service;

import com.MovieReviewUserReg.exception.UserAlreadyExistsException;
import com.MovieReviewUserReg.exception.UserNotFoundException;
import com.MovieReviewUserReg.model.User;

import java.util.List;

public interface UserService {
    public String saveUser(User uobj) throws UserAlreadyExistsException;
    public User updateUser(User uobj, int uid) throws UserNotFoundException;
    public User getUserById(int uid) throws UserNotFoundException;
    public List<User> getAllUsers();
    public boolean delUser(int uid) throws UserNotFoundException;
    public User getuserByUserEmail(String userEmail) throws UserNotFoundException;
}
