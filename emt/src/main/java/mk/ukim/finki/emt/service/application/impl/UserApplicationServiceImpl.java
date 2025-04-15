package mk.ukim.finki.emt.service.application.impl;

import mk.ukim.finki.emt.dto.requestDto.LoginUserDto;
import mk.ukim.finki.emt.dto.requestDto.RequestUserDto;
import mk.ukim.finki.emt.dto.responseDto.ResponseUserDto;
import mk.ukim.finki.emt.model.domain.User;
import mk.ukim.finki.emt.model.exceptions.InvalidArgumentsException;
import mk.ukim.finki.emt.service.application.UserApplicationService;
import mk.ukim.finki.emt.service.domain.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserApplicationServiceImpl implements UserApplicationService {

    private final UserService userService;

    public UserApplicationServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Optional<ResponseUserDto> register(RequestUserDto createUserDto) {
        User user = userService.register(
                createUserDto.username(),
                createUserDto.password(),
                createUserDto.repeatPassword(),
                createUserDto.name(),
                createUserDto.surname(),
                createUserDto.role()
        );
        return Optional.of(ResponseUserDto.from(user));
    }

    @Override
    public Optional<ResponseUserDto> login(LoginUserDto loginUserDto) {
        return Optional.of(ResponseUserDto.from(userService.login(
                loginUserDto.username(),
                loginUserDto.password()
        )));
    }

    @Override
    public Optional<ResponseUserDto> findByUsername(String username) {
        return Optional.of(ResponseUserDto.from(userService.findByUsername(username).orElseThrow(InvalidArgumentsException::new)));
    }
}

