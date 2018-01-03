package com.sertac.sertac.havadurumu.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sertac.sertac.havadurumu.R;


import com.sertac.sertac.havadurumu.adapter.BaseFragmentCurrentlyRecyclerAdapter;
import com.sertac.sertac.havadurumu.adapter.BaseFragmentWeeklyRecylerAdapter;
import com.sertac.sertac.havadurumu.constants.Constants;
import com.sertac.sertac.havadurumu.interfaces.RecyclerViewItemClickListener;
import com.sertac.sertac.havadurumu.network.network.open_weather_currently_network_model.CurrentlyWeatherStatus;
import com.sertac.sertac.havadurumu.network.network.open_weather_weekly_network_model.WeeklyWeatherStatus;
import com.sertac.sertac.havadurumu.network.network.pixabay_network_model.Pixabay;
import com.sertac.sertac.havadurumu.network.network.retrofit_builders.OpenWeatherCurrentlyRetrofitBuilder;
import com.sertac.sertac.havadurumu.network.network.retrofit_builders.OpenWeatherWeeklyRetrofitBuilder;
import com.sertac.sertac.havadurumu.network.network.retrofit_builders.PixabayRetrofitBuilder;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.TimeZone;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BaseFragment extends Fragment implements RecyclerViewItemClickListener {

    @BindView(R.id.currentlyForecastList)
    RecyclerView currentlyForecastList;
    @BindView(R.id.weeklyForecastList)
    RecyclerView weeklyForecastList;
    @BindView(R.id.baseFragmentLocation)
    TextView baseFragmentLocation;
    @BindView(R.id.baseFragmentIcon)
    ImageView baseFragmentIcon;
    @BindView(R.id.baseFragmentTemperature)
    TextView baseFragmentTemperature;
    @BindView(R.id.baseFragmentHumidity)
    TextView baseFragmentHumidity;
    @BindView(R.id.baseFragmentWind)
    TextView baseFragmentWind;
    @BindView(R.id.baseFragmentCloud)
    TextView baseFragmentCloud;
    @BindView(R.id.detailsLayout)
    LinearLayout detailsLayout;
    private Response<WeeklyWeatherStatus> weeklyResponse;
    private Response<CurrentlyWeatherStatus> currentlyResponse;
    private BaseFragmentCurrentlyRecyclerAdapter baseFragmentCurrentlyRecyclerAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_base, container, false);
        ButterKnife.bind(this, view);
        weeklyForecastList.setLayoutManager(new GridLayoutManager(getContext(),3));
        currentlyForecastList.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        if (getArguments() != null){
            String cityName=getArguments().getString("CityName");
            weeklyRetrofitBuilder(cityName);
            currentlyRetrofitBuider(cityName);
        }

        return view;
    }

    private void weeklyRetrofitBuilder(String cityName) {
        OpenWeatherWeeklyRetrofitBuilder.getWeeklyOpenWeatherServiceGenerator().getWeeklyWeatherStatus(cityName, Constants.ALINAN_GUN_SAYISI,Constants.OPEN_WEATHER_MODE,Constants.OPEN_WEATHER_UNITS ,Constants.OPEN_WEATHER_API_KEY).enqueue(new Callback<WeeklyWeatherStatus>() {
            @Override
            public void onResponse(Call<WeeklyWeatherStatus> call, Response<WeeklyWeatherStatus> response) {
            weeklyForecastList.setAdapter(new BaseFragmentWeeklyRecylerAdapter(response,getActivity(),BaseFragment.this));
            baseFragmentLocation.setText(response.body().city.name);
            weeklyResponse=response;
            }

            @Override
            public void onFailure(Call<WeeklyWeatherStatus> call, Throwable t) {

            }
        });
    }

    private void currentlyRetrofitBuider(String cityName){
        OpenWeatherCurrentlyRetrofitBuilder.getCurrentlyOpenWeatherServiceGenerator().getCurrentlyWeatherStatus(cityName,Constants.OPEN_WEATHER_MODE,
            Constants.OPEN_WEATHER_UNITS,Constants.OPEN_WEATHER_API_KEY).enqueue(new Callback<CurrentlyWeatherStatus>() {
            @Override
            public void onResponse(Call<CurrentlyWeatherStatus> call, Response<CurrentlyWeatherStatus> response) {
                baseFragmentCurrentlyRecyclerAdapter=new BaseFragmentCurrentlyRecyclerAdapter(getActivity(),BaseFragment.this,response);
                baseFragmentCurrentlyRecyclerAdapter.setUnixTime(weeklyResponse.body().list.get(0).dt);
                currentlyForecastList.setAdapter(baseFragmentCurrentlyRecyclerAdapter);
                baseFragmentMainForecastIcon(response.body().list.get(0).weather.get(0).icon);
                baseFragmentTemperature.setText(temperatureConverter((int)response.body().list.get(0).main.temp));
                baseFragmentHumidity.setText(String.valueOf(response.body().list.get(0).main.humidity));
                baseFragmentWind.setText(String.valueOf(response.body().list.get(0).wind.speed)+" m/s");
                baseFragmentCloud.setText(String.valueOf(response.body().list.get(0).clouds.all)+" %");
                currentlyResponse=response;
            }

            @Override
            public void onFailure(Call<CurrentlyWeatherStatus> call, Throwable t) {

            }
        });
    }


    private void photoRequest(String sehirAdi) {
        PixabayRetrofitBuilder.getPixabayServiceGenerator().getPixabay(Constants.PIXABAY_API_KEY, sehirAdi, Constants.PIXABAY_ORIENTATION, Constants.PIXABAY_CATEGORY, Constants.PIXABAY_IMAGE_TYPE)
                .enqueue(new Callback<Pixabay>() {
                    @Override
                    public void onResponse(Call<Pixabay> call, Response<Pixabay> response) {
                    }

                    @Override
                    public void onFailure(Call<Pixabay> call, Throwable t) {

                    }
                });
    }


    @Override
    public void recyclerViewItemPosition(int position,View view) {
        if (view.getId()==R.id.baseFragmentWeeklyRecyclerCardView){
//            baseFragmentMainForecastIcon(weeklyResponse.body().list.get(position).weather.get(0).icon);
//            baseFragmentTemperature.setText(temperatureConverter((int)weeklyResponse.body().list.get(position).temp.day));
//            baseFragmentHumidity.setText(String.valueOf(weeklyResponse.body().list.get(position).humidity));
//            baseFragmentWind.setText(String.valueOf(weeklyResponse.body().list.get(position).speed));
//            baseFragmentCloud.setText(String.valueOf(weeklyResponse.body().list.get(position).clouds));
            baseFragmentCurrentlyRecyclerAdapter.setUnixTime(weeklyResponse.body().list.get(position).dt);
            currentlyForecastList.setAdapter(baseFragmentCurrentlyRecyclerAdapter);
        }
        else if (view.getId()==R.id.baseFragmentCurrentlyCardView){
            baseFragmentMainForecastIcon(currentlyResponse.body().list.get(position).weather.get(0).icon);
            baseFragmentTemperature.setText(temperatureConverter((int)currentlyResponse.body().list.get(position).main.temp));
            baseFragmentHumidity.setText(String.valueOf(currentlyResponse.body().list.get(position).main.humidity)+" %");
            baseFragmentWind.setText(String.valueOf(currentlyResponse.body().list.get(position).wind.speed)+" m/s");
            baseFragmentCloud.setText(String.valueOf(currentlyResponse.body().list.get(position).clouds.all)+" %");
        }
    }

    @Override
    public void recyclerViewItemLongClick(int position, View view) {

    }

    private String fragmentUnixConverter(long unixTime) {
        Date date = new Date(unixTime * 1000L);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE,dd MMMM");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        return simpleDateFormat.format(date);
    }

    private String forecastTurkishConverter(String havaDurumu) {
        HashMap<String, String> stringHashMap = new HashMap<>();
        stringHashMap.put("Clear", "Açık");
        stringHashMap.put("Clouds", "Bulutlu");
        stringHashMap.put("Snow", "Kar Yağışı");
        stringHashMap.put("Rain", "Yağmurlu");
        stringHashMap.put("Thunderstorm", "Fırtınalı");
        stringHashMap.put("Atmosphere", "Kapalı");
        stringHashMap.put("Drizzle", "Çiseleyen Yağmur");
        return stringHashMap.get(havaDurumu);
    }

    private String temperatureConverter(int sicaklikCelcius) {
        return String.valueOf(sicaklikCelcius) + "\u2103";
    }

    private void baseFragmentMainForecastIcon(String iconId){
        HashMap<String, String> stringStringHashMap = new HashMap<>();
        stringStringHashMap.put("01d", "https://cdn4.iconfinder.com/data/icons/the-weather-is-nice-today/64/weather_3-256.png");
        stringStringHashMap.put("01n", "https://cdn4.iconfinder.com/data/icons/the-weather-is-nice-today/64/weather_4-256.png");
        stringStringHashMap.put("02d", "https://cdn4.iconfinder.com/data/icons/the-weather-is-nice-today/64/weather_2-256.png");
        stringStringHashMap.put("02n", "https://cdn4.iconfinder.com/data/icons/the-weather-is-nice-today/64/weather_5-256.png");
        stringStringHashMap.put("03d", "https://cdn4.iconfinder.com/data/icons/the-weather-is-nice-today/64/weather_1-256.png");
        stringStringHashMap.put("03n", "https://cdn4.iconfinder.com/data/icons/the-weather-is-nice-today/64/weather_1-256.png");
        stringStringHashMap.put("04d", "https://cdn4.iconfinder.com/data/icons/the-weather-is-nice-today/64/weather_1-256.png");
        stringStringHashMap.put("04n", "https://cdn4.iconfinder.com/data/icons/the-weather-is-nice-today/64/weather_1-256.png");
        stringStringHashMap.put("09d", "https://cdn4.iconfinder.com/data/icons/the-weather-is-nice-today/64/weather_17-256.png");
        stringStringHashMap.put("09n", "https://cdn4.iconfinder.com/data/icons/the-weather-is-nice-today/64/weather_18-256.png");
        stringStringHashMap.put("10d", "https://cdn4.iconfinder.com/data/icons/the-weather-is-nice-today/64/weather_17-256.png");
        stringStringHashMap.put("10n", "https://cdn4.iconfinder.com/data/icons/the-weather-is-nice-today/64/weather_18-256.png");
        stringStringHashMap.put("11d", "https://cdn4.iconfinder.com/data/icons/the-weather-is-nice-today/64/weather_24-256.png");
        stringStringHashMap.put("11n", "https://cdn4.iconfinder.com/data/icons/the-weather-is-nice-today/64/weather_25-256.png");
        stringStringHashMap.put("13d", "https://cdn4.iconfinder.com/data/icons/the-weather-is-nice-today/64/weather_36-256.png");
        stringStringHashMap.put("13n", "https://cdn4.iconfinder.com/data/icons/the-weather-is-nice-today/64/weather_37-256.png");
        stringStringHashMap.put("50d", "https://cdn4.iconfinder.com/data/icons/the-weather-is-nice-today/64/weather_49-256.png");
        stringStringHashMap.put("50n", "https://cdn4.iconfinder.com/data/icons/the-weather-is-nice-today/64/weather_50-256.png");
        Picasso.with(getContext()).load(stringStringHashMap.get(iconId)).into(baseFragmentIcon);
    }


}
