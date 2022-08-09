package com.xrest.emanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface BaseRepository<E,P> extends JpaRepository<E,P> , JpaSpecificationExecutor<E> {
}
