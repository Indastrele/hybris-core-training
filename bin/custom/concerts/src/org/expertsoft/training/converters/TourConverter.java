package org.expertsoft.training.converters;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import org.expertsoft.training.data.TourData;
import org.springframework.beans.factory.annotation.Required;

public class TourConverter implements Converter<ProductModel, TourData> {

    private Populator<ProductModel, TourData> tourDataPopulator;

    @Override
    public TourData convert(ProductModel productModel) throws ConversionException {
        final TourData tourData = new TourData();


        return convert(productModel, tourData);
    }

    @Override
    public TourData convert(ProductModel productModel, TourData tourData) throws ConversionException {
        tourDataPopulator.populate(productModel, tourData);

        return tourData;
    }

    @Required
    public void setTourDataPopulator(Populator<ProductModel, TourData> tourDataPopulator) {
        this.tourDataPopulator = tourDataPopulator;
    }
}
