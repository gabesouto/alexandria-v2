package com.alexandria.service;

import com.alexandria.model.entity.*;
import com.alexandria.model.repository.*;
import java.util.*;
import org.modelmapper.*;
import org.springframework.stereotype.*;

@Service
public class PublisherService extends
    CrudServiceImpl<PublisherRepository, Publisher, UUID, Publisher> {

  public PublisherService(PublisherRepository repository, ModelMapper modelMapper) {
    super(repository, modelMapper);
  }

  public Publisher createOrFindPublisher(String publisherName) {
    Optional<Publisher> publisher = repository.findByName(publisherName);

    return publisher.orElseGet(() -> createElement(new Publisher(publisherName)));
  }

  @Override
  public List<Publisher> convertToListDto(List<Publisher> elements) {
    return List.of();
  }

  @Override
  public Publisher convertToDetailDto(Publisher element) {
    return null;
  }
}
