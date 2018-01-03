package com.sertac.sertac.havadurumu.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sertac.sertac.havadurumu.R;
import com.sertac.sertac.havadurumu.interfaces.RecyclerViewItemClickListener;
import com.sertac.sertac.havadurumu.network.network.open_weather_currently_network_model.CurrentlyWeatherStatus;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.TimeZone;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Response;


public class BaseFragmentCurrentlyRecyclerAdapter extends RecyclerView.Adapter<BaseFragmentCurrentlyRecyclerAdapter.BaseFragmentRecyclerViewHolder> {

    private Response<CurrentlyWeatherStatus> currentlyResponce;
    private Context context;
    private Fragment fragment;
    private long selectedUnixTime;

    public BaseFragmentCurrentlyRecyclerAdapter(Context context, Fragment fragment,Response<CurrentlyWeatherStatus> currentlyResponce)
    {
        this.currentlyResponce=currentlyResponce;
        this.context = context;
        this.fragment=fragment;
    }


    public void setUnixTime(long unixTime) {
        this.selectedUnixTime = unixTime;
    }

    @Override
    public BaseFragmentRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BaseFragmentRecyclerViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_base_fragment_currently_recycler_view,parent,false));
    }

    @Override
    public void onBindViewHolder(BaseFragmentRecyclerViewHolder holder, int position) {
        if (responseStringUnixTime(currentlyResponce.body().list.get(position).dt).equals(stringUnixTime())){
            baseFragmentListForecastIcon(currentlyResponce.body().list.get(position).weather.get(0).icon,holder.baseFragmentCurrentlyForecastIcon);
            holder.baseFragmentCurrentlyListDate.setText(baseFragmentCurrentlyListUnixConverter(currentlyResponce.body().list.get(position).dt));
            holder.baseFragmentCurrentlyTemperatures.setText(baseFragmentTemperatureConverter((int)currentlyResponce.body().list.get(position).main.temp));
        }
        else
            holder.setIsRecyclable(false);
    }

    @Override
    public int getItemCount() {
        return currentlyResponce.body().list.size();
    }

    private String stringUnixTime(){
        Date date = new Date(selectedUnixTime * 1000L);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        return simpleDateFormat.format(date);
    }

    private String responseStringUnixTime(long unixTime){
        Date date = new Date(unixTime * 1000L);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        return simpleDateFormat.format(date);
    }


    private String baseFragmentTemperatureConverter(int sicaklikCelcius) {
        return String.valueOf(sicaklikCelcius) + "\u2103";
    }

    private void baseFragmentListForecastIcon(String iconId, ImageView ımageView) {
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
        Picasso.with(context).load(stringStringHashMap.get(iconId)).into(ımageView);
    }

    private String baseFragmentCurrentlyListUnixConverter(long unixTime) {
        Date date = new Date(unixTime * 1000L);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        return simpleDateFormat.format(date);
    }


    public class BaseFragmentRecyclerViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.baseFragmentCurrentlyForecastIcon)
        ImageView baseFragmentCurrentlyForecastIcon;
        @BindView(R.id.baseFragmentCurrentlyListDate)
        TextView baseFragmentCurrentlyListDate;
        @BindView(R.id.baseFragmentCurrentlyTemperatures)
        TextView baseFragmentCurrentlyTemperatures;
            @BindView(R.id.baseFragmentCurrentlyCardView)
            CardView baseFragmentCurrentlyRecyclerCardView;
        private RecyclerViewItemClickListener recyclerViewItemClickListener;


        public BaseFragmentRecyclerViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            recyclerViewItemClickListener= (RecyclerViewItemClickListener) fragment;
        }

        @OnClick(R.id.baseFragmentCurrentlyCardView)
        public void recyclerCardViewClick(){
            recyclerViewItemClickListener.recyclerViewItemPosition(getAdapterPosition(),baseFragmentCurrentlyRecyclerCardView);
        }

    }
}
