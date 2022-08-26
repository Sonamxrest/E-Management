package com.xrest.emanagement.repository;

import com.xrest.emanagement.entity.User;


public interface UserRepository extends BaseRepository<User,Long>{
    User findUserByUsername(String username);

    User findUserByEmail(String email);

}
