package com.expertsoft.training.service.impl;

import com.expertsoft.training.dao.ProducerDao;
import com.expertsoft.training.model.ProducerModel;
import com.expertsoft.training.service.ProducerService;
import de.hybris.platform.servicelayer.exceptions.AmbiguousIdentifierException;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import org.springframework.beans.factory.annotation.Required;

import java.util.List;

public class DefaultProducerService implements ProducerService {

    private ProducerDao producerDao;

    @Override
    public ProducerModel getProducerByCode(String code) {
        final List<ProducerModel> result = producerDao.findProducersByCode(code);
        if (result.isEmpty()) {
            throw new UnknownIdentifierException("Producer with code '" + code + "' not found!");
        }
        else if (result.size() > 1) {
            throw new AmbiguousIdentifierException("Producer code '" + code + "' is not unique, " + result.size() + " producers found!");
        }

        return result.get(0);
    }

    @Required
    public void setProducerDao(ProducerDao producerDao) {
        this.producerDao = producerDao;
    }
}
