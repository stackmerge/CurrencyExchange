package com.mulesoft;

import java.net.URLConnection;
import java.net.HttpURLConnection;
import java.net.URL;

public class CurrencyExchangeConnection {
	URLConnection con = null;

	public CurrencyExchangeConnection(String apiUrl, String apiKey) {
		try {
			con = new URL(apiUrl + "/latest?apikey=" + apiKey).openConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public URLConnection getConnection() {
		return this.con;
	}

	public void invalidate() {
		if (this.con != null)
			((HttpURLConnection) con).disconnect();

	}
}
