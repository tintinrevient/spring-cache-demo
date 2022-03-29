package com.example.demo.repository;

import com.example.demo.domain.Feature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface FeatureRepository extends JpaRepository<Feature, String> {
    Feature findByFeatureKey(String featureKey);
    @Transactional
    String removeByFeatureKey(String featureKey);
}
