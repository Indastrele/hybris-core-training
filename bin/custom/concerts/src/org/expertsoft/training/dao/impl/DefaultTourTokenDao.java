package org.expertsoft.training.dao.impl;

import com.expertsoft.training.model.BandModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import org.expertsoft.training.dao.TourTokenDao;
import org.expertsoft.training.model.TourTokenModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("tourTokenDao")
public class DefaultTourTokenDao implements TourTokenDao {

    private static final String SELECT_FROM_WHERE_CODE = "SELECT {%s} FROM {%s} WHERE {%s}=?code";
    private static final String SELECT_FROM = "SELECT {%s} FROM {%s}";
    private static final String CODE = "code";
    /**
     * Use SAP
     Commerce FlexibleSearchService for running queries against the database
     */
    @Autowired
    private FlexibleSearchService flexibleSearchService;

    @Override
    public Optional<TourTokenModel> getByCode(String code) {
        final String queryString = String.format(SELECT_FROM_WHERE_CODE, TourTokenModel.PK, TourTokenModel._TYPECODE, TourTokenModel.CODE);
        final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString);
        query.addQueryParameter(CODE, code);
        return flexibleSearchService.<TourTokenModel> search(query).getResult().stream().findFirst();
    }
}
