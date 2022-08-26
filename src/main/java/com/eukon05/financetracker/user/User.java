package com.eukon05.financetracker.user;

import com.eukon05.financetracker.token.Token;
import com.eukon05.financetracker.wallet.Wallet;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

@Data
@Entity
@Table(name = "APP_USER")
@NoArgsConstructor
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String username;

    private String password;

    private String name;

    private String surname;

    private String email;

    public User(String username, String password, String name, String surname) {
        setUsername(username);
        setPassword(password);
        setName(name);
        setSurname(surname);
    }

    private boolean enabled = false;
    private boolean locked = false;

    @Enumerated(EnumType.STRING)
    @ElementCollection
    private final Set<RoleType> roles = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private final List<Token> tokens = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private final List<Wallet> wallets = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.toString())).collect(Collectors.toSet());
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

}
