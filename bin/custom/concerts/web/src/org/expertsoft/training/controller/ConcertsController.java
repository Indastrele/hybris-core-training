/*
 * Copyright (c) 2021 SAP SE or an SAP affiliate company. All rights reserved.
 */
package org.expertsoft.training.controller;

import static org.expertsoft.training.constants.ConcertsConstants.PLATFORM_LOGO_CODE;

import de.hybris.platform.catalog.CatalogVersionService;
import org.expertsoft.training.facades.TourFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.expertsoft.training.service.ConcertsService;


@Controller
public class ConcertsController
{
	@Autowired
	private TourFacade tourFacade;
	@Autowired
	private CatalogVersionService catalogVersionService;

	private static final String CATALOG_ID = "concertToursProductCatalog";
	private static final String CATALOG_VERSION_NAME = "Online";

	@GetMapping("/{id}")
	public String getTour(@PathVariable(name = "id") final String id, final ModelMap model) {
		catalogVersionService.setSessionCatalogVersion(CATALOG_ID, CATALOG_VERSION_NAME);
		model.addAttribute("tour", tourFacade.getTourDetails(id));
		return "tourDetails";
	}
}
