package com.expertsoft.training.converters;

import com.expertsoft.training.data.BandData;
import com.expertsoft.training.model.BandModel;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import org.springframework.beans.factory.annotation.Required;

public class BandConverter implements Converter<BandModel, BandData> {

    private Populator<BandModel, BandData> bandPopulator;

    @Override
    public BandData convert(BandModel bandModel) throws ConversionException {
        final BandData bandData = new BandData();
        return convert(bandModel, bandData);
    }

    @Override
    public BandData convert(BandModel bandModel, BandData bandData) throws ConversionException {
        bandPopulator.populate(bandModel, bandData);

        return bandData;
    }

    @Required
    public void setBandPopulator(Populator<BandModel, BandData> bandPopulator) {
        this.bandPopulator = bandPopulator;
    }
}
