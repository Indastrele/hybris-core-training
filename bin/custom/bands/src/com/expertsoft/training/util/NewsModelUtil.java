package com.expertsoft.training.util;

import com.expertsoft.training.model.NewsModel;
import de.hybris.platform.servicelayer.model.ModelService;
import org.springframework.beans.factory.annotation.Required;

import java.util.Date;
import java.util.UUID;

public class NewsModelUtil {

    private ModelService modelService;

    public void createAndSaveNewsModel(String headline, String content) {
        final NewsModel newsModel = modelService.create(NewsModel.class);

        newsModel.setCode(UUID.randomUUID().toString());
        newsModel.setDate(new Date());
        newsModel.setHeadline(headline);
        newsModel.setContent(content);

        modelService.save(newsModel);
    }

    @Required
    public void setModelService(ModelService modelService) {
        this.modelService = modelService;
    }
}
