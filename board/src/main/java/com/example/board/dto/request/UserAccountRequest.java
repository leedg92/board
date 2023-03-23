package com.example.board.dto.request;

import com.example.board.dto.UserAccountDto;

import java.io.Serializable;

public record UserAccountRequest(
        String userId,
        String passWord,
        String nickname,
        String email,
        String memo
) implements Serializable {

    public static UserAccountRequest of(String userId, String passWord, String nickname, String email, String memo) {
        return new UserAccountRequest(userId, passWord, nickname, email, memo);
    }

    public UserAccountDto toDto(UserAccountDto dto){
        return UserAccountDto.of(
                userId,
                passWord,
                nickname,
                email,
                memo
        );

    }
}
