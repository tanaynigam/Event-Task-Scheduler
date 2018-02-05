import Utility.EventTaskHTTPClient;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class EventBritePost {
    private static final String endpoint= "https://www.eventbriteapi.com/v3/events/";
    private static final String eventBriteKey = "BV5QJ7WYFQSLDAQNSQW4";

    public static JSONObject PostOnEventBrite(JSONObject payload) throws Exception {
        EventTaskHTTPClient EBClient = new EventTaskHTTPClient();
        String authorizationHeader = "Bearer "+eventBriteKey;

        // Create the Payload
        List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
        urlParameters.add(new BasicNameValuePair("event.name.html", payload.getString("name")));
        urlParameters.add(new BasicNameValuePair("event.start.utc", payload.getString("start")));
        urlParameters.add(new BasicNameValuePair("event.end.utc", payload.getString("end")));
        urlParameters.add(new BasicNameValuePair("event.start.timezone", payload.getString("timezone")));
        urlParameters.add(new BasicNameValuePair("event.end.timezone", payload.getString("timezone")));
        urlParameters.add(new BasicNameValuePair("event.currency", payload.getString("currency")));
        urlParameters.add(new BasicNameValuePair("event.capacity", payload.getString("capacity")));

        return  EBClient.sendPost(endpoint,authorizationHeader,urlParameters);
    }
}
