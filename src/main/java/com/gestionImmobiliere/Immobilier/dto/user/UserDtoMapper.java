package com.gestionImmobiliere.Immobilier.dto.user;

import com.gestionImmobiliere.Immobilier.generic.GenericDtoMapper;
import com.gestionImmobiliere.Immobilier.model.Organisme;
import com.gestionImmobiliere.Immobilier.model.User;
import com.gestionImmobiliere.Immobilier.service.OrganismeService;
import com.gestionImmobiliere.Immobilier.service.UserService;
import org.springframework.stereotype.Component;

@Component
public class UserDtoMapper implements GenericDtoMapper<UserDto, User> {
    private final UserService userService;
    private final OrganismeService organismeService;

    public UserDtoMapper(UserService userService, OrganismeService organismeService) {
        this.userService = userService;
        this.organismeService = organismeService;
    }

    @Override
    public UserDto toDto(User User){
        return UserDto.builder()
                .firstName(User.getFirstName())
                .lastName(User.getLastName())
                .email(User.getEmail())
                .address(User.getAddress())
                .avatar(User.getAvatar())
                .idUser(User.getIdUser())
                .gender(User.getGender())
                .createdAt(User.getCreatedAt())
                .build();

    }

    @Override
    public User toEntity(UserDto userDto) {
        if (userDto.getSlug() != null){
            User user = userService.findUniqueWithValueAndAttribut(userDto.getSlug(), "slug");

            return userDto.update(userDto);
        }else {
            Organisme organisme = organismeService.findUniqueById(userDto.getIdOrganisme());
            userDto.setSlugOrganisme(organisme.getSlug());

            return userDto.create(userDto, organisme);
        }
    }
}
