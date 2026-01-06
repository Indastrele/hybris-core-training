package com.expertsoft.training.controller;

import com.expertsoft.training.facades.ProducerFacade;
import de.hybris.platform.catalog.CatalogVersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/producers")
public class ProducerController {
    private static final String CATALOG_ID = "concertToursProductCatalog";
    private static final String CATALOG_VERSION_NAME = "Online";

    private final ProducerFacade producerFacade;
    private final CatalogVersionService catalogVersionService;

    @Autowired
    public ProducerController(ProducerFacade producerFacade, CatalogVersionService catalogVersionService) {
        this.producerFacade = producerFacade;
        this.catalogVersionService = catalogVersionService;
    }

    @GetMapping("/{id}")
    public String getProducer(@PathVariable(name = "id") String id, final ModelMap model) {
        catalogVersionService.setSessionCatalogVersion(CATALOG_ID, CATALOG_VERSION_NAME);
        model.addAttribute("producer", producerFacade.getProducer(id));

        return "producerDetails";
    }
}
