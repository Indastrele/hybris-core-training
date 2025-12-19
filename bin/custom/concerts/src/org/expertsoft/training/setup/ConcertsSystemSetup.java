/*
 * Copyright (c) 2021 SAP SE or an SAP affiliate company. All rights reserved.
 */
package org.expertsoft.training.setup;

import de.hybris.platform.core.initialization.SystemSetup;
import org.expertsoft.training.constants.ConcertsConstants;
import org.expertsoft.training.service.ConcertsService;

import java.io.InputStream;

import static org.expertsoft.training.constants.ConcertsConstants.PLATFORM_LOGO_CODE;


@SystemSetup(extension = ConcertsConstants.EXTENSIONNAME)
public class ConcertsSystemSetup
{
	private final ConcertsService concertsService;

	public ConcertsSystemSetup(final ConcertsService concertsService)
	{
		this.concertsService = concertsService;
	}

	@SystemSetup(process = SystemSetup.Process.INIT, type = SystemSetup.Type.ESSENTIAL)
	public void createEssentialData()
	{
		concertsService.createLogo(PLATFORM_LOGO_CODE);
	}

	private InputStream getImageStream()
	{
		return ConcertsSystemSetup.class.getResourceAsStream("/concerts/sap-hybris-platform.png");
	}
}
