package com.expertsoft.training.interceptors;

import com.expertsoft.training.model.BandModel;
import de.hybris.platform.servicelayer.interceptor.InterceptorContext;
import de.hybris.platform.servicelayer.interceptor.InterceptorException;
import de.hybris.platform.servicelayer.interceptor.ValidateInterceptor;

public class BandAlbumSalesInterceptor implements ValidateInterceptor {

    private static final long NEGATIVE_SALES = 0L;

    @Override
    public void onValidate(final Object model, final InterceptorContext ctx) throws InterceptorException {
        if (model instanceof final BandModel band) {
            final Long sales = band.getAlbumSales();

            if (sales != null && sales < NEGATIVE_SALES) {
                throw new InterceptorException("Album sales must be positive");
            }
        }
    }
}
