package com.expertsoft.training.service;

import com.expertsoft.training.model.NewsModel;

public interface NewsService {
    NewsModel createNewsModel(String headline, String content);
    void save(NewsModel newsModel);
}
