package com.alexandria.model.repository;

import com.alexandria.model.entity.*;
import java.util.*;
import org.springframework.data.jpa.repository.*;

public interface AuthorRepository extends JpaRepository<Author, UUID> {

}
