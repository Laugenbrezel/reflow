package controllers;

import java.net.MalformedURLException;

import org.ektorp.CouchDbConnector;
import org.ektorp.CouchDbInstance;
import org.ektorp.http.HttpClient;
import org.ektorp.http.StdHttpClient;
import org.ektorp.impl.StdCouchDbConnector;
import org.ektorp.impl.StdCouchDbInstance;

public class EktorpDb {

	private static CouchDbConnector DB = null;

	public static void initialize() {
		System.setProperty("org.ektorp.support.AutoUpdateViewOnChange", "true");
		HttpClient httpClient;
		try {
			httpClient = new StdHttpClient.Builder().url(
					"http://localhost:5984").build();

			CouchDbInstance dbInstance = new StdCouchDbInstance(httpClient);
			DB = new StdCouchDbConnector("reflow_dev", dbInstance);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static boolean isInitialized() {
		return DB != null;
	}

	public static CouchDbConnector getDb() {
		return DB;
	}
}
