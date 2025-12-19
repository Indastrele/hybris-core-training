package com.expertsoft.training.facades.impl;

import com.expertsoft.training.data.BandData;
import com.expertsoft.training.facades.BandFacade;
import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.core.Registry;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;

@IntegrationTest
public class DefaultBandFacadeIntegrationWithPropertiesTest extends ServicelayerTransactionalTest {

    @Resource
    private BandFacade bandFacade;

    @Before
    public void setUp() throws Exception
    {
        try {
            Thread.sleep(TimeUnit.SECONDS.toMillis(1));
            new JdbcTemplate(Registry.getCurrentTenant().getDataSource()).execute("CHECKPOINT");
            Thread.sleep(TimeUnit.SECONDS.toMillis(1));
        }
        catch (InterruptedException exc) {}
    }

    @Test
    public void testProperties() throws Exception {
        createCoreData();
        importCsv("/impex/essentialdata-mediaformats.impex", "UTF-8");
        importCsv("/impex/bands.impex", "UTF-8");

        List<BandData> bands = bandFacade.getBands();
        assertTrue(bands.size() > 0);
        assertTrue(DefaultBandFacade.BAND_DETAIL_FORMAT.equals("band.detail.format.name"));
    }

    @After
    public void teardown() {}
}
