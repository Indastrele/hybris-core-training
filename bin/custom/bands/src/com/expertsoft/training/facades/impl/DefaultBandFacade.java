package com.expertsoft.training.facades.impl;

import com.expertsoft.training.data.BandData;
import com.expertsoft.training.facades.BandFacade;
import com.expertsoft.training.model.BandModel;
import com.expertsoft.training.service.BandService;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import org.springframework.beans.factory.annotation.Required;

import java.util.ArrayList;
import java.util.List;

public class DefaultBandFacade implements BandFacade {

    private BandService bandService;
    private Converter<BandModel, BandData> bandConverter;

    @Override
    public List<BandData> getBands() {
        final List<BandModel> bandModels = bandService.getBands();
        final List<BandData> bandFacadeData = new ArrayList<>();
        bandModels.forEach((final BandModel sm) -> bandFacadeData.add(bandConverter.convert(sm)));
        return bandFacadeData;
    }

    @Override
    public BandData getBand(final String name) {
        if (name == null) {
            throw new IllegalArgumentException("Band name cannot be null");
        }

        final BandModel band = bandService.getBandForCode(name);
        if (band == null) {
            return null;
        }

        return bandConverter.convert(band);
    }

    @Required
    public void setBandService(final BandService bandService) {
        this.bandService = bandService;
    }

    @Required
    public void setBandConverter(final Converter<BandModel, BandData> bandConverter) {
        this.bandConverter = bandConverter;
    }
}
