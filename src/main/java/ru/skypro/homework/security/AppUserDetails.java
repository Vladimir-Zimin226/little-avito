package ru.skypro.homework.security;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.skypro.homework.entity.Role;
import ru.skypro.homework.entity.User;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Data
@NoArgsConstructor
public class AppUserDetails implements UserDetails {

    private Integer id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String phone;
    private Role role;

    public AppUserDetails(User user) {
        this.id = user.getId();
        this.username = user.getEmail();
        this.password = user.getPassword();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.phone = user.getPhone();
        this.role = user.getRole();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Optional.ofNullable(role)
                .map(role -> "ROLE_" + role)
                .map(SimpleGrantedAuthority::new)
                .map(List::of)
                .orElse(Collections.emptyList());
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
