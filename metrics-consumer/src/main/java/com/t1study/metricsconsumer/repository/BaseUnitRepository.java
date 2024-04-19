package com.t1study.metricsconsumer.repository;

import com.t1study.metricsconsumer.model.BaseUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BaseUnitRepository extends JpaRepository<BaseUnit, Long> {

    BaseUnit findBaseUnitNameByName(String name);

}
