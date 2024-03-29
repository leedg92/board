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
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequiredArgsConstructor
@RequestMapping("/users")
@Controller
public class UserAccountController {

    private final UserAccountService userAccountService;

    @GetMapping("/userForm/th")
    public String userJoinForm(ModelMap map) {
//        map.addAttribute("formStatus", FormStatus.ACREATE);

        return "articles/userForm";
    }

    @PostMapping("/userForm/th")
    public String postUserJoin(UserAccountDto dto,
                               ModelMap map
    ) {
        System.out.println("controller 진입?");
        System.out.println(dto);
        System.out.println(userAccountService.searchUser(dto.userId()));


        BoardPrincipal.from(userAccountService.saveUser(dto.userId(),dto.userPassword(),dto.email(), dto.nickname(),dto.memo()));
//        System.out.println(result);
//        map.addAttribute("joinResult", result);
        //TODO : 화면단에 alert 띄우는 것 까지 나중에 구현하기(안해도되나..?)

        return "redirect:/articles/th";
    }

    @GetMapping("/isExistedUser")
    @ResponseBody
    public boolean searchUser(
            @RequestParam("paramId") String paramId
    ){
        System.out.println("paramId :: " + paramId);
        Optional<UserAccountDto> dupCheckResult = userAccountService.searchUser(paramId);
        System.out.println("dupCheckResult :: " + dupCheckResult);
        return dupCheckResult.isPresent() ? true : false;
    }


    @GetMapping("/userForm/jsp")
    public String jspUserJoinForm(ModelMap map) {

        return "userForm";
    }

    @PostMapping("/userForm/jsp")
    public String jspPostUserJoin(UserAccountDto dto,
                               ModelMap map
    ) {
        System.out.println("controller 진입?");
        System.out.println(dto);
        System.out.println(userAccountService.searchUser(dto.userId()));


        BoardPrincipal.from(userAccountService.saveUser(dto.userId(),dto.userPassword(),dto.email(), dto.nickname(),dto.memo()));

        return "redirect:/articles/jsp";
    }
}
