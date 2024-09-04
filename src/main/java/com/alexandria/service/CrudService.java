package com.alexandria.service;

import com.alexandria.model.entity.*;
import java.util.*;
import org.springframework.data.domain.*;

public interface CrudService<DTO, T extends BaseEntity<ID>, ID> {

  List<T> findAll();

  Page<T> findAll(Pageable pageable);

  List<T> findAllById(List<ID> ids);

  Optional<T> findById(ID id);

  T createElement(T element);

  List<T> createElements(List<T> elements);

  Optional<T> updateElement(ID id, T element);

  boolean deleteElement(ID id);

  boolean deleteElement(T element);

  void deleteAll();

  long count();

  List<DTO> convertToListDto(List<T> elements);

  DTO convertToDetailDto(T element);

  T convertToModel(DTO dto);
}
