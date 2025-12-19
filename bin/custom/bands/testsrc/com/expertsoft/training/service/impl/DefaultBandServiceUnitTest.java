package com.expertsoft.training.service.impl;

import com.expertsoft.training.dao.BandDao;
import com.expertsoft.training.model.BandModel;
import de.hybris.bootstrap.annotations.UnitTest;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.List;
import java.util.Locale;

import static org.mockito.Mockito.mock;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@UnitTest
public class DefaultBandServiceUnitTest {

    private DefaultBandService bandService;
    private BandDao bandDao;
    private BandModel bandModel;
    private static final String BAND_CODE = "Ch00X";
    private static final String BAND_NAME = "Singers All";
    private static final String BAND_HISTORY = "Medieval choir formed in 2001, based in Munich famous for authentic monastic chants";
    @Before
    public void setUp() {
        bandService = new DefaultBandService();

        bandDao = mock(BandDao.class);
        bandService.setBandDao(bandDao);

        bandModel = new BandModel();
        bandModel.setCode(BAND_CODE);
        bandModel.setName(BAND_NAME);
        bandModel.setAlbumSales(1000L);
        bandModel.setHistory(BAND_HISTORY, Locale.ENGLISH);
    }

    @Test
    public void testGetAllBands() {
        final List<BandModel> bandModels = Collections.singletonList(bandModel);

        when(bandDao.findBands()).thenReturn(bandModels);

        final List<BandModel> result = bandService.getBands();

        assertEquals("We should find one", 1, result.size());
        assertEquals("And should equals what the mock returned", bandModel, result.get(0));
    }
    @Test
    public void testGetBand() {
        when(bandDao.findBandsByCode(BAND_CODE)).thenReturn(Collections.singletonList(bandModel));

        final BandModel result = bandService.getBandForCode(BAND_CODE);

        assertEquals("Band should equals() what the mock returned", bandModel, result);
    }
}
