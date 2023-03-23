package com.example.board.controller;

import com.example.board.domain.constant.FormStatus;
import com.example.board.dto.UserAccountDto;
import com.example.board.dto.request.ArticleRequest;
import com.example.board.dto.request.UserAccountRequest;
import com.example.board.dto.security.BoardPrincipal;
import com.example.board.service.UserAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@RequestMapping("/users")
@Controller
public class UserAccountController {

    private final UserAccountService userAccountService;

    @GetMapping("/userForm")
    public String userJoinForm(ModelMap map) {
//        map.addAttribute("formStatus", FormStatus.ACREATE);

        return "articles/userForm";
    }

    @PostMapping("/userForm")
    public String postUserJoin(UserAccountDto dto
    ) {
        System.out.println("controller 진입?");
        System.out.println(dto);


        userAccountService.saveUserAccount(dto);

        return "redirect:/articles";
    }
}
