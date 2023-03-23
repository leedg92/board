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


    public void saveUserAccount(UserAccountDto dto) {
        System.out.println("통과하니?");
        System.out.println(dto.toEntity());

        userAccountRepository.save(dto.toEntity());
    }
}
