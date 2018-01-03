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
import com.sertac.sertac.havadurumu.network.network.open_weather_weekly_network_model.WeeklyWeatherStatus;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.TimeZone;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Response;

public class BaseFragmentWeeklyRecylerAdapter extends RecyclerView.Adapter<BaseFragmentWeeklyRecylerAdapter.BaseFragmentRecyclerViewHolder> {
    private Response<WeeklyWeatherStatus> weeklyResponse;
    private Context context;
    private Fragment fragment;

    public BaseFragmentWeeklyRecylerAdapter(Response<WeeklyWeatherStatus> weeklyResponse, Context context, Fragment fragment)
    {
        this.weeklyResponse = weeklyResponse;
        this.context = context;
        this.fragment=fragment;
    }

    @Override
    public BaseFragmentRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BaseFragmentRecyclerViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_base_fragment_weekly_recycler_view, parent, false));
    }

    @Override
    public void onBindViewHolder(BaseFragmentRecyclerViewHolder holder, int position) {
            baseFragmentListForecastIcon(weeklyResponse.body().list.get(position).weather.get(0).icon, holder.baseFragmentWeeklyForecastIcon);
            holder.baseFragmentWeeklyListDate.setText(baseFragmentWeeklyListUnixConverter(weeklyResponse.body().list.get(position).dt));
            holder.baseFragmentWeeklyTemperatures.setText(baseFragmentTemperatureConverter((int)weeklyResponse.body().list.get(position).temp.day));
    }

    @Override
    public int getItemCount() {
        return weeklyResponse.body().list.size();
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

    private String baseFragmentWeeklyListUnixConverter(long unixTime) {
        Date date = new Date(unixTime * 1000L);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        return simpleDateFormat.format(date);
    }

    public class BaseFragmentRecyclerViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.baseFragmentWeeklyForecastIcon)
        ImageView baseFragmentWeeklyForecastIcon;
        @BindView(R.id.baseFragmentWeeklyListDate)
        TextView baseFragmentWeeklyListDate;
        @BindView(R.id.baseFragmentWeeklyTemperatures)
        TextView baseFragmentWeeklyTemperatures;
            @BindView(R.id.baseFragmentWeeklyRecyclerCardView)
            CardView baseFragmentWeeklyRecyclerCardView;
        private RecyclerViewItemClickListener recyclerViewItemClickListener;


        public BaseFragmentRecyclerViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            recyclerViewItemClickListener= (RecyclerViewItemClickListener) fragment;
        }

        @OnClick(R.id.baseFragmentWeeklyRecyclerCardView)
        public void recyclerCardViewClick(){
            recyclerViewItemClickListener.recyclerViewItemPosition(getAdapterPosition(), baseFragmentWeeklyRecyclerCardView);
        }

    }
}
