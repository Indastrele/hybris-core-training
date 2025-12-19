package org.expertsoft.training.populators;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import org.expertsoft.training.data.ConcertSummaryData;
import org.expertsoft.training.enums.ConcertType;
import org.expertsoft.training.model.ConcertModel;

public class ConcertSummaryDataPopulator implements Populator<ConcertModel, ConcertSummaryData> {

    @Override
    public void populate(ConcertModel concertModel, ConcertSummaryData concertSummaryData) throws ConversionException {
        concertSummaryData.setId(concertModel.getCode());
        concertSummaryData.setDate(concertModel.getDate());
        concertSummaryData.setVenue(concertModel.getVenue());
        concertSummaryData.setType(concertModel.getConcertType() == ConcertType.OPENAIR ? "Outdoors" : "Indoors");
    }
}
