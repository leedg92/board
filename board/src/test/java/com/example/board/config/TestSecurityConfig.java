package com.example.board.config;

import com.example.board.dto.UserAccountDto;
import com.example.board.repository.UserAccountRepository;
import com.example.board.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.event.annotation.BeforeTestMethod;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@Import(SecurityConfig.class)
public class TestSecurityConfig {

    @MockBean private UserAccountRepository userAccountRepository;
    @MockBean private UserAccountService userAccountService;



    @BeforeTestMethod
    public void securitySetup() {


        given(userAccountService.searchUser(anyString()))
                .willReturn(Optional.of(createUserAccountDto()));
        given(userAccountService.saveUser(anyString(), anyString(), anyString(), anyString(), anyString()))
                .willReturn(createUserAccountDto());
    }


    private UserAccountDto createUserAccountDto(){
        return UserAccountDto.of(
                "test",
                "pw",
                "test@test.com",
                "testnick",
                "testmemo"
        );
    }
}
