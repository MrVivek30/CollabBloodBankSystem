package com.trackobit.repository;


import com.trackobit.model.Request;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RequestRepository extends JpaRepository<Request, Integer> {
    public Optional<Request> findByEmail(String email);
    public List<Request> findByType(String type);
    public Optional<Request> findByUniqueId(int uniqueId);
}

