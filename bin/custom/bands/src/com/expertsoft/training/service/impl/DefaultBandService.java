package com.expertsoft.training.service.impl;

import com.expertsoft.training.dao.BandDao;
import com.expertsoft.training.model.BandModel;
import com.expertsoft.training.service.BandService;
import de.hybris.platform.servicelayer.exceptions.AmbiguousIdentifierException;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import org.springframework.beans.factory.annotation.Required;

import java.util.List;

public class DefaultBandService implements BandService {
    private BandDao bandDao;
    @Override
    public List<BandModel> getBands()
    {
        return bandDao.findBands();
    }
    /**
     * Gets all bands for given code by delegating to {@link BandDAO#findBandsByCode(String)} and then assuring
     * uniqueness of result.
     */
    @Override
    public BandModel getBandForCode(final String code) throws AmbiguousIdentifierException, UnknownIdentifierException
    {
        final List<BandModel> result = bandDao.findBandsByCode(code);
        if (result.isEmpty())
        {
            throw new UnknownIdentifierException("Band with code '" + code + "' not found!");
        }
        else if (result.size() > 1)
        {
            throw new AmbiguousIdentifierException("Band code '" + code + "' is not unique, " + result.size() + " bands found!");
        }
        return result.get(0);
    }

    @Required
    public void setBandDao(final BandDao bandDao) {
        this.bandDao = bandDao;
    }
}
