package org.expertsoft.training.service.impl;

import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import de.hybris.platform.servicelayer.model.ModelService;
import org.expertsoft.training.dao.TourTokenDao;
import org.expertsoft.training.model.TourTokenModel;
import org.expertsoft.training.service.TourTokenService;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class DefaultTourTokenService implements TourTokenService {

    private TourTokenDao tourTokenDao;
    private ModelService modelService;

    @Override
    public TourTokenModel getTokenByCode(String code) {
        return tourTokenDao.getByCode(code)
                .orElseThrow(() -> new UnknownIdentifierException("TourToken with code '" + code + "' not found!"));
    }

    @Override
    public void updateToken(TourTokenModel tourTokenModel) {
        tourTokenModel.setToken(UUID.randomUUID().toString());
        modelService.save(tourTokenModel);
    }

    @Required
    public void setModelService(ModelService modelService) {
        this.modelService = modelService;
    }

    @Required
    public void setTourTokenDao(TourTokenDao tourTokenDao) {
        this.tourTokenDao = tourTokenDao;
    }
}
