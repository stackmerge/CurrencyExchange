package com.mulesoft;

import org.mule.runtime.extension.api.annotation.Configurations;
import org.mule.runtime.extension.api.annotation.Extension;
import org.mule.runtime.extension.api.annotation.dsl.xml.Xml;
import org.mule.runtime.extension.api.annotation.error.ErrorTypes;

@Xml(prefix = "currency-exchange")
@Extension(name = "Currency Exchange")
@Configurations(CurrencyExchangeConfiguration.class)
@ErrorTypes(CurrencyExchangeErrorType.class)
public class CurrencyExchangeExtension {}