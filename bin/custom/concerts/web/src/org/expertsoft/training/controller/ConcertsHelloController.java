/*
 * Copyright (c) 2021 SAP SE or an SAP affiliate company. All rights reserved.
 */
package org.expertsoft.training.controller;

import static org.expertsoft.training.constants.ConcertsConstants.PLATFORM_LOGO_CODE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.expertsoft.training.service.ConcertsService;


@Controller
public class ConcertsHelloController
{
	@Autowired
	private ConcertsService concertsService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String printWelcome(final ModelMap model)
	{
		model.addAttribute("logoUrl", concertsService.getHybrisLogoUrl(PLATFORM_LOGO_CODE));
		return "welcome";
	}
}
