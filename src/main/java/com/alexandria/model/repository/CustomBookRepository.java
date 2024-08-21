package com.alexandria.model.repository;

import com.alexandria.model.entity.*;
import java.util.*;

public interface CustomBookRepository {

  List<Book> findBooksByAuthor(String authorName);

  List<Book> findBooksByTitle(String title);

  List<Book> findBooksByPublishedDate(Date publishedDate);

}
