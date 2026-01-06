package com.expertsoft.training.populators;

import com.expertsoft.training.data.BandData;
import com.expertsoft.training.data.ProducerData;
import com.expertsoft.training.enums.MusicType;
import com.expertsoft.training.model.BandModel;
import com.expertsoft.training.model.ProducerModel;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import org.expertsoft.training.data.TourSummaryData;
import org.springframework.beans.factory.annotation.Required;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class BandPopulator implements Populator<BandModel, BandData> {

    private Populator<ProductModel, TourSummaryData> tourSummaryDataPopulator;
    private Populator<ProducerModel, ProducerData> producerPopulator;

    @Override
    public void populate(BandModel bandModel, BandData bandData) throws ConversionException {
        bandData.setId(bandModel.getCode());
        bandData.setDescription(bandModel.getHistory());
        bandData.setName(bandModel.getName());
        bandData.setAlbumsSold(bandModel.getAlbumSales());
        bandData.setMembers(bandModel.getMembers());
        bandData.setMembersToInstruments(bandModel.getMembersToInstruments());
        bandData.setGenres(getGenres(bandModel));
        bandData.setTours(getTourHistory(bandModel));
        bandData.setProducers(getProducers(bandModel));
    }

    private List<TourSummaryData> getTourHistory(final BandModel bandModel) {
        Set<ProductModel> tourHistory = bandModel.getTours();

        if (tourHistory == null) {
            return Collections.emptyList();
        }

        return tourHistory.stream()
                .map(model -> {
                    final TourSummaryData summary = new TourSummaryData();
                    tourSummaryDataPopulator.populate(model, summary);
                    return summary;
                })
                .toList();
    }

    private List<String> getGenres(final BandModel bandModel) {
        Collection<MusicType> types = bandModel.getTypes();

        if (types == null) {
            return Collections.emptyList();
        }

        return types.stream().map(MusicType::getCode).toList();
    }

    private List<ProducerData> getProducers(final BandModel bandModel) {
        Collection<ProducerModel> producers = bandModel.getProducers();

        if (producers == null) {
            return Collections.emptyList();
        }

        return producers.stream().map(model -> {
            final ProducerData producerData = new ProducerData();
            producerPopulator.populate(model, producerData);
            return producerData;
        }).toList();
    }

    @Required
    public void setTourSummaryDataPopulator(Populator<ProductModel, TourSummaryData> tourSummaryDataPopulator) {
        this.tourSummaryDataPopulator = tourSummaryDataPopulator;
    }

    @Required
    public void setProducerPopulator(Populator<ProducerModel, ProducerData> producerPopulator){
        this.producerPopulator = producerPopulator;
    }
}
