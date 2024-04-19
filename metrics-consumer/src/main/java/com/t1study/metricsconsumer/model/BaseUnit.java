package com.t1study.metricsconsumer.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "t_base_unit_names")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BaseUnit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "baseUnit", cascade = CascadeType.ALL)
    private List<Metric> metrics;
}
