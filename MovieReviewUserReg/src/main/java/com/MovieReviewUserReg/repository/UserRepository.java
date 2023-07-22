package com.MovieReviewUserReg.repository;

import com.MovieReviewUserReg.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
    User findByEmail(String userEmail);

}
