package mk.ukim.finki.emt.service.application;

import mk.ukim.finki.emt.dto.requestDto.LoginRequestHostDto;
import mk.ukim.finki.emt.dto.responseDto.ResponseHostDto;
import mk.ukim.finki.emt.dto.requestDto.RequestHostDto;

import java.util.List;
import java.util.Optional;

public interface HostApplicationService {

    List<ResponseHostDto> findAll();

    Optional<ResponseHostDto> findByUsername(String username);

    Optional<ResponseHostDto> update(String id, RequestHostDto host);

    void deleteById(String username);

    Optional<ResponseHostDto> register(RequestHostDto host);

    Optional<ResponseHostDto> login(LoginRequestHostDto loginHostDto);
}
