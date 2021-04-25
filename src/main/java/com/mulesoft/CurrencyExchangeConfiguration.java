package com.mulesoft;

import org.mule.runtime.extension.api.annotation.Operations;
import org.mule.runtime.extension.api.annotation.connectivity.ConnectionProviders;

@Operations(CurrencyExchangeOperations.class)
@ConnectionProviders(CurrencyExchangeConnectionProvider.class)
public class CurrencyExchangeConfiguration {}
