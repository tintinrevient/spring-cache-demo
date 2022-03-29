package com.example.demo.controller;

import org.springframework.cache.CacheManager;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.demo.repository.FeatureRepository;
import com.example.demo.domain.Feature;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.CacheEvict;
import lombok.extern.slf4j.Slf4j;
import java.util.Optional;

@Slf4j
@RestController
public class FeatureController {

    @Autowired
    private FeatureRepository featureRepository;

    @Autowired
    private CacheManager cacheManager;

    @RequestMapping("/ping")
    public String index() {
        return "pong";
    }

    @RequestMapping("/caches/{cacheKey}")
    public Optional<Feature> caches(@PathVariable("cacheKey") String cacheKey) {
        try {
            log.info("Retrieve the feature value from the cache by the given feature key {}", cacheKey);
            return Optional.ofNullable((Feature) cacheManager.getCache("featureCache").get(cacheKey).get());
        } catch (NullPointerException ex) {
            log.warn("{} does not exist in the cache", cacheKey);
            return Optional.empty();
        }
    }

    @RequestMapping(value = "/features/{featureKey}", method = RequestMethod.GET)
    @ResponseBody
    @Cacheable(value="featureCache", key="#featureKey")
    public Feature find(@PathVariable("featureKey") String featureKey) {
        log.info("Query the database by feature key: " + featureKey);
        return featureRepository.findByFeatureKey(featureKey);
    }

    @RequestMapping(value = "/features", method = RequestMethod.POST)
    @ResponseBody
    @CachePut(value="featureCache", key="#feature.featureKey")
    public Feature save(@RequestBody Feature feature) {
        log.info("Save the feature into the database: " + feature.toString());
        return featureRepository.save(feature);
    }

    @RequestMapping(value = "features/{featureKey}", method = RequestMethod.PUT)
    @CachePut(value="featureCache", key="#featureKey")
    public Feature update(@PathVariable("featureKey") String featureKey, @RequestBody Feature feature) {
        feature.setFeatureKey(featureKey);
        return featureRepository.save(feature);
    }

    @RequestMapping(value = "features/{featureKey}", method = RequestMethod.DELETE)
    @CacheEvict(value="featureCache", key="#featureKey")
    public String delete(@PathVariable("featureKey") String featureKey) {
        return featureRepository.removeByFeatureKey(featureKey);
    }
}
