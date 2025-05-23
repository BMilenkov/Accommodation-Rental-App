package mk.ukim.finki.emt.service.application;

import mk.ukim.finki.emt.dto.requestDto.LoginUserDto;
import mk.ukim.finki.emt.dto.requestDto.RequestUserDto;
import mk.ukim.finki.emt.dto.responseDto.LoginResponseDto;
import mk.ukim.finki.emt.dto.responseDto.ResponseCountryDto;
import mk.ukim.finki.emt.dto.responseDto.ResponseUserDto;

import java.util.List;
import java.util.Optional;

public interface UserApplicationService {

    List<ResponseUserDto> findAll();

    Optional<ResponseUserDto> register(RequestUserDto createUserDto);

    Optional<LoginResponseDto> login(LoginUserDto loginUserDto);

    Optional<ResponseUserDto> findByUsername(String username);
}
