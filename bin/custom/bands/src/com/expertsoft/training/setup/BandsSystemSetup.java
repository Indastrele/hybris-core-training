/*
 * Copyright (c) 2021 SAP SE or an SAP affiliate company. All rights reserved.
 */
package com.expertsoft.training.setup;

import static com.expertsoft.training.constants.BandsConstants.PLATFORM_LOGO_CODE;

import de.hybris.platform.core.initialization.SystemSetup;

import java.io.InputStream;

import com.expertsoft.training.constants.BandsConstants;
import com.expertsoft.training.service.BandsService;


@SystemSetup(extension = BandsConstants.EXTENSIONNAME)
public class BandsSystemSetup
{
	private final BandsService bandsService;

	public BandsSystemSetup(final BandsService bandsService)
	{
		this.bandsService = bandsService;
	}

	@SystemSetup(process = SystemSetup.Process.INIT, type = SystemSetup.Type.ESSENTIAL)
	public void createEssentialData()
	{
		bandsService.createLogo(PLATFORM_LOGO_CODE);
	}

	private InputStream getImageStream()
	{
		return BandsSystemSetup.class.getResourceAsStream("/bands/sap-hybris-platform.png");
	}
}
