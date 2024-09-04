package com.alexandria.service;

import com.alexandria.dto.*;
import com.alexandria.model.entity.*;
import com.alexandria.model.repository.*;
import java.util.*;
import java.util.stream.*;
import org.springframework.stereotype.*;

@Service
public class UserService extends CrudServiceImpl<UserRepository, User, UUID, UserDto> {


  public UserService(UserRepository repository) {
    super(repository);
  }

  public List<UserDto> getUsers() {
    List<User> users = findAll();
    return convertToListDto(users);

  }

  @Override
  public List<UserDto> convertToListDto(List<User> users) {
    return users.stream().map(user -> modelMapper.map(user, UserDto.class))
        .collect(Collectors.toList());
  }

  @Override
  public UserDto convertToDetailDto(User element) {
    return null;
  }

  @Override
  public User convertToModel(UserDto userDto) {
    return null;
  }
}
