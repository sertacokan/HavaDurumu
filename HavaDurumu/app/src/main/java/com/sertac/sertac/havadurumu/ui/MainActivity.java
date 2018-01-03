package com.sertac.sertac.havadurumu.ui;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import android.widget.Toast;

import com.sertac.sertac.havadurumu.R;
import com.sertac.sertac.havadurumu.adapter.BaseViewPagerAdapter;
import com.sertac.sertac.havadurumu.databases.Database;
import com.sertac.sertac.havadurumu.fragments.BaseFragment;

import java.util.ArrayList;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.relex.circleindicator.CircleIndicator;


public class MainActivity extends BaseActivity {

    private String arananSehirAdi;
    private Database database = new Database(this);
    private BaseViewPagerAdapter baseViewPagerAdapter=new BaseViewPagerAdapter(getSupportFragmentManager());

    @BindView(R.id.mainToolbar)
    Toolbar mainToolbar;
    @BindView(R.id.baseFragmentPager)
    ViewPager baseFragmentPager;
    @BindView(R.id.baseViewPagerIndicator)
    CircleIndicator baseViewPagerIndicator;
    @BindView(R.id.appName)
    TextView appName;

    @Override
    protected int layout() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        ButterKnife.bind(this);
        setSupportActionBar(mainToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        appName.setText(getResources().getString(R.string.app_name));
        baseFragmentPager.setAdapter(baseViewPagerAdapter);
    }


    @Override
    protected void onResume() {
        super.onResume();
        if (database.getCountryList().size()>0){
            if (baseViewPagerAdapter.getCount()==0){
                for (String cityName:database.getCountryList()){
                    BaseFragment baseFragment=new BaseFragment();
                    Bundle bundle=new Bundle();
                    bundle.putString("CityName",cityName);
                    baseFragment.setArguments(bundle);
                    baseViewPagerAdapter.addCountry(baseFragment);
                }
                baseViewPagerIndicator.setViewPager(baseFragmentPager);
                baseViewPagerAdapter.registerDataSetObserver(baseViewPagerIndicator.getDataSetObserver());
            }


        }
        else {

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                String resultData = data.getStringExtra("Result");
                if (database.getCountryList().contains(resultData))
                    Toast.makeText(this, "Aynı Şehri İki Kere Ekleyemezsiniz", Toast.LENGTH_SHORT).show();
                else {
                    BaseFragment baseFragment=new BaseFragment();
                    Bundle bundle=new Bundle();
                    bundle.putString("CityName",resultData);
                    baseFragment.setArguments(bundle);
                    baseViewPagerAdapter.addCountry(baseFragment);
                    database.addCountry(resultData);
                }
            }
        }
    }

    @OnClick(R.id.addNewCity)
    public void addNewCity(){
        Intent ıntent=new Intent(this,SearchActivity.class);
        startActivityForResult(ıntent,1);
    }

    @OnClick(R.id.deleteCity)
    public void deleteCity(){
        if (baseViewPagerAdapter.getItemList().size()>0){
            database.deleteCountry(baseViewPagerAdapter.getItemList().get(baseFragmentPager.getCurrentItem()).getArguments().getString("CityName"));
            baseViewPagerAdapter.removeCity(baseFragmentPager.getCurrentItem());
            ArrayList<Fragment> fragmentArrayList=baseViewPagerAdapter.getItemList();
            baseViewPagerAdapter.setItemList(fragmentArrayList);
            baseFragmentPager.setAdapter(baseViewPagerAdapter);
            baseViewPagerIndicator.setViewPager(baseFragmentPager);
        }
    }


// private ArrayList<Fragment> removeCountry(ArrayList<Fragment> followingCountries, int countryPosition) {
//        int i = 0;
//        Iterator<Fragment> item = followingCountries.iterator();
//        while (item.hasNext()) {
//            Fragment next = item.next();
//            if (i == countryPosition) {
//                item.remove();
//                break;
//            }
//            i++;
//        }
//        return followingCountries;
//    }

}
