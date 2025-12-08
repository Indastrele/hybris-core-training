package com.expertsoft.training.facades;

import com.expertsoft.training.data.BandData;

import java.util.List;

public interface BandFacade {
    BandData getBand(String name);
    List<BandData> getBands();
}
