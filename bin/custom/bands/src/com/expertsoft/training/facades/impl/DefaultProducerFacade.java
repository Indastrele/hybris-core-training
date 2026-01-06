package com.expertsoft.training.facades.impl;

import com.expertsoft.training.data.ProducerData;
import com.expertsoft.training.facades.ProducerFacade;
import com.expertsoft.training.model.ProducerModel;
import com.expertsoft.training.service.ProducerService;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import org.springframework.beans.factory.annotation.Required;

public class DefaultProducerFacade implements ProducerFacade {

    private ProducerService producerService;
    private Converter<ProducerModel, ProducerData> producerConverter;

    @Override
    public ProducerData getProducer(String id) {
        if (id == null) {
            throw new IllegalArgumentException("Producer id cannot be null");
        }

        final ProducerModel producer = producerService.getProducerByCode(id);
        if (producer == null) {
            return null;
        }

        return producerConverter.convert(producer);
    }

    @Required
    public void setProducerService(ProducerService producerService) {
        this.producerService = producerService;
    }

    @Required
    public void setProducerConverter(Converter<ProducerModel, ProducerData> producerConverter) {
        this.producerConverter = producerConverter;
    }
}
