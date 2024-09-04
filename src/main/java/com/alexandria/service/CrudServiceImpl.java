package com.alexandria.service;

import com.alexandria.model.entity.*;
import java.util.*;
import org.modelmapper.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.*;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
public abstract class CrudServiceImpl<L extends JpaRepository<T, ID>, T extends BaseEntity<ID>, ID, DTO>
    implements CrudService<DTO, T, ID> {

  @Autowired
  protected ModelMapper modelMapper;
  protected L repository;

  @Autowired
  public CrudServiceImpl(L repository) {
    this.repository = repository;
    this.modelMapper = new ModelMapper();
  }

  @Override
  public List<T> findAll() {
    return repository.findAll();
  }

  @Override
  public Page<T> findAll(Pageable pageable) {
    return repository.findAll(pageable);
  }

  @Override
  public List<T> findAllById(List<ID> ids) {
    return repository.findAllById(ids);
  }

  @Override
  public Optional<T> findById(ID id) {
    return repository.findById(id);
  }

  @Override
  public T createElement(T element) {
    return repository.save(element);
  }

  @Override
  public List<T> createElements(List<T> elements) {
    return repository.saveAll(elements);
  }

  @Override
  public Optional<T> updateElement(ID id, T element) {
    if (repository.existsById(id)) {
      T updatedEntity = repository.save(element);
      return Optional.of(updatedEntity);
    }
    return Optional.empty();
  }

  @Override
  public boolean deleteElement(ID id) {
    if (repository.existsById(id)) {
      repository.deleteById(id);
      return true;
    }
    return false;
  }

  @Override
  public boolean deleteElement(T element) {
    if (repository.existsById(element.getId())) {
      repository.delete(element);
      return true;
    }
    return false;
  }

  @Override
  public void deleteAll() {
    repository.deleteAll();
  }

  @Override
  public long count() {
    return repository.count();
  }

  // Abstract methods for DTO conversion, to be implemented by subclasses
  @Override
  public abstract List<DTO> convertToListDto(List<T> elements);

  @Override
  public abstract DTO convertToDetailDto(T element);

  @Override
  public abstract T convertToModel(DTO dto);
}
