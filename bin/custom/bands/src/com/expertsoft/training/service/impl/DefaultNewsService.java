package com.expertsoft.training.service.impl;

import com.expertsoft.training.model.NewsModel;
import com.expertsoft.training.service.NewsService;
import de.hybris.platform.servicelayer.model.ModelService;
import org.springframework.beans.factory.annotation.Required;

import java.util.Date;
import java.util.UUID;

public class DefaultNewsService implements NewsService {

    private ModelService modelService;

    @Override
    public NewsModel createNewsModel(String headline, String content) {
        final NewsModel newsModel = modelService.create(NewsModel.class);

        newsModel.setCode(UUID.randomUUID().toString());
        newsModel.setDate(new Date());
        newsModel.setHeadline(headline);
        newsModel.setContent(content);

        return newsModel;
    }

    @Override
    public void save(NewsModel newsModel) {
        modelService.save(newsModel);
    }

    @Required
    public void setModelService(ModelService modelService)  {
        this.modelService = modelService;
    }
}
