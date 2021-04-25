package com.mulesoft;

import org.mule.runtime.api.meta.ExpressionSupport;
import org.mule.runtime.extension.api.annotation.Expression;
import org.mule.runtime.extension.api.annotation.param.Optional;
import org.mule.runtime.extension.api.annotation.param.Parameter;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;
import org.mule.runtime.extension.api.annotation.param.display.Example;

public class CurrencyExchangeParameters {
	@Parameter
	@Example("1000.00")
	@Optional(defaultValue = "1000.00")
	@DisplayName("Amount")
	@Expression(ExpressionSupport.SUPPORTED)
	private Double amount;

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}
}
