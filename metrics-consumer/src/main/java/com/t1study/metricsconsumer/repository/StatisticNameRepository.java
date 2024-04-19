package com.t1study.metricsconsumer.repository;

import com.t1study.metricsconsumer.model.StatisticName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatisticNameRepository extends JpaRepository<StatisticName, Long> {

    StatisticName findStatisticNameByName(String name);
}




