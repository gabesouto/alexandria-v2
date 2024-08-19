package com.alexandria.model.repository;

import com.alexandria.model.entity.*;
import java.util.*;
import org.springframework.data.jpa.repository.*;

public interface BookRepository extends JpaRepository<Book, UUID>, CustomBookRepository {

}
