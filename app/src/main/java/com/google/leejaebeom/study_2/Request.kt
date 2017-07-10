package com.google.leejaebeom.study_2

import android.util.Log
import org.json.JSONObject

import java.net.URL
import java.util.*

/**
 * Created by leejaebeom on 2017. 7. 10..
 */
class Request(val url : String){
    val num = 2
    fun run() : ForecastResult{
        val forecastJsonStr = URL(url).readText()
        val forecastJson: JSONObject = JSONObject(forecastJsonStr)
        val skyJSON:JSONObject = forecastJson.getJSONObject("weather").getJSONArray("forecast6days").getJSONObject(0).getJSONObject("sky")
        val tempJSON:JSONObject = forecastJson.getJSONObject("weather").getJSONArray("forecast6days").getJSONObject(0).getJSONObject("temperature")
        val cityJSON:JSONObject = forecastJson.getJSONObject("weather").getJSONArray("forecast6days").getJSONObject(0).getJSONObject("grid")
        val city:City = City(cityJSON.getString("city"), cityJSON.getString("county"), cityJSON.getString("village"))
        var num = 2

        var forecastArray: Array<Forecast?> = arrayOfNulls<Forecast>(9)
        var today:Calendar = Calendar.getInstance()
        for (i in 0..8){
            forecastArray[i] = Forecast((num+today.get(Calendar.DATE)).toString(), skyJSON.getString("amCode${num}day"), skyJSON.getString("amName${num}day"),skyJSON.getString("pmCode${num}day"), skyJSON.getString("pmName${num}day"), tempJSON.getString("tmax${num}day"), tempJSON.getString("tmin${num}day"))
            ++num
        }
        val forecastList :List<Forecast?> = forecastArray.toList()
        return ForecastResult(city, forecastList)
        Log.d(javaClass.simpleName, forecastJsonStr)
    }
}

data class ForecastResult(val city: City, val list: List<Forecast?>)
data class City(val city:String, val country:String, val village:String)
data class Forecast(val date: String, val amWeatherCode:String, val amWeather:String, val pmWeatherCode:String, val pmWeather:String, val maxTemp:String, val minTemp:String)

/*  결과 예제
* {
	"weather":{
		"forecast6days":[
			{
				"grid":{
				    "city":"서울","county":"종로구","village":"홍파동"
				},
				"sky":{
					"amCode2day":"SKY_W03","amName2day":"구름많음","pmCode2day":"SKY_W03","pmName2day":"구름많음",
					"amCode3day":"SKY_W03","amName3day":"구름많음","pmCode3day":"SKY_W03","pmName3day":"구름많음",
					"amCode4day":"SKY_W03","amName4day":"구름많음","pmCode4day":"SKY_W03","pmName4day":"구름많음",
					"amCode5day":"SKY_W03","amName5day":"구름많음","pmCode5day":"SKY_W03","pmName5day":"구름많음",
					"amCode6day":"SKY_W03","amName6day":"구름많음","pmCode6day":"SKY_W03","pmName6day":"구름많음",
					"amCode7day":"SKY_W09","amName7day":"구름많고 비","pmCode7day":"SKY_W09","pmName7day":"구름많고 비",
					"amCode8day":"SKY_W03","amName8day":"구름많음","pmCode8day":"SKY_W03","pmName8day":"구름많음",
					"amCode9day":"SKY_W03","amName9day":"구름많음","pmCode9day":"SKY_W03","pmName9day":"구름많음",
					"amCode10day":"SKY_W03","amName10day":"구름많음","pmCode10day":"SKY_W03","pmName10day":"구름많음"
				},
				"temperature":{
					"tmax2day":"31",
					"tmin2day":"24",
					"tmax3day":"31",
					"tmin3day":"24",
					"tmax4day":"31",
					"tmin4day":"24",
					"tmax5day":"31",
					"tmin5day":"24",
					"tmax6day":"30",
					"tmin6day":"24",
					"tmax7day":"30",
					"tmin7day":"24",
					"tmax8day":"31",
					"tmin8day":"24",
					"tmax9day":"31",
					"tmin9day":"24",
					"tmax10day":"29",
					"tmin10day":"25"
				},
				"timeRelease":"2017-07-09 18:00:00",
				"fcstext":{
					"text":"장마전선의 영향으로 12일은 제주도, 15일은 남부지방, 16일은 중부지방에 비가 오겠습니다. \n그 밖의 날은 북태평양고기압의 가장자리에 들어 구름이 많겠습니다.\n기온은 평년(최저기온: 19~23도, 최고기온: 25~31도)보다 조금 높겠습니다.\n강수량은 평년(4~20mm)과 비슷하거나 조금 많겠습니다.\n\n* 북태평양고기압의 확장 정도에 따라 장마전선의 예상위치와 강수영역이 달라질 수 있으니, 앞으로 발표되는 기상정보를 참고하기 바랍니다.",
					"timeRelease":"2017-07-09 18:00:00",
					"locationName":"전국"
				},
				"fcstextRegion":{
					"text":"장마전선의 영향으로 16일은 비가 오겠고, 그 밖의 날은 고기압의 가장자리에 들어 구름이 많겠습니다.\n기온은 평년(최저기온: 20~22도, 최고기온: 27~29도)보다 조금 높겠습니다. \n강수량은 평년(9~18mm)과 비슷하거나 조금 많겠습니다.\n서해중부해상의 물결은 1.0~2.0m로 일겠습니다.\n\n* 장마전선은 북태평양고기압의 확장정도에 따라 예상위치와 강수영역이 달라질 수 있으니 앞으로 발표되는 기상정보를 참고하기 바랍니다.",
					"timeRelease":"2017-07-09 18:00:00",
					"locationName":"서울.경기도"
				},
				"location":{
					"name":"서울"
				}
			}
		]
	},
	"common":{
		"alertYn":"Y",
		"stormYn":"N"
	},
	"result":{
		"code":9200,
		"requestUrl":"/weather/forecast/6days?lon=126.9658&village=도곡동&county=강남구&foretxt=Y&lat=37.5714&city=서울&version=1&appKey=cfef6258-e444-39c9-9eb8-862eedc1e87b",
		"message":"성공"
	}
}*/