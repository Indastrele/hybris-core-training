package com.expertsoft.training.facades.impl;

import de.hybris.platform.core.model.product.ProductModel;
import com.expertsoft.training.data.BandData;
import com.expertsoft.training.enums.MusicType;
import com.expertsoft.training.facades.BandFacade;
import com.expertsoft.training.model.BandModel;
import com.expertsoft.training.service.BandService;
import org.expertsoft.training.data.TourSummaryData;
import org.springframework.beans.factory.annotation.Required;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Set;

public class DefaultBandFacade implements BandFacade {
    private BandService bandService;
    @Override
    public List<BandData> getBands()
    {
        final List<BandModel> bandModels = bandService.getBands();
        final List<BandData> bandFacadeData = new ArrayList<>();
        for (final BandModel sm : bandModels)
        {
            final BandData sfd = createNewSimpleBandData(sm);
            bandFacadeData.add(sfd);
        }
        return bandFacadeData;
    }
    @Override
    public BandData getBand(final String name)
    {
        if (name == null)
        {
            throw new IllegalArgumentException("Band name cannot be null");
        }
        final BandModel band = bandService.getBandForCode(name);
        if (band == null)
        {
            return null;
        }

        final List<String> genres = getGenres(band);
        final List<TourSummaryData> tourHistory = getTourHistory(band);

        final BandData bandData = createNewSimpleBandData(band);
        bandData.setGenres(genres);
        bandData.setTours(tourHistory);
        return bandData;
    }

    private List<String> getGenres(final BandModel bandModel) {
        Collection<MusicType> types = bandModel.getTypes();

        if (types == null) {
            return new ArrayList<>();
        }

        return types.stream().map(MusicType::getCode).toList();
    }

    private List<TourSummaryData> getTourHistory(final BandModel bandModel) {
        Set<ProductModel> tourHistory = bandModel.getTours();

        if (tourHistory == null) {
            return new ArrayList<>();
        }

        return tourHistory.stream()
                .map(model -> {
                    final TourSummaryData summary = new TourSummaryData();
                    summary.setId(model.getCode());
                    summary.setTourName(model.getName(Locale.ENGLISH));
                    summary.setNumberOfConcerts(Integer.toString(model.getVariants().size()));

                    return summary;
                })
                .toList();
    }

    private BandData createNewSimpleBandData(final BandModel bandModel) {
        final BandData bandData = new BandData();
        bandData.setId(bandModel.getCode());
        bandData.setName(bandModel.getName());
        bandData.setDescription(bandModel.getHistory());
        bandData.setAlbumsSold(bandModel.getAlbumSales());
        bandData.setMembers(bandModel.getMembers());
        bandData.setMembersToInstruments(bandModel.getMembersToInstruments());

        return bandData;
    }
    @Required
    public void setBandService(final BandService bandService)
    {
        this.bandService = bandService;
    }
}
