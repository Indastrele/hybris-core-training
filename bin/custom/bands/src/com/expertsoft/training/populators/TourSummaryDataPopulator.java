package com.expertsoft.training.populators;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import org.expertsoft.training.data.TourSummaryData;

import java.util.Locale;

public class TourSummaryDataPopulator implements Populator<ProductModel, TourSummaryData> {
    @Override
    public void populate(ProductModel productModel, TourSummaryData tourSummaryData) throws ConversionException {
        tourSummaryData.setId(productModel.getCode());
        tourSummaryData.setTourName(productModel.getName(Locale.ENGLISH));
        tourSummaryData.setNumberOfConcerts(Integer.toString(productModel.getVariants().size()));
    }
}
