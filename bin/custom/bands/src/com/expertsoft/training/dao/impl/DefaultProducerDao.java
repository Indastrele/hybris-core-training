package com.expertsoft.training.dao.impl;

import com.expertsoft.training.dao.ProducerDao;
import com.expertsoft.training.model.ProducerModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component(value = "producerDao")
public class DefaultProducerDao implements ProducerDao {
    private static final String SELECT_FROM_WHERE_CODE = "SELECT {%s} FROM {%s} WHERE {%s}=?code";
    private static final String CODE = "code";

    @Autowired
    private FlexibleSearchService flexibleSearchService;

    @Override
    public List<ProducerModel> findProducersByCode(String code) {
        final String queryString = String.format(SELECT_FROM_WHERE_CODE, ProducerModel.PK, ProducerModel._TYPECODE, ProducerModel.CODE);
        final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString);
        query.addQueryParameter(CODE, code);
        query.setCount(1);
        return flexibleSearchService.<ProducerModel> search(query).getResult();
    }
}
