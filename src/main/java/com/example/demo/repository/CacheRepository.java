package com.example.demo.repository;

import com.example.demo.domain.Cache;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface CacheRepository extends JpaRepository<Cache, Long> {
    Cache findByCachekey(String cachekey);
    @Transactional
    String removeByCachekey(String cachekey);
}
