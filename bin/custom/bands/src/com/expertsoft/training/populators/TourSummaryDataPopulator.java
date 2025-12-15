package com.expertsoft.training.populators;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.i18n.I18NService;
import org.expertsoft.training.data.TourSummaryData;
import org.springframework.beans.factory.annotation.Required;

public class TourSummaryDataPopulator implements Populator<ProductModel, TourSummaryData> {

    private I18NService i18NService;

    @Override
    public void populate(ProductModel productModel, TourSummaryData tourSummaryData) throws ConversionException {
        tourSummaryData.setId(productModel.getCode());
        tourSummaryData.setTourName(productModel.getName(i18NService.getCurrentLocale()));
        tourSummaryData.setNumberOfConcerts(Integer.toString(productModel.getVariants().size()));
    }

    @Required
    public void setI18NService(I18NService i18NService) {
        this.i18NService = i18NService;
    }
}
