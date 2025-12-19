package org.expertsoft.training.facades.impl;

import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.product.ProductService;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import org.expertsoft.training.data.TourData;
import org.expertsoft.training.facades.TourFacade;
import org.springframework.beans.factory.annotation.Required;

public class DefaultTourFacade implements TourFacade {

    private ProductService productService;
    private Converter<ProductModel, TourData> tourConverter;

    @Override
    public TourData getTourDetails(final String tourId) {
        if (tourId == null) {
            throw new IllegalArgumentException("Tour id cannot be null");
        }
        final ProductModel product = productService.getProductForCode(tourId);
        if (product == null) {
            return null;
        }

        return tourConverter.convert(product);
    }

    @Required
    public void setTourConverter(Converter<ProductModel, TourData> tourConverter) {
        this.tourConverter = tourConverter;
    }

    @Required
    public void setProductService(final ProductService productService) {
        this.productService = productService;
    }
}
