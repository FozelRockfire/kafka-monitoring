package com.t1study.metricsconsumer.repository;

import com.t1study.metricsconsumer.model.MetricName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MetricNameRepository extends JpaRepository<MetricName, Long> {

    MetricName findMetricNameByName(String name);

}
