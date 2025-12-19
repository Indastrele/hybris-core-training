package org.expertsoft.training.facades;

import org.expertsoft.training.data.TourData;

public interface TourFacade {
    TourData getTourDetails(final String tourId);
}
