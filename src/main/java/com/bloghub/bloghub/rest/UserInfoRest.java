package com.bloghub.bloghub.rest;


import com.bloghub.bloghub.entities.UserInfo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(path= "/appUser")
public interface UserInfoRest {


    @PostMapping(path = "/addNewUser")
    ResponseEntity<?> addNewUser (@RequestBody (required = true) UserInfo userInfo);

}
