/*
 * Copyright (c) 2021 SAP SE or an SAP affiliate company. All rights reserved.
 */
package org.expertsoft.training.controller;

import de.hybris.platform.catalog.CatalogVersionService;
import org.expertsoft.training.facades.TourFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
public class ConcertsController {

	private static final String CATALOG_ID = "concertToursProductCatalog";
	private static final String CATALOG_VERSION_NAME = "Online";
	private static final String TOUR = "tour";
	private static final String ID = "id";

	private TourFacade tourFacade;
	private CatalogVersionService catalogVersionService;

	@Autowired
	public ConcertsController(TourFacade tourFacade, CatalogVersionService catalogVersionService) {
		this.tourFacade = tourFacade;
		this.catalogVersionService = catalogVersionService;
	}

	@GetMapping("/{id}")
	public String getTour(@PathVariable(name = ID) final String id, final ModelMap model) {
		catalogVersionService.setSessionCatalogVersion(CATALOG_ID, CATALOG_VERSION_NAME);
		model.addAttribute(TOUR, tourFacade.getTourDetails(id));
		return "tourDetails";
	}
}
