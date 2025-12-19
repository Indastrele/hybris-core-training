/*
 * Copyright (c) 2021 SAP SE or an SAP affiliate company. All rights reserved.
 */
package com.expertsoft.training.service;

public interface BandsLogoService
{
	String getHybrisLogoUrl(String logoCode);

	void createLogo(String logoCode);
}
