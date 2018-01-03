package com.sertac.sertac.havadurumu.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;



import java.util.ArrayList;


public class BaseViewPagerAdapter extends FragmentStatePagerAdapter {

    private ArrayList<Fragment> followingCountries=new ArrayList<>();

    public BaseViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return followingCountries.get(position);
    }

    @Override
    public int getCount() {
        return followingCountries.size();
    }

    public void addCountry(Fragment countryName){
        if (followingCountries.size()<10){
            followingCountries.add(countryName);
            notifyDataSetChanged();
        }
    }


    public void removeCity(int countryPosition){
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
        followingCountries.remove(countryPosition);
    }


    public ArrayList<Fragment> getItemList(){

        return followingCountries;
    }

    public void setItemList(ArrayList<Fragment> followingCountrie){

        this.followingCountries = followingCountrie;
    }
}
