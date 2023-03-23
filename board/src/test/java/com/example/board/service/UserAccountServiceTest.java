package com.example.board.service;

import com.example.board.domain.Article;
import com.example.board.domain.UserAccount;
import com.example.board.dto.ArticleDto;
import com.example.board.dto.UserAccountDto;
import com.example.board.repository.ArticleRepository;
import com.example.board.repository.UserAccountRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@DisplayName("비즈니스 로직 - 회원가입")
@ExtendWith(MockitoExtension.class)
class UserAccountServiceTest {

    @InjectMocks
    private UserAccountService sut;

    @Mock private UserAccountRepository userAccountRepository;

    @DisplayName("회원 정보를 입력하면, 테이블에 새로운 회원을 생성한다.")
    @Test
    void givenArticleInfo_whenSavingArticle_thenSavesArticle() {
        // Given
        UserAccountDto dto = createUserAccountDto();

        given(userAccountRepository.save(any(UserAccount.class))).willReturn(createUserAccount());

        // When
        sut.saveUserAccount(dto);

        // Then
        then(userAccountRepository).should().save(any(UserAccount.class));
    }

    private UserAccount createUserAccount() {
        UserAccount userAccount = UserAccount.of(
                "id",
                "pw",
                "email@email.com",
                "nickname",
                "memo"
        );
        ReflectionTestUtils.setField(userAccount, "userId", null);

        return userAccount;
    }

    private UserAccountDto createUserAccountDto() {return createUserAccountDto("testId","testPw","testNick", "test@Test.com","testMemo");}

    private UserAccountDto createUserAccountDto(String userId, String userPassword, String nickname, String email, String memo){
        return UserAccountDto.of(
                userId,
                userPassword,
                email,
                nickname,
                memo,
                LocalDateTime.now(),
                "Uno",
                LocalDateTime.now(),
                "Uno"

        );
    }

}