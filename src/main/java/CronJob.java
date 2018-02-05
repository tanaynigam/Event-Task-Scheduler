
import Utility.EventTaskHTTPClient;
import org.json.JSONObject;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class CronJob implements org.quartz.Job{
    public CronJob() {

    }

    public void execute(JobExecutionContext context) throws JobExecutionException {
        try {
            PublishEvents();
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }

    private void PublishEvents() throws Exception {
        // We have created an Auto Close HTTP Client which should close after communication has ended.
        EventTaskHTTPClient HTTPClient = new EventTaskHTTPClient();
        //Get latest events
        JSONObject value = HTTPClient.sendGet("http://localhost:8080/latestEvents");
        //Print out the events on the console
        System.out.println(value.toString(2));
        //Map the data and fire to EventBrite
        JSONObject EBresponse = EventBritePost.PostOnEventBrite(value);
        //EventBrite response
        System.out.println(EBresponse.toString(2));
    }
}
