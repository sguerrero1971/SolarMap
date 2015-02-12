package com.example.sguerrero.solarmap;


import android.content.Context;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

/**
 * Created by sguerrero on 11/28/14.
 */
public class eqmgmt_db extends SQLiteAssetHelper {

    //*Database Name *
    private static final String DATABASE_NAME = "eqmgmt.db";
    //*Database Version *
    private static final int DATABASE_VERSION = 1;
    //Primary key *
    public static final String Key_mine_id = "mine_id";
    //*Mine Name*
    public static final String Key_mine_name = "mine_name";
    //*Mine Cost Center*
    public static final String Key_mine_Cost_center = "mine_cost_center";
    //*table name*
    private static final String dataBase_table = "mine";

    public eqmgmt_db(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

}
