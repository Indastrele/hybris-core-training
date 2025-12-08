package com.expertsoft.training.dao.impl;

import com.expertsoft.training.dao.BandDao;
import com.expertsoft.training.model.BandModel;
import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.core.Registry;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import de.hybris.platform.servicelayer.model.ModelService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;

@IntegrationTest
public class DefaultBandDaoIntegrationTest extends ServicelayerTransactionalTest {
    /** As this is an integration test, the class (object) being tested gets injected here. */
    @Resource
    private BandDao bandDao;
    /** Platform's ModelService used for creation of test data. */
    @Resource
    private ModelService modelService;
    /** Name of test band. */
    private static final String BAND_CODE = "ROCK-11";
    /** Name of test band. */
    private static final String BAND_NAME = "Ladies of Rock";
    /** History of test band. */
    private static final String BAND_HISTORY = "All female rock band formed in Munich in the late 1990s";
    /** Albums sold */
    private static final Long ALBUMS_SOLD = 1000L;
    @Before
    public void setUp() throws Exception {
        try {
            Thread.sleep(TimeUnit.SECONDS.toMillis(1));
            new JdbcTemplate(Registry.getCurrentTenant().getDataSource()).execute("CHECKPOINT");
            Thread.sleep(TimeUnit.SECONDS.toMillis(1));
        } catch (InterruptedException exc) {}
    }
    @Test
    public void bandDAOTest()
    {
        // check that our test band is not already present in the database
        List<BandModel> bandsByCode = bandDao.findBandsByCode(BAND_CODE);
        assertTrue("No Band should be returned", bandsByCode.isEmpty());
        // retrieve all bands currently in the database
        List<BandModel> allBands = bandDao.findBands();
        final int size = allBands.size();
        // add our test band to the database
        final BandModel bandModel = modelService.create(BandModel.class);
        bandModel.setCode(BAND_CODE);
        bandModel.setName(BAND_NAME);
        bandModel.setHistory(BAND_HISTORY);
        bandModel.setAlbumSales(ALBUMS_SOLD);
        modelService.save(bandModel);
        // check we now get one more band back than previously and our test band is in the list
        allBands = bandDao.findBands();
        Assert.assertEquals(size + 1, allBands.size());
        Assert.assertTrue("band not found", allBands.contains(bandModel));
        // check we can locate our test band by its code
        bandsByCode = bandDao.findBandsByCode(BAND_CODE);
        Assert.assertEquals("Did not find the Band we just saved", 1, bandsByCode.size());
        Assert.assertEquals("Retrieved Band's code attribute incorrect", BAND_CODE, bandsByCode.get(0).getCode());
        Assert.assertEquals("Retrieved Band's name attribute incorrect", BAND_NAME, bandsByCode.get(0).getName());
        Assert.assertEquals("Retrieved Band's albumSales attribute incorrect", ALBUMS_SOLD, bandsByCode.get(0).getAlbumSales());
        Assert.assertEquals("Retrieved Band's history attribute incorrect", BAND_HISTORY, bandsByCode.get(0).getHistory());
    }
    @Test
    public void testFindBands_EmptyStringParam()
    {
        final List<BandModel> bands = bandDao.findBandsByCode("");
        Assert.assertTrue("No Band should be returned", bands.isEmpty());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindBands_NullParam()
    {
        bandDao.findBandsByCode(null);
    }

    @After
    public void tearDown() {

    }
}
