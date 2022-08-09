package com.xrest.emanagement.serviceimpl;

import com.xrest.emanagement.repository.BaseRepository;
import com.xrest.emanagement.service.BaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class BaseServiceImpl<E,P> implements BaseService<E,P> {

    private BaseRepository<E,P> baseRepository;

    public BaseServiceImpl(BaseRepository<E, P> baseRepository) {
        this.baseRepository = baseRepository;
    }

    @Override
    public E findOne(P id) {
        return baseRepository.getOne(id);
    }

    @Override
    public List<E> findAll() {
        return baseRepository.findAll();
    }

    @Override
    public Page<E> findAll(Object specs, Pageable pageable) {
        return null;
    }

    @Override
    public E save(E object) {
        return baseRepository.save(object);
    }

    @Override
    public void delete(P id) {
        baseRepository.deleteById(id);
    }

    @Override
    public List<E> saveAll(List<E> all) {
        return baseRepository.saveAll(all);
    }
}
