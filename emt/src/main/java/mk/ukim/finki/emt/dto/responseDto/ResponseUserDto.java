package mk.ukim.finki.emt.dto.responseDto;

import mk.ukim.finki.emt.model.domain.User;
import mk.ukim.finki.emt.model.enumerations.Role;

public record ResponseUserDto(String username, String name, String surname, Role role) {

    public static ResponseUserDto from(User user) {
        return new ResponseUserDto(
                user.getUsername(),
                user.getName(),
                user.getSurname(),
                user.getRole()
        );
    }

    public User toUser() {
        return new User(username, name, surname, role);
    }
}

