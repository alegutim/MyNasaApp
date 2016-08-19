package mx.com.agutierrezm.mynasaapp.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import mx.com.agutierrezm.mynasaapp.model.favorite;

/**
 * Created by alegutim on 18/08/16.
 */
public class AppDataSource {
    private final SQLiteDatabase db;

    public AppDataSource(Context context) {
        MySqliteHelper helper = new MySqliteHelper(context);
        db = helper.getWritableDatabase();
    }


    public void saveFavorite(String url) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(MySqliteHelper.COLUMN_APP_IMAGE, url);
        db.insert(MySqliteHelper.TABLE_NAME_APP, null, contentValues);
    }

    public List<favorite> getAllItems(){
        List<favorite> favoriteList = new ArrayList<>();
        Cursor cursor = db.query(MySqliteHelper.TABLE_NAME_APP,null,null,null,null,null,null);
        while (cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(MySqliteHelper.COLUMN_ID_APP));
            String appImage = cursor.getString(cursor.getColumnIndexOrThrow(MySqliteHelper.COLUMN_APP_IMAGE));
            favorite modelFavorite = new favorite();
            modelFavorite.setId(id);
            modelFavorite.setUrl(appImage);
            favoriteList.add(modelFavorite);
        }
        return favoriteList;
    }


}
