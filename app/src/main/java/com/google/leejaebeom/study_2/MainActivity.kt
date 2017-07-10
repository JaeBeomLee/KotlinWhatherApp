package com.google.leejaebeom.study_2

import android.app.DownloadManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.longToast
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread

class MainActivity : AppCompatActivity() {

    private val items = listOf(
            "Mon 6/23 - Sunny - 31/17",
            "Tue 6/24 - Cloudy - 23/6",
            "Wed 6/25 - Foggy - 25/10",
            "Thu 6/26 - Sunny - 30/20",
            "Fri 6/27 - Rainy - 20/5"
    )

    lateinit var list :RecyclerView
    lateinit var forecastResult :ForecastResult
    override fun onCreate(savedInstanceState: Bundle?) {
        val appKey ="cfef6258-e444-39c9-9eb8-862eedc1e87b"
        val version = 1
        val lat =  37.5714000000
        val lng = 126.9658000000
        val city = "서울"
        val county = "강남구"
        val village = "도곡동"
        val foretxt = "Y"
        val url = "http://apis.skplanetx.com/weather/forecast/6days?appKey=$appKey&version=$version&lat=$lat&lon=$lng&city=$city&county=$county&village=$village&foretxt=$foretxt"
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        doAsync() {
            forecastResult = Request(url).run()
            uiThread {
                longToast("Request performed")
                list = findViewById(R.id.main_list) as RecyclerView
                list.layoutManager = LinearLayoutManager(this@MainActivity)
                list.adapter = ForecastListAdapter(forecastResult, this@MainActivity)
            }
        }

    }
}
