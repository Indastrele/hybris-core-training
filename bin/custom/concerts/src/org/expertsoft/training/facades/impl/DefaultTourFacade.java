package org.expertsoft.training.facades.impl;

import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.product.ProductService;
import de.hybris.platform.variants.model.VariantProductModel;
import org.expertsoft.training.data.ConcertSummaryData;
import org.expertsoft.training.data.TourData;
import org.expertsoft.training.enums.ConcertType;
import org.expertsoft.training.facades.TourFacade;
import org.expertsoft.training.model.ConcertModel;
import org.springframework.beans.factory.annotation.Required;

import java.util.ArrayList;
import java.util.List;

public class DefaultTourFacade implements TourFacade {
    private ProductService productService;
    @Override
    public TourData getTourDetails(final String tourId)
    {
        if (tourId == null)
        {
            throw new IllegalArgumentException("Tour id cannot be null");
        }
        final ProductModel product = productService.getProductForCode(tourId);
        if (product == null)
        {
            return null;
        }
        // Create a list of ConcertSummaryData from the matches
        final List<ConcertSummaryData> concerts = new ArrayList<>();
        if (product.getVariants() != null)
        {
            for (final VariantProductModel variant : product.getVariants())
            {
                if (variant instanceof final ConcertModel concert)
                {
                    final ConcertSummaryData summary = new ConcertSummaryData();
                    summary.setId(concert.getCode());
                    summary.setDate(concert.getDate());
                    summary.setVenue(concert.getVenue());
                    summary.setType(concert.getConcertType() == ConcertType.OPENAIR ? "Outdoors" : "Indoors");
                    concerts.add(summary);
                }
            }
        }

        final TourData tourData = new TourData();
        tourData.setId(product.getCode());
        tourData.setTourName(product.getName());
        tourData.setDescription(product.getDescription());
        tourData.setConcerts(concerts);
        return tourData;
    }
    @Required
    public void setProductService(final ProductService productService)
    {
        this.productService = productService;
    }
}
