package com.techforb.Techforb.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     Long id;

    @NotBlank
    @Size(min=2, max=100)
    private String fullname;

    @NotBlank
    @Email
    @Column(unique=true)
    private String email;

    @NotBlank
    @Size(min=6, max=100)
    private String password;

    @NotNull
    private TypeDocumentEnum typeDocument;

    @NotBlank
    @Column(unique=true)
    private String numberDocument;

    private String imageUrl;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "owner", cascade = CascadeType.ALL)
    public List<Card> cards = new ArrayList<>();

    public void addCard(Card card) {
        if (cards == null) {
            cards = new ArrayList<>();
        }
        cards.add(card);
        card.setOwner(this);
    }

    @NotNull
    private Role role;

    @PastOrPresent
    private LocalDateTime createdAt;

    @PastOrPresent
    private LocalDateTime updatedAt;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return email;
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
