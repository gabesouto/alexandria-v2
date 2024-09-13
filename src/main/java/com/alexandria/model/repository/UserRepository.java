package com.alexandria.model.repository;

import com.alexandria.model.entity.User;
import java.util.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.*;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

  UserDetails findByEmail(String login);
}
