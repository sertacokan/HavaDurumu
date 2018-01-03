package com.sertac.sertac.havadurumu.ui;


import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;


import com.sertac.sertac.havadurumu.R;
import com.sertac.sertac.havadurumu.adapter.AutoCompleteListAdapter;
import com.sertac.sertac.havadurumu.constants.Constants;
import com.sertac.sertac.havadurumu.interfaces.RecyclerViewItemClickListener;
import com.sertac.sertac.havadurumu.network.network.autocomplete_network_model.AutoCompletePlace;
import com.sertac.sertac.havadurumu.network.network.pixabay_network_model.Pixabay;
import com.sertac.sertac.havadurumu.network.network.retrofit_builders.AutoCompleteRetrofitBuilder;
import com.sertac.sertac.havadurumu.network.network.retrofit_builders.PixabayRetrofitBuilder;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends BaseActivity implements SearchView.OnQueryTextListener,RecyclerViewItemClickListener {

    @BindView(R.id.searchToolbar)
    Toolbar searchToolbar;
    @BindView(R.id.autoComleteList)
    RecyclerView autoCompleteList;
    private  Response<AutoCompletePlace> responses;
    @Override
    protected int layout() {
        return R.layout.activity_search;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        ButterKnife.bind(this);
        setSupportActionBar(searchToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        autoCompleteList.setLayoutManager(new LinearLayoutManager(SearchActivity.this,LinearLayoutManager.VERTICAL,false));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.search_toolbar_menu,menu);
        MenuItem menuItem=menu.findItem(R.id.arama);
        SearchView searchView= (SearchView) menuItem.getActionView();
        searchView.onActionViewExpanded();
        searchView.setOnQueryTextListener(this);
        return true;
    }

    private void retrofitBuilder(String locationName) {
        AutoCompleteRetrofitBuilder.getAutoCompleteServicesGenerator().getAutoCompleteList(locationName,Constants.AUTOCOMPLETE_PLACE_TYPE,Constants.AUTOCOMPLETE_LANGUAGE,
                Constants.AUTOCOMPLETE_API_KEY).enqueue(new Callback<AutoCompletePlace>() {
            @Override
            public void onResponse(Call<AutoCompletePlace> call, Response<AutoCompletePlace> response) {
            responses=response;
            autoCompleteList.setAdapter(new AutoCompleteListAdapter(response,SearchActivity.this));
            }

            @Override
            public void onFailure(Call<AutoCompletePlace> call, Throwable t) {

            }
        });
    }

    private void photoRequest(String locationName){
        PixabayRetrofitBuilder.getPixabayServiceGenerator().getPixabay(Constants.PIXABAY_API_KEY,locationName,Constants.PIXABAY_ORIENTATION,Constants.PIXABAY_CATEGORY,Constants.PIXABAY_IMAGE_TYPE)
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
    public boolean onQueryTextSubmit(String query) {
//        this.query = query;
//            retrofitBuilder(query.toUpperCase());

        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText)
    {
        retrofitBuilder(newText);
        return true;
    }

    @Override
    public void recyclerViewItemPosition(int position,View view) {
        Intent ıntent = new Intent();
        ıntent.putExtra("Result",responses.body().predictions.get(position).structured_formatting.main_text);
        setResult(RESULT_OK, ıntent);
        finish();
    }

    @Override
    public void recyclerViewItemLongClick(int position, View view) {

    }
}