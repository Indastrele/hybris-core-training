package com.expertsoft.training.facades.impl;

import com.expertsoft.training.data.BandData;
import com.expertsoft.training.facades.BandFacade;
import com.expertsoft.training.model.BandModel;
import com.expertsoft.training.service.BandService;
import de.hybris.platform.core.model.media.MediaContainerModel;
import de.hybris.platform.core.model.media.MediaFormatModel;
import de.hybris.platform.servicelayer.config.ConfigurationService;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.servicelayer.media.MediaService;
import org.springframework.beans.factory.annotation.Required;

import java.util.ArrayList;
import java.util.List;

public class DefaultBandFacade implements BandFacade {

    public static final String BAND_DETAIL_FORMAT = "band.detail.format.name";
    private BandService bandService;
    private MediaService mediaService;
    private ConfigurationService configService;
    private Converter<BandModel, BandData> bandConverter;

    @Override
    public List<BandData> getBands() {
        final List<BandModel> bandModels = bandService.getBands();
        final List<BandData> bandFacadeData = new ArrayList<>();
        if (bandModels != null && !bandModels.isEmpty()) {
            bandModels.forEach((final BandModel sm) -> bandFacadeData.add(bandConverter.convert(sm)));
        }
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

        final String mediaFormatName = configService.getConfiguration().getString(BAND_DETAIL_FORMAT);
        final MediaFormatModel format = mediaService.getFormat(mediaFormatName);

        final BandData bandData = bandConverter.convert(band);
        bandData.setImageURL(getImageURL(band, format));

        return bandData;
    }


    protected String getImageURL(final BandModel sm, final MediaFormatModel format) {
        final MediaContainerModel container = sm.getImage();
        if (container != null) {
            return mediaService.getMediaByFormat(container, format).getDownloadURL();
        }
        return null;
    }

    @Required
    public void setBandService(final BandService bandService) {
        this.bandService = bandService;
    }

    @Required
    public void setBandConverter(final Converter<BandModel, BandData> bandConverter) {
        this.bandConverter = bandConverter;
    }
    @Required
    public void setMediaService(MediaService mediaService) {
        this.mediaService = mediaService;
    }
    @Required
    public void setConfigService(ConfigurationService configService){
        this.configService = configService;
    }

}
