package com.sertac.sertac.havadurumu.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sertac.sertac.havadurumu.R;
import com.sertac.sertac.havadurumu.interfaces.RecyclerViewItemClickListener;
import com.sertac.sertac.havadurumu.network.network.autocomplete_network_model.AutoCompletePlace;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Response;

/**
 * Created by sertac on 15.11.2017.
 */

public class AutoCompleteListAdapter extends RecyclerView.Adapter<AutoCompleteListAdapter.AutoCompleteViewHolder> {

    private  Response<AutoCompletePlace> response;
    private Context context;
    private RecyclerViewItemClickListener recyclerViewItemClickListener;

    public AutoCompleteListAdapter(Response<AutoCompletePlace> response, Context context){
        this.response=response;
        this.context=context;
    }

    @Override
    public AutoCompleteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new AutoCompleteViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_complete_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(AutoCompleteViewHolder holder, int position) {
        holder.locationName.setText(response.body().predictions.get(position).structured_formatting.main_text);
    }

    @Override
    public int getItemCount() {
        return response.body().predictions.size();
    }

    public class AutoCompleteViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.locationName)
        TextView locationName;


        public AutoCompleteViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        recyclerViewItemClickListener= (RecyclerViewItemClickListener) context;
        }

        @OnClick(R.id.autoCompleteCardView)
        public void autoCompleteCardViewClick(){
            recyclerViewItemClickListener.recyclerViewItemPosition(getAdapterPosition(),locationName);
        }

    }
}
