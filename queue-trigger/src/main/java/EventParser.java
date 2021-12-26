import org.json.JSONArray;
import org.json.JSONObject;

public class EventParser {

    public String getObjectKey(String eventJson) {
        JSONObject json = new JSONObject(eventJson);
        return json.getJSONArray("messages")
                .getJSONObject(0)
                .getJSONObject("details")
                .getJSONObject("message")
                .getString("body");
    }
}
