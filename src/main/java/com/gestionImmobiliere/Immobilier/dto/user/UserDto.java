package com.gestionImmobiliere.Immobilier.dto.user;


import com.gestionImmobiliere.Immobilier.enumeration.Genre;
import com.gestionImmobiliere.Immobilier.model.Organisme;
import com.gestionImmobiliere.Immobilier.model.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
public class UserDto {
    private long idUser;
    private String email;
    private String firstName;
    private String username;
    private String tel;
    private String address;
    private String lastName;
    private String password;
    private String slug;
    private String avatar;
    private String organisme;
    private Genre gender;
    private LocalDateTime createdAt;
    private long idOrganisme = 1;
    private String slugOrganisme;

    public User create(UserDto userDto, Organisme organisme){
        User user = new User();

        user.setEmail(userDto.getEmail());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setSlugOrganisme(userDto.getSlugOrganisme());
        user.setEmail(userDto.getEmail());
        user.setOrganisme(organisme);

        return user;
    }


    public User login(UserDto userDto){
        PasswordEncoder passwordEncoder = null;
        
        User user = new User();
        user.setEmail(userDto.getEmail());

        return user;
    }

    public User update(UserDto userDto) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof User) {
            User user = (User) principal;

            // Mise à jour des champs
            user.setFirstName(userDto.getFirstName());
            user.setLastName(userDto.getLastName());
            user.setAddress(userDto.getAddress());
            user.setTel(userDto.getTel());
            user.setGender(userDto.getGender());

            return user;
        } else {
            throw new RuntimeException("Unknown principal type: " + principal.getClass());
        }
    }


    public User enableUnableStatus(User user){
        //user.setRole(user.());

        return user;
    }

}
