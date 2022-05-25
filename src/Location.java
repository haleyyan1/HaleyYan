import org.json.JSONObject;

public class Location {
    private String name;
    private String region;
    private String temp;
    private String condition;
    private String wind;
    private String precip;
    private String humidity;
    private String realFeel;

    public Location(String response){
        JSONObject obj = new JSONObject(response);
        name = obj.getJSONObject("location").getString("name");
        region=obj.getJSONObject("location").getString("region");
        temp=obj.getJSONObject("current").getDouble("temp_c")+"°C";
        condition=obj.getJSONObject("current").getJSONObject("condition").getString("text");
        wind=obj.getJSONObject("current").getDouble("wind_kph")+" kilometers per hour";
        precip="Precipitation: "+obj.getJSONObject("current").getDouble("precip_mm")+" millimeters";
        humidity="Humidity: "+obj.getJSONObject("current").getInt("humidity");
        realFeel="Feels like "+obj.getJSONObject("current").getDouble("feelslike_c")+" °C";
    }
    public String toString(){
        return name+", "+region+"\n"+temp+"\n"+condition+"\n"+wind+"\n"+precip+"\n"+humidity+"\n"+realFeel;
    }
}
