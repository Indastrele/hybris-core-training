package com.expertsoft.training.events;

import com.expertsoft.training.model.NewsModel;
import com.expertsoft.training.service.NewsService;
import de.hybris.platform.servicelayer.event.impl.AbstractEventListener;

public class BandAlbumSalesEventListener extends AbstractEventListener<BandAlbumSalesEvent> {

    private static final String BAND_SALES_HEADLINE = "%s album sales exceed 50000";
    private static final String BAND_SALES_CONTENT = "%s album sales reported as %d";
    private NewsService newsService;

    public BandAlbumSalesEventListener() {
    }

    public void setNewsService(NewsService newsService) {
        this.newsService = newsService;
    }

    @Override
    protected void onEvent(final BandAlbumSalesEvent event) {
        if (event == null) {
            return;
        }

        final String headline = String.format(BAND_SALES_HEADLINE, event.getName());
        final String content = String.format(BAND_SALES_CONTENT, event.getName(), event.getSales());

        final NewsModel newsModel = newsService.createNewsModel(headline, content);
        newsService.save(newsModel);
    }
}
