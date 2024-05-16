package com.baseball.config;

import com.baseball.domain.entity.UserInfo;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class CustomUserDetail implements UserDetails {
    private final UserInfo userInfo;

    public CustomUserDetail(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
//        System.out.println(this.userInfo.getAuthority());
        Collection<GrantedAuthority> collection = new ArrayList<>();
//        collection.add(new SimpleGrantedAuthority(this.userInfo.getAuthority().name()));
//        collection.add(
//                new GrantedAuthority() {
//                           @Override
//                           public String getAuthority() {
//
//                               System.out.println("========================="+userInfo.getAuthority().name());
//                           return userInfo.getAuthority().name();
//                           }
//                       }
//        );
        return collection;
    }

    @Override
    public String getPassword() {
        return userInfo.getPassword();
    }

    @Override
    public String getUsername() {
        return userInfo.getLoginId();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
