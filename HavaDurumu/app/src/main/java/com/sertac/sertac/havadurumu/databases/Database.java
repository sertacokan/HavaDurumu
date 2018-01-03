package com.sertac.sertac.havadurumu.databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.sertac.sertac.havadurumu.models.MainGridModel;

import java.util.ArrayList;

public class Database extends SQLiteOpenHelper {

    private static final String DATABASE_NAME="WeatherDatabase";
    private static final String DATABASE_TABLE="WeatherCountriesTable";
    private static final String ROW_ID="id";
    private static final String CITY_NAMES ="CityNames";
    private static final String ACTIVITIES_DATE ="Date";
    private static final String ACTIVITIES ="Activities";
    private static final String ACTIVITIES_STATUS="Status";
    private static final int VERSION_NUMBER=6;
    private SQLiteDatabase sqLiteDatabase;
    public Database(Context context) {
        super(context, DATABASE_NAME, null,VERSION_NUMBER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE "+DATABASE_TABLE+"( "
                                                 +ROW_ID + " INTEGER PRIMARY KEY, "
                                                 + CITY_NAMES + " TEXT,"
                                                 + ACTIVITIES_DATE + " TEXT,"
                                                 + ACTIVITIES + " TEXT,"
                                                 + ACTIVITIES_STATUS +" INTEGER NOT NULL DEFAULT 0)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " +DATABASE_TABLE);
        onCreate(db);
    }

    public void addCountry(String countryName){
        sqLiteDatabase=getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(CITY_NAMES,countryName);
        sqLiteDatabase.insert(DATABASE_TABLE,null,contentValues);
        close();
    }

    public void addNote(String activity,String date,String cityName){
        sqLiteDatabase=getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(ACTIVITIES,activity);
        contentValues.put(ACTIVITIES_DATE,date);
        contentValues.put(CITY_NAMES,cityName);
        sqLiteDatabase.insert(DATABASE_TABLE,null,contentValues);
        close();
    }

    public ArrayList<String> getCountryList(){
        sqLiteDatabase=getReadableDatabase();
        ArrayList<String> countriesList=new ArrayList<>();
        Cursor cursor=sqLiteDatabase.query(DATABASE_TABLE,new String[]{CITY_NAMES},null,null,null,null,null);
        while (cursor.moveToNext()){
            countriesList.add(cursor.getString(cursor.getColumnIndex(CITY_NAMES)));
        }
        cursor.close();
        close();
        return countriesList;
    }

    public ArrayList<MainGridModel> getActivities(){
        ArrayList<MainGridModel> activityList=new ArrayList<>();
        Cursor cursor=sqLiteDatabase.query(DATABASE_TABLE,new String[]{CITY_NAMES,ACTIVITIES_DATE,ACTIVITIES,ACTIVITIES_STATUS},null,null,null,null,null);
        while (cursor.moveToNext()){
         activityList.add(new MainGridModel(cursor.getString(cursor.getColumnIndex(CITY_NAMES)),
                 cursor.getString(cursor.getColumnIndex(ACTIVITIES)),
                 cursor.getString(cursor.getColumnIndex(ACTIVITIES_DATE))));
        }
        cursor.close();
        close();
        return activityList;
    }
    public void deleteCountry(String deletedCountryName){
        sqLiteDatabase=getWritableDatabase();
        sqLiteDatabase.delete(DATABASE_TABLE, CITY_NAMES +"=?",new String[]{deletedCountryName});
        close();
    }

}
