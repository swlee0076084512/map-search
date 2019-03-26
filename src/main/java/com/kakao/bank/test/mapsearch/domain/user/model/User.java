package com.kakao.bank.test.mapsearch.domain.user.model;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Entity
@Table(name="users")
@Data
public class User implements UserDetails, Serializable {

    private static final long serialVersionUID = 329201432055640299L;

    public User(){}

    public User(String username, String password, String role){
        this.username = username;
        this.password = password;
        this.roles.add(role);
    }

    public User(String username, String password,  Collection<? extends GrantedAuthority> authorities){
        this.username = username;
        this.password = password;
        authorities.stream().forEach(a -> roles.add(a.getAuthority()));
    }

    public User(String username, Collection<? extends GrantedAuthority> authorities){
        this.username = username;
        authorities.stream().forEach(a -> roles.add(a.getAuthority()));
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    @NotEmpty
    private String username;
    @NotEmpty
    private String password;
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles = new ArrayList<>();
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream().map(SimpleGrantedAuthority::new).collect(toList());
    }
    @Override
    public String getPassword() {
        return this.password;
    }
    @Override
    public String getUsername() {
        return this.username;
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