package phoenix.hackfest.orderit.Handlers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Jibin_ism on 26-May-15.
 */
public class NotificationDataHandler {

    public static final String NOTIFICATION_IMAGE_URL="notifImg";
    public static final String NOTIFICATION_TEXT="notifText";
    public static final String NOTIFICATION_ID="notifId";
    public static final String DATABASE_NAME = "sn_notifications";
    public static final String TABLE_NAME_DEFAULT = "notifs";
    public static final String TABLE_NAME_ALL="notifsAll";
    public static final int DATABASE_VERSION = 1;

    public static final String CREATE_TABLE_DEFAULT="CREATE TABLE IF NOT EXISTS "+TABLE_NAME_DEFAULT+"("+NOTIFICATION_ID+" INTEGER,"+NOTIFICATION_TEXT+" varchar(1000),"+NOTIFICATION_IMAGE_URL+" varchar(2000)"+")";
    public static final String CREATE_TABLE_ALL="CREATE TABLE IF NOT EXISTS "+TABLE_NAME_ALL+"("+NOTIFICATION_ID+" INTEGER,"+NOTIFICATION_TEXT+" varchar(1000),"+NOTIFICATION_IMAGE_URL+" varchar(2000)"+")";

    DataBaseHelper dbhelper;
    Context ctx;
    SQLiteDatabase db;

    public NotificationDataHandler(Context ctx) {
        this.ctx = ctx;
        dbhelper = new DataBaseHelper(ctx);
    }

    private static class DataBaseHelper extends SQLiteOpenHelper {

        private Context mcontext;

        public DataBaseHelper(Context ctx) {
            super(ctx, DATABASE_NAME, null, DATABASE_VERSION);
            this.mcontext=ctx;
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_TABLE_DEFAULT);
            db.execSQL(CREATE_TABLE_ALL);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            //TODO: Write UPDATE function
        }


    }


    public NotificationDataHandler open() {
        db = dbhelper.getWritableDatabase();
        return this;
    }

    public void close(){
        dbhelper.close();
    }

    public long insertData(String TABLE_NAME,String notificationId,String notificationText, String notificationImgurl){
        db.execSQL(CREATE_TABLE_DEFAULT);
        db.execSQL(CREATE_TABLE_ALL);

        ContentValues content=new ContentValues();
        content.put(NOTIFICATION_ID, notificationId);
        content.put(NOTIFICATION_TEXT, notificationText);
        content.put(NOTIFICATION_IMAGE_URL, notificationImgurl);
        Log.e("value insert",notificationText);
        return db.insertOrThrow(TABLE_NAME, null, content);
    }

    public ArrayList<Notifications> returnData(String TABLE_NAME){
        ArrayList<Notifications> notifications=new ArrayList<>();
        db.execSQL(CREATE_TABLE_DEFAULT);
        db.execSQL(CREATE_TABLE_ALL);
        Cursor c= db.query(TABLE_NAME, new String[]{NOTIFICATION_ID, NOTIFICATION_TEXT, NOTIFICATION_IMAGE_URL}, null, null, null, null,null);
        if(c.moveToFirst()) {
            do {
                Notifications notif = new Notifications();
//                notif.setNotificationId(c.getString(0));
//                notif.setNotificationText(c.getString(1));
//                notif.setNotificationImgUrl(c.getString(2));
                notifications.add(notif);
                Log.e("value return", c.getString(1));
            }while (c.moveToNext());
        }
        c.close();
        return notifications;
    }

    public void deleteTable(String TABLE_NAME){
        db.delete(TABLE_NAME, null, null);
    }
    public boolean deleteRow(String TABLE_NAME,int notificationId)
    {
        db.execSQL("DELETE FROM "+TABLE_NAME+" WHERE "+NOTIFICATION_ID+"='"+notificationId+"'");
        return true;
    }

    public class  Notifications{

    }


}
