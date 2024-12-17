package com.bloghub.bloghub.restImplement;

import com.bloghub.bloghub.entities.UserInfo;
import com.bloghub.bloghub.service.UseInfoService;
import com.bloghub.bloghub.rest.UserInfoRest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserInfoRestImplement implements UserInfoRest {

    Logger log = LoggerFactory.getLogger(UserInfoRestImplement.class);

    @Autowired
    private UseInfoService userInfoService;

    @Override
    public ResponseEntity<?> addNewUser(UserInfo userInfo) {
        try {
            return userInfoService.addNewUser(userInfo);
        } catch (Exception ex) {
            log.error("Error in addNewUser {}", ex);
        }
        return new ResponseEntity<>("{\"message\":\"Something went wrong\"}", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}