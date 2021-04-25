package com.mulesoft;

import java.util.HashSet;
import java.util.Set;

import org.mule.runtime.extension.api.annotation.error.ErrorTypeProvider;
import org.mule.runtime.extension.api.error.ErrorTypeDefinition;

public class CurrencyExchangeErrorTypeProvider implements ErrorTypeProvider {
	@Override
	public Set<ErrorTypeDefinition> getErrorTypes() {
		HashSet<ErrorTypeDefinition> errors = new HashSet<ErrorTypeDefinition>();
		errors.add(CurrencyExchangeErrorType.INVALID_REQUEST);
		errors.add(CurrencyExchangeErrorType.EMPTY_REQUEST);
		return errors;
	}
}
