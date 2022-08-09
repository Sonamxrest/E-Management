package com.xrest.emanagement.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;

import javax.transaction.Transactional;
import java.util.List;

public interface BaseService <E,P>{

 E findOne(P id);

 List<E> findAll();

 Page<E> findAll(Object specs, Pageable pageable);

 E save(E object);

 @Modifying
 @Transactional
 void delete(P id);

 List<E> saveAll(List<E> all);

}
