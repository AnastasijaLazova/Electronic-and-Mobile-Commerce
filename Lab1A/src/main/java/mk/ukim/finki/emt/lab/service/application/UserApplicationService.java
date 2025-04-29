package mk.ukim.finki.emt.lab.service.application;


import mk.ukim.finki.emt.lab.dto.CreateUserDto;
import mk.ukim.finki.emt.lab.dto.LoginDto;
import mk.ukim.finki.emt.lab.dto.UpdateUserDto;

import java.util.Optional;

public interface UserApplicationService {

    Optional<UpdateUserDto> register(CreateUserDto createUserDto);

    Optional<UpdateUserDto> login(LoginDto loginUserDto);

    Optional<UpdateUserDto> findByUsername(String username);
}
