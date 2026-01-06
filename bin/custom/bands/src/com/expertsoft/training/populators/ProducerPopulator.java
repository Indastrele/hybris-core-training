package com.expertsoft.training.populators;

import com.expertsoft.training.data.BandData;
import com.expertsoft.training.data.ProducerData;
import com.expertsoft.training.model.BandModel;
import com.expertsoft.training.model.ProducerModel;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class ProducerPopulator implements Populator<ProducerModel, ProducerData> {
    @Override
    public void populate(ProducerModel producerModel, ProducerData producerData) throws ConversionException {
        producerData.setId(producerModel.getCode());
        producerData.setName(producerModel.getName());
        producerData.setSurname(producerModel.getSurname());
        producerData.setPatronymic(producerModel.getPatronymic());
        producerData.setDescription(producerModel.getDescription());
        producerData.setBands(getBands(producerModel));
    }

    private List<BandData> getBands(final ProducerModel producerModel) {
        Collection<BandModel> bands = producerModel.getBands();

        if (bands == null) {
            return Collections.emptyList();
        }

        return bands.stream()
                .map(this::getBandData)
                .toList();
    }

    // Placed here because of cyclic dependency between this class and BandPopulator
    private BandData getBandData(BandModel bandModel) {
        final BandData bandData = new BandData();
        bandData.setId(bandModel.getCode());
        bandData.setName(bandModel.getName());
        return bandData;
    }
}
