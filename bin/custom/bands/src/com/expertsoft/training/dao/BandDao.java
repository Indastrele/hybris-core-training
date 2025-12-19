package com.expertsoft.training.dao;

import java.util.List;
import com.expertsoft.training.model.BandModel;

public interface BandDao {
    /**
     * Return a list of band models that are currently persisted. If none are found an empty list is returned.
     *
     * @return all the bands in the system
     */
    List<BandModel> findBands();
    /**
     * Finds all bands with given code. If none is found, an empty list will be returned.
     *
     * @param code
     *           the code to search for bands
     * @return All bands with the given code.
     */
    List<BandModel> findBandsByCode(String code);
}