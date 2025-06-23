package mk.ukim.finki.emt.service.domain;

import mk.ukim.finki.emt.dto.responseDto.ResponseUserDto;
import mk.ukim.finki.emt.model.domain.User;
import mk.ukim.finki.emt.model.enumerations.Role;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface UserService extends UserDetailsService {

    List<User> findAll();

    User register(String username, String password, String repeatPassword, String name, String surname, Role role);

    User login(String username, String password);

    Optional<User> findByUsername(String username);
}
