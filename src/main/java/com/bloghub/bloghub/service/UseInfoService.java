package com.bloghub.bloghub.service;

import com.bloghub.bloghub.entities.UserInfo;
import org.springframework.http.ResponseEntity;

public interface UseInfoService {

    ResponseEntity<?> addNewUser(UserInfo userInfo);
}