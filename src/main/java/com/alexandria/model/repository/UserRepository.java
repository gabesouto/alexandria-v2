package com.alexandria.model.repository;

import com.alexandria.model.entity.*;
import java.util.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
  // Custom methods can be defined here
}