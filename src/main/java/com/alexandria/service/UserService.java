package com.alexandria.service;

import com.alexandria.dto.*;
import com.alexandria.model.entity.*;
import com.alexandria.model.repository.*;
import java.util.*;
import java.util.stream.*;
import org.modelmapper.*;
import org.springframework.stereotype.*;

@Service
public class UserService extends CrudServiceImpl<UserRepository, User, UUID, UserDto> {


  public UserService(UserRepository repository, ModelMapper modelMapper) {
    super(repository, modelMapper);
  }

  public List<UserDto> getUsers() {
    List<User> users = findAll();
    return convertToListDto(users);

  }

  public UserDto addUser(UserCreationDto payload) {
    User newUser = convertTo(payload, User.class);
    createElement(newUser);
    return convertTo(newUser, UserDto.class);
  }


  public UserDto updateUser(UserDto payload) {

    return convertTo(updateElement(payload.getId(), payload), UserDto.class);
  }

  public void deleteUser(UUID id) {
    deleteElement(id);
  }

  @Override
  public List<UserDto> convertToListDto(List<User> users) {
    return users.stream().map(user -> modelMapper.map(user, UserDto.class))
        .collect(Collectors.toList());
  }

  @Override
  public UserDto convertToDetailDto(User user) {
    return modelMapper.map(user, UserDto.class);
  }


}
