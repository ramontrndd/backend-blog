package com.bloghub.bloghub.jwtService;

import com.bloghub.bloghub.dao.UserInfoRepository;
import com.bloghub.bloghub.entities.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserInfoService implements UserDetailsService {

    @Autowired
    private UserInfoRepository repository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<UserInfo> userDetails = repository.findByEmail(email);
        return userDetails.map(UserInfoDetails::new).orElseThrow(() -> new UsernameNotFoundException("User not found" + email));
    }
}
