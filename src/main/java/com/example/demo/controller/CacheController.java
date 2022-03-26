package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import com.example.demo.repository.CacheRepository;
import com.example.demo.domain.Cache;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.CacheEvict;

@RestController
@RequestMapping("/cache")
public class CacheController {

    Logger logger = LoggerFactory.getLogger(CacheController.class);

    @Autowired
    private CacheRepository cacheRepository;

    @GetMapping(value = "/{id}")
    @Cacheable(value="demoCache", key="#result.id")
    public Cache find(@PathVariable("id") Long id) {
        logger.info("Query the cache by its id " + id);
        return cacheRepository.findById(id).get();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @CachePut(value="demoCache", key="#result.id")
    public void save(@RequestBody Cache cache) {
        cacheRepository.save(cache);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @CachePut(value="demoCache", key="#result.id")
    public void update(@PathVariable("id") Long id, @RequestBody Cache cache) {
        cache.setId(id);
        cacheRepository.save(cache);
    }

    @DeleteMapping(value = "/{id}")
    @CacheEvict(value="demoCache")
    public void delete(@PathVariable("id") Long id) {
        cacheRepository.deleteById(id);
    }
}
