package com.example.demo.controller;

import java.util.Map;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import com.example.demo.repository.CacheRepository;
import com.example.demo.domain.Cache;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.CacheEvict;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class CacheController {

    @Autowired
    private CacheRepository cacheRepository;

    @RequestMapping("/ping")
    public String index() {
        return "pong";
    }

    @RequestMapping(value = "/caches/{cachekey}", method = RequestMethod.GET)
    @ResponseBody
    @Cacheable(value="demoCache")
    public Cache find(@PathVariable("cachekey") String cachekey) {
        log.info("Query the database by cachekey = " + cachekey);
        return cacheRepository.findByCachekey(cachekey);
    }

    @RequestMapping(value = "/caches", method = RequestMethod.POST)
    @ResponseBody
    @CachePut(value="demoCache", key="#result.cachekey")
    public Cache save(@RequestBody Cache cache) {
        log.info("Save the data into the database: " + cache.toString());
        return cacheRepository.save(cache);
    }

    @RequestMapping(value = "caches/{cachekey}", method = RequestMethod.PUT)
    @CachePut(value="demoCache", key="#result.cachekey")
    public Cache update(@PathVariable("cachekey") String cachekey, @RequestBody Cache cache) {
        cache.setCachekey(cachekey);
        return cacheRepository.save(cache);
    }

    @RequestMapping(value = "caches/{cachekey}", method = RequestMethod.DELETE)
    @CacheEvict(value="demoCache")
    public String delete(@PathVariable("cachekey") String cachekey) {
        return cacheRepository.removeByCachekey(cachekey);
    }
}
