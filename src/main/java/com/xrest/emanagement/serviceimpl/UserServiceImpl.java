package com.xrest.emanagement.serviceimpl;

import com.xrest.emanagement.core.OTP;
import com.xrest.emanagement.entity.User;
import com.xrest.emanagement.repository.UserRepository;
import com.xrest.emanagement.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends BaseServiceImpl<User,Long> implements UserService{
    private final UserRepository userRepository;
    private final OTP otp;

    public UserServiceImpl(UserRepository userRepository, OTP otp) {
        super(userRepository);
        this.userRepository = userRepository;
        this.otp = otp;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findUserByUsername(username);
    }

    @Override
    public User register(User user) {
        User existing = userRepository.findUserByEmail(user.getEmail());
        if (null == existing) {
            user = userRepository.save(user);
            otp.setEmail(user.getEmail());
            Thread thread = new Thread(otp);
            thread.start();
            return user;
        }
        return null;
    }
}
