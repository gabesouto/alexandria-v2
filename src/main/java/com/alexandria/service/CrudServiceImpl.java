package com.alexandria.service;

import com.alexandria.model.entity.*;
import jakarta.persistence.*;
import java.util.*;
import org.modelmapper.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.*;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
public abstract class CrudServiceImpl<L extends JpaRepository<T, ID>, T extends BaseEntity<ID>, ID, DTO>
    implements CrudService<DTO, T, ID> {

  protected final ModelMapper modelMapper;
  protected L repository;

  @Autowired
  public CrudServiceImpl(L repository, ModelMapper modelMapper) {
    this.repository = repository;
    this.modelMapper = modelMapper;
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
  public Optional<T> updateElement(ID id, DTO element) {

    T existingEntity = repository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Entity not found"));

    modelMapper.map(element, existingEntity);

    return Optional.of(repository.save(existingEntity));
  }

  @Override
  public boolean deleteElement(ID id) {
    if (repository.existsById(id)) {
      repository.deleteById(id);
      return true;
    }
    throw new EntityNotFoundException("Entity not found");
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
  public <S, D> D convertTo(S sourceDto, Class<D> destinationType) {
    return modelMapper.map(sourceDto, destinationType);
  }

}
