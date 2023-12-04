package com.example.sqllitedbapplication

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context, factory:SQLiteDatabase.CursorFactory?):
    SQLiteOpenHelper(context,DATABASE_NAME,factory,DATABASE_VERSION){

    companion object{
        const val DATABASE_NAME="user_data"
        const val DATABASE_VERSION=1
        const val TABLE_NAME="user"
        const val COL_ID="U_id"
        const val COL_FNAME="U_fname"
        const val COL_LNAME="U_lname"
        const val COL_AGE="U_age"
        const val COL_EMAIL="U_email"

    }

    override fun onCreate(db: SQLiteDatabase?) {
        val query_table =
            "CREATE TABLE" + TABLE_NAME + "(" + COL_ID + "INTEGER PRIMARY KEY AUTOINCREMENT" + COL_FNAME + "TEXT," + COL_LNAME + "TEXT," + COL_AGE + "INTEGER" + COL_EMAIL + "TEXT);"
        db?.execSQL(query_table)
    }

        fun AddUser(userFName:String,userLName:String,userAage:Int,userEmail:String){
            val context_value=ContentValues();
            context_value.put(COL_FNAME,userFName)
            context_value.put(COL_LNAME,userLName)
            context_value.put(COL_AGE,userAage)
            context_value.put(COL_EMAIL,userEmail)

            val db=this.writableDatabase
            db.insert(TABLE_NAME,null,context_value)
            db.close()
        }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }

    @SuppressLint("SuspiciousIndentation")
    fun getUsers(): Cursor? {
        val db=this.readableDatabase
        val cursor_dc=db.rawQuery("SELECT * FROM"+ TABLE_NAME,null)
            return cursor_dc
    }
    fun updateUser(id:Int,lname:String,Age:Int){
        val values =ContentValues()
        values.put(COL_LNAME,lname)
        values.put(COL_AGE,Age)

        val db=this.writableDatabase
        db.update(TABLE_NAME,values, COL_ID+"=?", arrayOf(id.toString()))
        db.close()
    }


}