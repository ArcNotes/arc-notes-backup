package com.arcnotes.service;

import io.vavr.control.Option;

public interface BaseService<T> {

    T save(T entity);
    Option<T> findById(Long id);
}
