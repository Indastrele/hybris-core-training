package org.expertsoft.training.service;

import org.expertsoft.training.model.TourTokenModel;

public interface TourTokenService {
    TourTokenModel getTokenByCode(String code);

    void updateToken(TourTokenModel tourTokenModel);
}
