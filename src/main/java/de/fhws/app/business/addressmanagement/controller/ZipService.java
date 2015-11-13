/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhws.app.business.addressmanagement.controller;

import java.io.IOException;
import java.io.StringReader;
import java.net.URLConnection;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.List;
import javax.json.Json;
import javax.json.JsonObject;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Matthias Reining
 */
public class ZipService {

    final String deutschePostUrl = "https://www.postdirekt.de/plzserver/PlzAjaxServlet";

    public String getCityByZip(String zip) {
        return getCityByZipFromDeutschePost(zip);
    }

    public String getCityByZipFromDeutschePost(String zip) {
        try {
            SSLContext sslContext = SSLContext.getInstance("TLSv1");
            TrustManager[] trustManagerArray = {new NoX509TrustManager()};
            sslContext.init(null, trustManagerArray, null);

            //?finda=streets&plz_plz=97491&lang=de_DE
            WebTarget target = ClientBuilder.newBuilder().sslContext(sslContext).build()
                    .target(deutschePostUrl)
                    .queryParam("finda", "city")
                    .queryParam("lang", "de_DE")
                    .queryParam("city", zip);
            Response postResponse = target.request(MediaType.TEXT_PLAIN).post(null, Response.class);

            if (postResponse.getStatus() != Response.Status.OK.getStatusCode()) {
                throw new RuntimeException(postResponse.readEntity(String.class));
            }

        //FIXME two cities at one zip are not supported here!!
            String respObj = postResponse.readEntity(String.class);
            System.out.println("result from DeutschePost");
            System.out.println(respObj);

            JsonObject data = Json.createReader(new StringReader(respObj)).readObject();

            try {
                //TODO please check for 97286 Sommerhausen and Winterhausen!
                return data.getJsonArray("rows").getJsonObject(0).getString("city");
            } catch (Exception e) {
                return "no valid zip code";
            }
        } catch (NoSuchAlgorithmException | KeyManagementException | RuntimeException e) {
            throw new RuntimeException(e);
        }

    }

    private class NoX509TrustManager implements X509TrustManager {

        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType)
                throws CertificateException {
        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType)
                throws CertificateException {
        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }

    }

}
