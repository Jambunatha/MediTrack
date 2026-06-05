package com.airtribe.meditrack.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class DataStore<T> {
    private final ConcurrentMap<String, T> record = new ConcurrentHashMap<>();

    public void save(String id, T entity) {
        record.put(id, entity);
    }

    public Optional<T> findById(String id) {
        return Optional.ofNullable(record.get(id));
    }

    public boolean deleteById(String id) {
        return record.remove(id) != null;
    }

    public List<T> findAll() {
        return new ArrayList<>(record.values());
    }
}
