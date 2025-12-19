package org.expertsoft.training.jobs;

import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.cronjob.model.CronJobModel;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;
import org.expertsoft.training.model.TourTokenModel;
import org.expertsoft.training.service.TourTokenService;
import org.springframework.beans.factory.annotation.Autowired;

public class TourTokenUpdateJob extends AbstractJobPerformable<CronJobModel> {

    private static final String TOKEN_CODE = "TT001";
    @Autowired
    private TourTokenService tourTokenService;

    @Override
    public PerformResult perform(CronJobModel cronJobModel) {
        try {
            TourTokenModel tourTokenModel = tourTokenService.getTokenByCode(TOKEN_CODE);
            tourTokenService.updateToken(tourTokenModel);
        } catch (Exception ex) {
            return new PerformResult(CronJobResult.ERROR, CronJobStatus.FINISHED);
        }

        return new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
    }
}
