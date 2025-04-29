package mk.ukim.finki.emt.lab.service.application.impl;


import mk.ukim.finki.emt.lab.dto.CreateUserDto;
import mk.ukim.finki.emt.lab.dto.LoginDto;
import mk.ukim.finki.emt.lab.dto.UpdateUserDto;
import mk.ukim.finki.emt.lab.model.domain.User;
import mk.ukim.finki.emt.lab.service.application.UserApplicationService;
import mk.ukim.finki.emt.lab.service.domain.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserApplicationServiceImpl implements UserApplicationService {

    private final UserService userService;

    public UserApplicationServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Optional<UpdateUserDto> register(CreateUserDto createUserDto) {
        User user = userService.register(
                createUserDto.username(),
                createUserDto.password(),
                createUserDto.repeatPassword(),
                createUserDto.name(),
                createUserDto.surname(),
                createUserDto.role()
        );
        return Optional.of(UpdateUserDto.from(user));
    }

    @Override
    public Optional<UpdateUserDto> login(LoginDto loginUserDto) {
        return Optional.of(UpdateUserDto.from(userService.login(
                loginUserDto.username(),
                loginUserDto.password()
        )));
    }

    @Override
    public Optional<UpdateUserDto> findByUsername(String username) {
        return Optional.of(UpdateUserDto.from(userService.findByUsername(username)));
    }
}

