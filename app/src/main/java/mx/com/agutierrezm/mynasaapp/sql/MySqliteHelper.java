package mx.com.agutierrezm.mynasaapp.sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by alegutim on 18/08/16.
 */
public class MySqliteHelper  extends SQLiteOpenHelper {
    private final static String DATABASE_NAME  = "apod_msqlite";
    private final static int DATABASE_VERSION=1;
    public static final String TABLE_NAME_APP = "APP_TABLE";
    public static final String COLUMN_ID_APP = BaseColumns._ID;
    public static final String COLUMN_APP_IMAGE= "IMAGE";

    private static final String CREATE_TABLE_APPS = "create table " + TABLE_NAME_APP
            + " ( " + COLUMN_ID_APP + " integer primary key autoincrement ," +
            COLUMN_APP_IMAGE + " text not null )";


    public MySqliteHelper(Context context) {
        super(context,DATABASE_NAME , null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_APPS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
