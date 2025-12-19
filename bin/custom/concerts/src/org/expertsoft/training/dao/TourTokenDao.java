package org.expertsoft.training.dao;

import org.expertsoft.training.model.TourTokenModel;

import java.util.Optional;

public interface TourTokenDao {
    Optional<TourTokenModel> getByCode(String code);
}
