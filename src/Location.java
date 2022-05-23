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
        name = obj.getString("name");
        region=obj.getString("region");
        temp=obj.getString("temp_c")+"°C";
        condition=obj.getString("condition");
        wind=obj.getString("wind_kph")+" kilometers per hour";
        precip="Precipitation: "+obj.getDouble("precip_mm")+" millimeters";
        humidity="Humidity: "+obj.getInt("humidity");
        realFeel="Feels like "+obj.getDouble("feelslike_c")+" °C";
    }
    public String toString(){
        return name+", "+"\n"+temp+"\n"+condition+"\n"+wind+"\n"+precip+"\n"+humidity+"\n"+realFeel;
    }
}
