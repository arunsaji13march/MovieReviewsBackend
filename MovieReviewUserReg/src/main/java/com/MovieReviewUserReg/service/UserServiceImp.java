package com.MovieReviewUserReg.service;

import com.MovieReviewUserReg.exception.UserAlreadyExistsException;
import com.MovieReviewUserReg.exception.UserNotFoundException;
import com.MovieReviewUserReg.model.User;
import com.MovieReviewUserReg.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private  UserRepository userRepository;
    @Override
    public String saveUser(User uobj) throws UserAlreadyExistsException {
        User adduobj = null;

        User storedDetails = this.userRepository.findByEmail(uobj.getEmail());

        if (storedDetails != null) {
            throw new UserAlreadyExistsException();
        } else {
            uobj.setCreatedAt(LocalDateTime.now());
            adduobj = this.userRepository.save(uobj);
            return "true";
        }
    }

    @Override
    public User updateUser(User updatedobj, int uid) throws UserNotFoundException {
        Optional <User> userOptional = this.userRepository.findById(uid);
        User uObj = null;
        User updatedData = null;
        if(userOptional.isPresent())
        {
            uObj = userOptional.get();
            if(updatedobj.getUserName()!=null){
                uObj.setUserName(updatedobj.getUserName());
            }
            if(updatedobj.getEmail()!=null){
                uObj.setEmail(updatedobj.getEmail());
            }
            if(updatedobj.getPassword()!=null){
                uObj.setPassword(updatedobj.getPassword());
            }

            uObj.setUpdatedAt(LocalDateTime.now());
            updatedData=this.userRepository.save(uObj);
        }
        else {
            throw new UserNotFoundException();
        }
        return updatedData;
    }

    @Override
    public User getUserById(int uid) throws UserNotFoundException {
        Optional <User> userOptional = this.userRepository.findById(uid);
        User uObj = null;

        if(userOptional.isPresent())
        {
            uObj = userOptional.get();
        }
        else
        {
            throw new UserNotFoundException();
        }

        return uObj;
    }

    @Override
    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    @Override
    public boolean delUser(int uid) throws UserNotFoundException {
        Optional <User> userOptional = this.userRepository.findById(uid);
        boolean  status = false;
        if(userOptional.isPresent())
        {
            this.userRepository.delete(userOptional.get());
            status = true;
        }
        else
        {
            throw new UserNotFoundException()   ;
        }
        return status;
    }

    @Override
    public User getuserByUserEmail(String userEmail) throws UserNotFoundException {
        return this.userRepository.findByEmail(userEmail);
    }
}
