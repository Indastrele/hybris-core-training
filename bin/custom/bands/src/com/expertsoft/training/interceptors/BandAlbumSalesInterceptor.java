package com.expertsoft.training.interceptors;

import com.expertsoft.training.events.BandAlbumSalesEvent;
import com.expertsoft.training.model.BandModel;
import de.hybris.platform.servicelayer.event.EventService;
import de.hybris.platform.servicelayer.interceptor.InterceptorContext;
import de.hybris.platform.servicelayer.interceptor.InterceptorException;
import de.hybris.platform.servicelayer.interceptor.PrepareInterceptor;
import de.hybris.platform.servicelayer.interceptor.ValidateInterceptor;
import org.springframework.beans.factory.annotation.Autowired;

import static de.hybris.platform.servicelayer.model.ModelContextUtils.getItemModelContext;

public class BandAlbumSalesInterceptor implements ValidateInterceptor, PrepareInterceptor {

    private static final long BIG_SALES = 50000L;
    private static final long NEGATIVE_SALES = 0L;
    @Autowired
    private EventService eventService;

    @Override
    public void onValidate(final Object model, final InterceptorContext ctx) throws InterceptorException {
        if (model instanceof final BandModel band) {
            final Long sales = band.getAlbumSales();

            if (sales != null && sales < NEGATIVE_SALES) {
                throw new InterceptorException("Album sales must be positive");
            }
        }
    }
    @Override
    public void onPrepare(final Object model, final InterceptorContext ctx) {
        if (model instanceof final BandModel band) {
            if (!hasBecomeBig(band, ctx)) {
                return;
            }

            eventService.publishEvent(new BandAlbumSalesEvent(band.getCode(), band.getName(), band.getAlbumSales()));
        }
    }
    private boolean hasBecomeBig(final BandModel band, final InterceptorContext ctx)
    {
        final Long sales = band.getAlbumSales();
        if (sales != null && sales >= BIG_SALES) {
            if (ctx.isNew(band)) {
                return true;
            } else {
                final Long oldValue = getItemModelContext(band).getOriginalValue(BandModel.ALBUMSALES);

                return oldValue == null || oldValue.intValue() < BIG_SALES;
            }
        }

        return false;
    }
}
