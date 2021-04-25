package com.mulesoft;

import java.io.IOException;
import java.net.HttpURLConnection;
import org.mule.runtime.api.connection.ConnectionException;
import org.mule.runtime.api.connection.ConnectionProvider;
import org.mule.runtime.api.connection.ConnectionValidationResult;
import org.mule.runtime.extension.api.annotation.param.Parameter;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;
import org.mule.runtime.extension.api.annotation.param.display.Example;
import org.mule.runtime.extension.api.annotation.param.display.Placement;

public class CurrencyExchangeConnectionProvider implements ConnectionProvider<CurrencyExchangeConnection> {

	@Parameter
	@DisplayName("ExchangeRate API Host")
	@Example("https://api.currencyfreaks.com")
	@Placement(order = 1)
	private String apiUrl;

	@Parameter
	@DisplayName("ExchangeRate API Key")
	@Example("62d0d90e107949bd86e03a36f0f168da")
	@Placement(order = 2)
	private String apiKey;

	@Override
	public CurrencyExchangeConnection connect() throws ConnectionException {
		return new CurrencyExchangeConnection(this.apiUrl,this.apiKey);
	}

	@Override
	public void disconnect(CurrencyExchangeConnection connection) {
		connection.invalidate();
	}

	@Override
	public ConnectionValidationResult validate(CurrencyExchangeConnection connection) {
		ConnectionValidationResult result = null;
		try {
			result = (((HttpURLConnection) connection.getConnection()).getResponseCode() == 200
					? ConnectionValidationResult.success()
					: ConnectionValidationResult.failure("Connection Failed", new Exception()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
}
