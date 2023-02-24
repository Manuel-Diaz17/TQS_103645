/*
 * Copyright 2015-2022 the original author or authors.
 *
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v2.0 which
 * accompanies this distribution and is available at
 *
 * http://www.eclipse.org/legal/epl-v20.html
 */

package com.example.project;


import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class StocksPortfolioTest {

	@Mock
    IStockmarketService market;

    @InjectMocks
    StocksPortfolio portfolio;

	@Test
    public void GetTotalValue_Test() {
        IStockmarketService mockStockMarket = mock(IStockmarketService.class);
        StocksPortfolio portfolio = new StocksPortfolio(mockStockMarket);

        when(mockStockMarket.lookUpPrice("SAMSUNG")).thenReturn(2.0);
        when(mockStockMarket.lookUpPrice("APPLE")).thenReturn(5.0);

        portfolio.addStock(new Stock("SAMSUNG",2));
        portfolio.addStock(new Stock("APPLE", 5));

        assertThat(portfolio.getTotalValue(),is(29.0));
        Mockito.verify(mockStockMarket, Mockito.times(2)).lookUpPrice(Mockito.anyString());
    }


}
