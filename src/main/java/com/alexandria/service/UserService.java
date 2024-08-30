package com.alexandria.service;

import com.alexandria.model.entity.*;
import com.alexandria.model.repository.*;
import java.util.*;
import org.springframework.stereotype.*;

@Service
public class UserService extends CrudServiceImpl<UserRepository, User, UUID> {


  public List<User> getUsers() {
    return findAll();
  }
}
