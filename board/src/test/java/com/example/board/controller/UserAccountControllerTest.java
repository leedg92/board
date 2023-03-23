package com.example.board.controller;

import com.example.board.domain.constant.FormStatus;
import com.example.board.dto.UserAccountDto;
import com.example.board.dto.request.UserAccountRequest;
import com.example.board.service.UserAccountService;
import com.example.board.util.FormDataEncoder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.then;
import static org.mockito.BDDMockito.willDoNothing;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@DisplayName("View 컨트롤러 - 회원가입")
@Import(FormDataEncoder.class)
@WebMvcTest(UserAccountController.class)
class UserAccountControllerTest {

    private final MockMvc mvc;

    private final FormDataEncoder formDataEncoder;

    @MockBean private UserAccountService userAccountService;


    UserAccountControllerTest(
            @Autowired MockMvc mvc,
            @Autowired FormDataEncoder formDataEncoder
    ) {
        this.mvc = mvc;
        this.formDataEncoder = formDataEncoder;
    }

    @WithMockUser
    @DisplayName("[view][GET] 새 회원정보 작성 페이지")
    @Test
    void givenNothing_whenRequesting_thenReturnsNewUserJoinPage() throws Exception {
        // Given

        // When & Then
        mvc.perform(get("/users/userForm"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(view().name("articles/userForm"));
//                .andExpect(model().attribute("formStatus", FormStatus.ACREATE));
    }

    @WithMockUser
    @DisplayName("[view][POST] 새 회원 등록 - 정상 호출")
    @Test
    void givenUserInfo_whenRequesting_thenReturnsNewUserJoinPage() throws Exception {
        // Given
//        UserAccountRequest userAccountRequest = UserAccountRequest.of("newId","newPw","newNick","newEmail", "newMemo");
        UserAccountDto userAccountDto = UserAccountDto.of("newId","newPw","newNick","newEmail", "newMemo");
        willDoNothing().given(userAccountService).saveUserAccount(any(UserAccountDto.class));

        // When & Then
        mvc.perform(
                        post("/users/userForm")
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                .content(formDataEncoder.encode(userAccountDto))
                                .with(csrf())
                )
//                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/articles"))
                .andExpect(redirectedUrl("/articles"));
        then(userAccountService).should().saveUserAccount(any(UserAccountDto.class));
    }
}