# WeatherForecast
Weather Forecast Application

This application makes available a service that will fetch the average weather forecast of a city.

For this /data endpoint has been exposed which take city as a query parameter. It returns 3 values
1. Average of daily (6h-18h) temperature, in Celsius, for the following 3 days
2. Average of nightly (18h-6h) temperature, in Celsius, for the following 3 days
3. Average of pressure for the following 3 days

The services uses Open Weather Map's free to use APIs. The API provides a 
large amount of information for the weather every 3 hours, 
many of which are not required, hence not handled in current implementation. 

_Sample request for Open Weather API_
<https://samples.openweathermap.org/data/2.5/forecast?q=M%C3%BCnchen,DE&appid=b6907d289e10d714a6e88b30761fae22>

The API
also provides time which are not in local timezone. However the API provides geolocation
coordinates which enables us to fetch the correct timezone using Google's Timezone API.

_Sample request for timezone API_
<https://maps.googleapis.com/maps/api/timezone/json?location=38.908133,-77.047119&timestamp=1458000000>

_The application runs on port **7777.**_ The port can be changed from application.properties 

**Sample request**
<http://localhost:7777/data?city=Berlin>

```
{
    "statusCode": 200,
    "statusMessage": "Success",
    "forecastSummary": {
        "averagePressure": 950.52,
        "averageDailyTemperature": 25.999,
        "averageNightlyTemeperature": 26.201
    }
}
```

**Swagger Documentation**

<http://localhost:7777/swagger-ui.html>

JUNit and integrations tests are available in the test package.