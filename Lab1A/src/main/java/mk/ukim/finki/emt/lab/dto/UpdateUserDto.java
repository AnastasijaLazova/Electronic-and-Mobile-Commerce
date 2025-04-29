package mk.ukim.finki.emt.lab.dto;

import mk.ukim.finki.emt.lab.model.domain.User;
import mk.ukim.finki.emt.lab.model.enumerations.Role;

public record UpdateUserDto(String username, String name, String surname, Role role) {

    public static UpdateUserDto from(User user) {
        return new UpdateUserDto(
                user.getUsername(),
                user.getName(),
                user.getSurname(),
                user.getRole()
        );
    }

    public User toUser() {
        return new User(username, name, surname, role.name());
    }
}

