package com.pswida.library.identity.presentation.rest;

import com.pswida.library.common.application.cqs.command.CommandDispatcher;
import com.pswida.library.identity.application.ChangeUserEmailCommand;
import com.pswida.library.identity.application.CreateUserCommand;
import com.pswida.library.identity.domain.User;
import com.pswida.library.identity.domain.UserId;
import com.pswida.library.identity.domain.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
class UserController {

  private final CommandDispatcher commandDispatcher;

  private final UserRepository userRepository;

  private final UserDtoMapper userDtoMapper;

  @PostMapping(value = "/", consumes = "application/json")
  ResponseEntity<UserDto> createUser(@RequestBody CreateUserRequest createUserRequest) {
    CreateUserCommand command = new CreateUserCommand(createUserRequest.username(), createUserRequest.email());
    commandDispatcher.dispatch(command);
    User createdUser = userRepository.getUserByUsername(command.username()).orElseThrow();

    return ResponseEntity.ok(userDtoMapper.mapUserSnapshotToUserDto(createdUser.toSnapshot()));
  }

  @PutMapping(value = "/{userId}/email", consumes = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<UserDto> updateUserEmail(@RequestBody ChangeEmailRequest emailRequest, @PathVariable String userId) {
    UserId id = new UserId(userId);
    ChangeUserEmailCommand command = new ChangeUserEmailCommand(id, emailRequest.email());
    commandDispatcher.dispatch(command);
    User updatedUser = userRepository.getUserById(id);

    return ResponseEntity.ok(userDtoMapper.mapUserSnapshotToUserDto(updatedUser.toSnapshot()));
  }
}
