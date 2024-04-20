package com.t1study.metricsconsumer.mapper;

public interface Mappable<E, D> {

    D toDTO(E entity);
}
