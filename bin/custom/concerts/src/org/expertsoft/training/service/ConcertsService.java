/*
 * Copyright (c) 2021 SAP SE or an SAP affiliate company. All rights reserved.
 */
package org.expertsoft.training.service;

public interface ConcertsService
{
	String getHybrisLogoUrl(String logoCode);

	void createLogo(String logoCode);
}
