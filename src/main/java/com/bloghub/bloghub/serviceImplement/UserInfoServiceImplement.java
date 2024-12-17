package com.bloghub.bloghub.serviceImplement;


import com.bloghub.bloghub.dao.UserInfoRepository;
import com.bloghub.bloghub.entities.UserInfo;
import com.bloghub.bloghub.service.UseInfoService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Objects;
import java.util.Optional;

@Service
public class UserInfoServiceImplement implements UseInfoService {


    Logger log = LoggerFactory.getLogger(UserInfoServiceImplement.class);


    @Autowired
    UserInfoRepository userInfoRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;



    public ResponseEntity<?> addNewUser(UserInfo userInfo) {
        try {
            if (!validateUserInfo(userInfo))
                return new ResponseEntity<>("{\"message\":\"Missing Required Data\"}", HttpStatus.BAD_REQUEST);
            Optional<UserInfo> db = userInfoRepository.findByEmail(userInfo.getEmail());
            if(db.isPresent())
                return new ResponseEntity<>("{\"message\":\"Email Already Exists\"}", HttpStatus.BAD_REQUEST);
            userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
            userInfo.setIsDeletable("true");
            userInfo.setStatus("false");
            userInfo.setEmail(userInfo.getEmail().toLowerCase());
            userInfoRepository.save(userInfo);
            return new ResponseEntity<>("{\"message\":\"User Added Successfully\"}", HttpStatus.OK);
        } catch (Exception ex) {
            log.error("Error in addNewUser {}", ex);
        }
        return new ResponseEntity<>("{\"message\":\"Someting went wrong\"}", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private Boolean validateUserInfo(UserInfo userInfo) {
        return !Objects.isNull(userInfo)
                && StringUtils.hasText(userInfo.getName())
                && StringUtils.hasText(userInfo.getEmail())
                && StringUtils.hasText(userInfo.getPassword());
    }

}
