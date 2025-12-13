package org.expertsoft.training.populators;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.variants.model.VariantProductModel;
import org.expertsoft.training.data.ConcertSummaryData;
import org.expertsoft.training.data.TourData;
import org.expertsoft.training.model.ConcertModel;
import org.springframework.beans.factory.annotation.Required;

import java.util.ArrayList;
import java.util.List;

public class TourDataPopulator implements Populator<ProductModel, TourData> {

    private Populator<ConcertModel, ConcertSummaryData> concertSummaryDataPopulator;

    @Override
    public void populate(ProductModel productModel, TourData tourData) throws ConversionException {
        tourData.setId(productModel.getCode());
        tourData.setTourName(productModel.getName());
        tourData.setDescription(productModel.getDescription());
        tourData.setConcerts(getConcerts(productModel));
    }

    private List<ConcertSummaryData> getConcerts(ProductModel productModel) {
        final List<ConcertSummaryData> concerts = new ArrayList<>();
        if (productModel.getVariants() != null) {
            for (final VariantProductModel variant : productModel.getVariants()) {
                if (variant instanceof final ConcertModel concert) {
                    final ConcertSummaryData summary = new ConcertSummaryData();
                    concertSummaryDataPopulator.populate(concert, summary);
                    concerts.add(summary);
                }
            }
        }

        return concerts;
    }

    @Required
    public void setConcertSummaryDataPopulator(Populator<ConcertModel, ConcertSummaryData> concertSummaryDataPopulator) {
        this.concertSummaryDataPopulator = concertSummaryDataPopulator;
    }
}
