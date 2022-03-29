package com.example.demo.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;
import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.Column;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Feature implements Serializable {
    @Id
    private String featureKey;
    private String featureValue;
}
