package com.prokarma.middleware.eeb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;

import org.jboss.com.sun.net.httpserver.HttpExchange;
import org.jboss.com.sun.net.httpserver.HttpHandler;
import org.jboss.com.sun.net.httpserver.HttpServer;

public class NotificationListener {

	public static void main(String[] args) throws IOException {
		createHttpEndpoint();
	}

	private static void createHttpEndpoint() throws IOException {
		int port = 7077;
		String contextPath = "/notification";

		HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);

		server.createContext(contextPath, new HttpRequestHandler());
		server.setExecutor(null); // creates a default executor
		server.start();
		System.out.println("Started HttpServer @ " + server.getAddress());
	}

	static class HttpRequestHandler implements HttpHandler {
		public void handle(HttpExchange t) throws IOException {
			System.out.println("Notification : \n" + convertToString(t.getRequestBody()));    	 
		}
	}

	private static String convertToString(InputStream is) {

		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();

		String line;
		try {

			br = new BufferedReader(new InputStreamReader(is));
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return sb.toString();
	}
}
