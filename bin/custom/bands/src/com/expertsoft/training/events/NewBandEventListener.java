package com.expertsoft.training.events;

import com.expertsoft.training.model.BandModel;
import com.expertsoft.training.model.NewsModel;
import com.expertsoft.training.service.NewsService;
import com.expertsoft.training.util.NewsModelUtil;
import de.hybris.platform.servicelayer.event.events.AfterItemCreationEvent;
import de.hybris.platform.servicelayer.event.impl.AbstractEventListener;
import de.hybris.platform.servicelayer.model.ModelService;

public class NewBandEventListener extends AbstractEventListener<AfterItemCreationEvent> {

    private static final String NEW_BAND_HEADLINE = "New band, %s";
    private static final String NEW_BAND_CONTENT = "There is a new band in town called, %s. Tour news to be announced soon.";
    private ModelService modelService;
    private NewsService newsService;

    public ModelService getModelService() {
        return modelService;
    }
    public void setModelService(final ModelService modelService) {
        this.modelService = modelService;
    }

    public void setNewsService(NewsService newsService) {
        this.newsService = newsService;
    }

    @Override
    protected void onEvent(final AfterItemCreationEvent event) {
        if (event != null && event.getSource() != null) {
            if (!BandModel._TYPECODE.equals(event.getTypeCode())) {
                return;
            }

            final Object object = modelService.get(event.getSource());
            if (object instanceof final BandModel band) {
                final String headline = String.format(NEW_BAND_HEADLINE, band.getName());
                final String content = String.format(NEW_BAND_CONTENT, band.getName());

                final NewsModel newsModel = newsService.createNewsModel(headline, content);
                newsService.save(newsModel);
            }
        }
    }
}
