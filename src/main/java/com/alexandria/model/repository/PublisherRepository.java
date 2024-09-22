package com.alexandria.model.repository;

import com.alexandria.model.entity.*;
import java.util.*;
import org.springframework.data.jpa.repository.*;

public interface PublisherRepository extends JpaRepository<Publisher, UUID> {

  Optional<Publisher> findByName(String name);
}
