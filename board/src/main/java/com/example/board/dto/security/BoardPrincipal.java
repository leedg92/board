package com.example.board.dto.security;

import com.example.board.dto.UserAccountDto;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public record BoardPrincipal(
        String username,
        String password,
        String email,
        String nickname,
        String memo,
        Collection<? extends GrantedAuthority> authorities,
//        String AUTHORITY,
        Map<String,Object> oAuth2Attribute
) implements UserDetails, OAuth2User {

    public static BoardPrincipal of(String username, String password, String email, String nickname, String memo) {

        return of(
                username,
                password,
                email,
                nickname,
                memo,
                null
        );
    }
    public static BoardPrincipal of(String username, String password, String email, String nickname, String memo, Map<String,Object> oAuth2Attribute) {
        Set<RoleType> roleTypes = Set.of(RoleType.USER);

        return new BoardPrincipal(
                username,
                password,
                email,
                nickname,
                memo,
                roleTypes.stream()
                        .map(RoleType::getName)
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toUnmodifiableSet()),
                oAuth2Attribute
        );
    }

    public static BoardPrincipal from(UserAccountDto dto){
        return BoardPrincipal.of(
                dto.userId(),
                dto.userPassword(),
                dto.email(),
                dto.nickname(),
                dto.memo()
        );
    }

    public UserAccountDto toDto(){
        return UserAccountDto.of(
                username,
                password,
                email,
                nickname,
                memo
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
//        ArrayList<GrantedAuthority> auth = new ArrayList<GrantedAuthority>();
//        auth.add(new SimpleGrantedAuthority(AUTHORITY));
//        return auth;  TODO: 권한 기능 추가하기
        return authorities;
    }

    @Override
    public String getPassword() {return password;}
    @Override
    public String getUsername() {return username;}

    @Override
    public boolean isAccountNonExpired() {return true;}
    @Override
    public boolean isAccountNonLocked() {return true;}
    @Override
    public boolean isCredentialsNonExpired() {return true;}
    @Override
    public boolean isEnabled() {return true;}


    @Override
    public Map<String, Object> getAttributes() {return oAuth2Attribute;}
    @Override
    public String getName() {return username;}

    public enum RoleType{
        USER("ROLE_TYPE");

        @Getter
        private final String name;

        RoleType(String name) {
            this.name = name;
        }
    }
}
