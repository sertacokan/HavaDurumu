package com.sertac.sertac.havadurumu.interfaces;


import android.view.View;

public interface RecyclerViewItemClickListener {

 void recyclerViewItemPosition(int position,View view);
 void recyclerViewItemLongClick(int position, View view);
}
