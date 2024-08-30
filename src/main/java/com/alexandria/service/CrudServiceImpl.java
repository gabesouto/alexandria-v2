package com.alexandria.service;

import com.alexandria.model.entity.*;
import java.util.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.*;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
public class CrudServiceImpl<L extends JpaRepository<T, ID>, T extends BaseEntity<ID>, ID>
    implements CrudService<T, ID> {

  @Autowired
  protected L repository;

  @Override
  public List<T> findAll() {
    return repository.findAll();
  }

  @Override
  public Page<T> findAll(Pageable pageable) {
    return null;
  }

  @Override
  public List<T> findAllById(List list) {
    return List.of();
  }

  @Override
  public Optional<T> findById(Object o) {
    return Optional.empty();
  }

  @Override
  public boolean deleteElement(Object o) {
    return false;
  }

  @Override
  public void deleteAll() {

  }

  @Override
  public long count() {
    return 0;
  }

  @Override
  public boolean deleteElement(BaseEntity element) {
    return false;
  }

  @Override
  public Optional<T> updateElement(Object o, BaseEntity element) {
    return Optional.empty();
  }

  @Override
  public List<T> createElements(List elements) {
    return List.of();
  }

  @Override
  public T createElement(BaseEntity element) {
    return null;
  }
}
