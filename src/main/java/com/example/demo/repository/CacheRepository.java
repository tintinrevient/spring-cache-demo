package com.example.demo.repository;

import com.example.demo.domain.Cache;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CacheRepository extends JpaRepository<Cache, Long> {

}
