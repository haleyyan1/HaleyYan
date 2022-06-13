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
    private String response;

    public Location(String res, String unit){
        response=res;
        JSONObject obj = new JSONObject(res);
        name = obj.getJSONObject("location").getString("name");
        region=obj.getJSONObject("location").getString("region");
        condition=obj.getJSONObject("current").getJSONObject("condition").getString("text");
        humidity="Humidity: "+obj.getJSONObject("current").getInt("humidity");
        if (unit.equals("Metric")){
        temp=obj.getJSONObject("current").getDouble("temp_c")+"°C";
        wind=obj.getJSONObject("current").getDouble("wind_kph")+" kilometers per hour";
        precip="Precipitation: "+obj.getJSONObject("current").getDouble("precip_mm")+" millimeters";
        realFeel="Feels like "+obj.getJSONObject("current").getDouble("feelslike_c")+" °C";}
        else{
            temp=obj.getJSONObject("current").getDouble("temp_f")+"°F";
            wind=obj.getJSONObject("current").getDouble("wind_mph")+" miles per hour";
            precip="Precipitation: "+obj.getJSONObject("current").getDouble("precip_in")+" inches";
            realFeel="Feels like "+obj.getJSONObject("current").getDouble("feelslike_f")+" °F";
        }
    }

    public String getResponse() {
        return response;
    }

    public String toString(){
        return name+", "+region+"\n\n"+temp+"\n\n"+condition+"\n\n"+wind+"\n\n"+precip+"\n\n"+humidity+"\n\n"+realFeel;
    }

}
