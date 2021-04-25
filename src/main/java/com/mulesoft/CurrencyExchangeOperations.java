package com.mulesoft;

import static org.mule.runtime.extension.api.annotation.param.MediaType.ANY;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import org.mule.runtime.extension.api.annotation.error.Throws;
import org.mule.runtime.extension.api.annotation.param.Connection;
import org.mule.runtime.extension.api.annotation.param.MediaType;
import org.mule.runtime.extension.api.annotation.param.ParameterGroup;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;
import org.mule.runtime.extension.api.exception.ModuleException;

public class CurrencyExchangeOperations {

	@MediaType(value = ANY, strict = false)
	@DisplayName("INR To USD")
	@Throws(CurrencyExchangeErrorTypeProvider.class)
	public Double inrToUsd(@Connection CurrencyExchangeConnection connection,
			@ParameterGroup(name = "InputParameters") CurrencyExchangeParameters param) {
		Double result = null;
		try {
			result = param.getAmount() / getExchangeRate("INR", connection);
		} catch (IOException e) {
			throw new ModuleException(CurrencyExchangeErrorType.INVALID_REQUEST, e);
		}
		return result;
	}

	private static Double getExchangeRate(String currencyCode, CurrencyExchangeConnection connection)
			throws IOException {

		HttpURLConnection con = (HttpURLConnection) connection.getConnection();
		con.setRequestMethod("GET");
		StringBuilder response = new StringBuilder();
		if (con.getResponseCode() == 200) { // success
			BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			while ((inputLine = br.readLine()) != null) {
				response.append(inputLine);
			}
			br.close();
		} else {
			System.out.println("GET request failed.");
		}
		String[] arrOfStr = response.toString().replaceAll("\"", "").split(",");

		Double exchangeRate = null;
		for (String a : arrOfStr) {
			if (a.contains(currencyCode))
				exchangeRate = Double.parseDouble(a.split(":")[1]);
		}
		return exchangeRate;
	}
}
