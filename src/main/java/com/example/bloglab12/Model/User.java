package com.example.bloglab12.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty(message = "username should not be empty")
    @Size(min = 3, max = 10 ,message = "username should range between 3 to 10 characters")
    @Column(columnDefinition = "VARCHAR(10) NOT NULL UNIQUE")
    private String username;
    @NotEmpty(message = "password should not be empty")
    @Size(min = 8, message = "password should be at least 8 characters")
    @Column(columnDefinition = "VARCHAR(100) not null")
    private String password;
    @Column(columnDefinition = "VARCHAR(8) NOT NULL CHECK(role= 'USER' or role='ADMIN')")
    private String role;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Set<Blog> blogs;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(this.role));
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
