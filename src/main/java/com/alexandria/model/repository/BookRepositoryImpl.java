package com.alexandria.model.repository;

import com.alexandria.model.entity.*;
import jakarta.persistence.*;
import java.util.*;

public class BookRepositoryImpl implements CustomBookRepository {

  @PersistenceContext
  private EntityManager entityManager;


  @Override
  public List<Book> findBooksByAuthor(String authorName) {
    TypedQuery<Book> query = entityManager.createQuery(
        "SELECT b FROM Book b WHERE  b.authorName = :authorName", Book.class);

    query.setParameter("authorName", authorName);
    return query.getResultList();
  }

  @Override
  public List<Book> findBooksByTitle(String title) {
    TypedQuery<Book> query = entityManager.createQuery(
        "SELECT b FROM Book b WHERE  b.title = :title", Book.class);
    query.setParameter("title", title);
    return query.getResultList();
  }

  @Override
  public List<Book> findBooksByPublishedDate(Date publishedDate) {
    TypedQuery<Book> query = entityManager.createQuery(
        "SELECT b FROM Book b WHERE  b.publishedDate = :publishedDate", Book.class);
    query.setParameter("publishedDate", publishedDate);
    return query.getResultList();
  }
}
