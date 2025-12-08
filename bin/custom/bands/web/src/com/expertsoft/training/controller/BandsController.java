/*
 * Copyright (c) 2021 SAP SE or an SAP affiliate company. All rights reserved.
 */
package com.expertsoft.training.controller;

import com.expertsoft.training.facades.BandFacade;
import de.hybris.platform.catalog.CatalogVersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
public class BandsController
{
	@Autowired
	private BandFacade bandFacade;
	@Autowired
	private CatalogVersionService catalogVersionService;

	private static final String CATALOG_ID = "concertToursProductCatalog";
	private static final String CATALOG_VERSION_NAME = "Online";

	@GetMapping(value = "/")
	public String getBandsList(final ModelMap model) {

		model.addAttribute("bands", bandFacade.getBands());
		return "bandList";
	}

	@GetMapping(value = "/{code}")
	public String getBand(@PathVariable(name = "code") String code, final ModelMap model) {
		catalogVersionService.setSessionCatalogVersion(CATALOG_ID, CATALOG_VERSION_NAME);
		model.addAttribute("band", bandFacade.getBand(code));
		return "bandDetails";
	}
}
