package com.expertsoft.training.dao.impl;

import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.expertsoft.training.model.BandModel;
import com.expertsoft.training.dao.BandDao;

@Component(value = "bandDao")
public class DefaultBandDao implements BandDao {
    private static final String SELECT_FROM_WHERE_CODE = "SELECT {%s} FROM {%s} WHERE {%s}=?code";
    private static final String SELECT_FROM = "SELECT {%s} FROM {%s}";
    private static final String CODE = "code";
    /**
     * Use SAP
     Commerce FlexibleSearchService for running queries against the database
     */
    @Autowired
    private FlexibleSearchService flexibleSearchService;
    /**
     * Finds all Bands by performing a FlexibleSearch using the {@link FlexibleSearchService}.
     */
    @Override
    public List<BandModel> findBands()
    {
        final String queryString = String.format(SELECT_FROM, BandModel.PK, BandModel._TYPECODE);
        final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString);
        return flexibleSearchService.<BandModel> search(query).getResult();
    }
    /**
     * Finds all Bands by given code by performing a FlexibleSearch using the {@link FlexibleSearchService}.
     */
    @Override
    public List<BandModel> findBandsByCode(final String code)
    {
        final String queryString = String.format(SELECT_FROM_WHERE_CODE, BandModel.PK, BandModel._TYPECODE, BandModel.CODE);
        final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString);
        query.addQueryParameter(CODE, code);
        query.setCount(1);
        return flexibleSearchService.<BandModel> search(query).getResult();
    }
}