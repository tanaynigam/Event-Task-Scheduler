package Utility;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class EventTaskHTTPClient {

    private final String USER_AGENT = "SCHEDULER_HTTP_CLIENT";
    private final CloseableHttpClient client = HttpClientBuilder.create().build();

    // HTTP GET request
    public JSONObject sendGet(String url) throws Exception {

        HttpGet request = new HttpGet(url);

        // add request header
        request.addHeader("User-Agent", USER_AGENT);

        HttpResponse response = client.execute(request);

        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " +
                response.getStatusLine().getStatusCode());

        BufferedReader rd = new BufferedReader(
                new InputStreamReader(response.getEntity().getContent()));

        StringBuffer result = new StringBuffer();
        String line = "";
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }

        JSONObject json_result = new JSONObject(result.toString());
        return json_result;
    }

    // HTTP POST request
    public JSONObject sendPost(String url,String AuthorizationHeader,List<NameValuePair> urlParameters) throws Exception {

        HttpPost post = new HttpPost(url);

        // add header
        if(AuthorizationHeader == null) {
            post.setHeader("User-Agent", USER_AGENT);
        } else {
            post.setHeader("User-Agent", USER_AGENT);
            post.setHeader("Authorization",AuthorizationHeader);
        }

        //To test if this Post works
        if(urlParameters == null) {
            urlParameters = new ArrayList<NameValuePair>();
            urlParameters.add(new BasicNameValuePair("sn", "C02G8416DRJM"));
            urlParameters.add(new BasicNameValuePair("cn", ""));
            urlParameters.add(new BasicNameValuePair("locale", ""));
            urlParameters.add(new BasicNameValuePair("caller", ""));
            urlParameters.add(new BasicNameValuePair("num", "12345"));
        }

        post.setEntity(new UrlEncodedFormEntity(urlParameters));

        HttpResponse response = client.execute(post);
        System.out.println("\nSending 'POST' request to URL : " + url);
        System.out.println("Post parameters : " + post.getEntity());
        System.out.println("Response Code : " +
                response.getStatusLine().getStatusCode());

        BufferedReader rd = new BufferedReader(
                new InputStreamReader(response.getEntity().getContent()));

        StringBuffer result = new StringBuffer();
        String line = "";
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }

        JSONObject json_result = new JSONObject(result.toString());
        return json_result;
    }

    // To test if POSTs work
    public JSONObject sendPost(String url) throws Exception {
        return sendPost(url,null,null);
    }

    public JSONObject sendPost(String url,List<NameValuePair>  payload ) throws Exception {
        return sendPost(url,null, payload);
    }
}
