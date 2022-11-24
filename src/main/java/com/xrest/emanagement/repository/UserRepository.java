package com.xrest.emanagement.repository;

import com.xrest.emanagement.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends BaseRepository<User,Long>{
    User findUserByUsername(String username);

    User findUserByEmail(String email);

    @Query(value = "select * from users u where u.otp = :otp and u.id = :id", nativeQuery = true)
    User fuindUserByOtp(@Param("id") Long id, @Param("otp") Long otp);

}
