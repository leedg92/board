package com.example.board.service;

import com.example.board.domain.UserAccount;
import com.example.board.dto.UserAccountDto;
import com.example.board.repository.UserAccountRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
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
    void givenUserInfo_whenSavingUser_thenSavesUser() {


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

// 여기서부터 강의 내용

    @DisplayName("존재하는 회원 ID를 검색하면, 회원 데이터를 Optional로 반환한다.")
    @Test
    void givenExistentUserId_whenSearching_thenReturnsOptionalUserData() {
        // Given
        String username = "uno";
        given(userAccountRepository.findById(username)).willReturn(Optional.of(createUserAccount(username)));

        // When
        Optional<UserAccountDto> result = sut.searchUser(username);

        // Then
        assertThat(result).isPresent();
        then(userAccountRepository).should().findById(username);
    }

    @DisplayName("존재하지 않는 회원 ID를 검색하면, 비어있는 Optional을 반환한다.")
    @Test
    void givenNonexistentUserId_whenSearching_thenReturnsOptionalUserData() {
        // Given
        String username = "wrong-user";
        given(userAccountRepository.findById(username)).willReturn(Optional.empty());

        // When
        Optional<UserAccountDto> result = sut.searchUser(username);

        // Then
        assertThat(result).isEmpty();
        then(userAccountRepository).should().findById(username);
    }

    @DisplayName("회원 정보를 입력하면, 새로운 회원 정보를 저장하여 가입시키고 해당 회원 데이터를 리턴한다.")
    @Test
    void givenUserParams_whenSaving_thenSavesUserAccount() {
        // Given
        UserAccount userAccount = createUserAccount("uno");
        UserAccount savedUserAccount = createSigningUpUserAccount("uno");
        given(userAccountRepository.save(userAccount)).willReturn(savedUserAccount);

        // When
        UserAccountDto result = sut.saveUser(
                userAccount.getUserId(),
                userAccount.getUserPassword(),
                userAccount.getEmail(),
                userAccount.getNickname(),
                userAccount.getMemo()
        );

        // Then
        assertThat(result)
                .hasFieldOrPropertyWithValue("userId", userAccount.getUserId())
                .hasFieldOrPropertyWithValue("userPassword", userAccount.getUserPassword())
                .hasFieldOrPropertyWithValue("email", userAccount.getEmail())
                .hasFieldOrPropertyWithValue("nickname", userAccount.getNickname())
                .hasFieldOrPropertyWithValue("memo", userAccount.getMemo())
                .hasFieldOrPropertyWithValue("createdBy", userAccount.getUserId())
                .hasFieldOrPropertyWithValue("modifiedBy", userAccount.getUserId());
        then(userAccountRepository).should().save(userAccount);
    }


    private UserAccount createUserAccount(String username) {
        return createUserAccount(username, null);
    }

    private UserAccount createSigningUpUserAccount(String username) {
        return createUserAccount(username, username);
    }

    private UserAccount createUserAccount(String username, String createdBy) {
        return UserAccount.of(
                username,
                "password",
                "e@mail.com",
                "nickname",
                "memo",
                createdBy
        );
    }

}