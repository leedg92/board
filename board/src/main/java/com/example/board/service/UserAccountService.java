package com.example.board.service;

import com.example.board.dto.UserAccountDto;
import com.example.board.repository.UserAccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class UserAccountService {

    private final UserAccountRepository userAccountRepository;


    public String saveUserAccount(UserAccountDto dto) {

        boolean isUser = isUser(dto.userId());
        if(isUser == false){
            userAccountRepository.save(dto.toJoinEntity());
            return "가입에 성공하였습니다.";
        }else{
            return "이미 가입된 아이디입니다.";
        }
    }

    public boolean isUser(String userId){
        System.out.println("in isUser ::: " + userAccountRepository.findById(userId).isPresent());
        return userAccountRepository.findById(userId).isPresent();
    }
}
