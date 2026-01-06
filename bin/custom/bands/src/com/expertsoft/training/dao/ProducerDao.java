package com.expertsoft.training.dao;

import com.expertsoft.training.model.ProducerModel;

import java.util.List;

public interface ProducerDao {
    List<ProducerModel> findProducersByCode(String code);
}
