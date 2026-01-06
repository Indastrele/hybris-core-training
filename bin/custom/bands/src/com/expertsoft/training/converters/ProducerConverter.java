package com.expertsoft.training.converters;

import com.expertsoft.training.data.ProducerData;
import com.expertsoft.training.model.ProducerModel;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import org.springframework.beans.factory.annotation.Required;

public class ProducerConverter implements Converter<ProducerModel, ProducerData> {

    private Populator<ProducerModel,  ProducerData> producerPopulator;

    @Override
    public ProducerData convert(ProducerModel producerModel) throws ConversionException {
        final ProducerData producerData = new ProducerData();
        return convert(producerModel, producerData);
    }

    @Override
    public ProducerData convert(ProducerModel producerModel, ProducerData producerData) throws ConversionException {
        producerPopulator.populate(producerModel, producerData);
        return producerData;
    }

    @Required
    public void setProducerPopulator(Populator<ProducerModel, ProducerData> producerPopulator) {
        this.producerPopulator = producerPopulator;
    }
}
