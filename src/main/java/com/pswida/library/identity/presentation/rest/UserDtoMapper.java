package com.pswida.library.identity.presentation.rest;

import com.pswida.library.identity.domain.UserSnapshot;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
interface UserDtoMapper {
  @Mapping(target = "id", expression = "java(userSnapshot.id().id())")
  UserDto mapUserSnapshotToUserDto(UserSnapshot userSnapshot);
}
