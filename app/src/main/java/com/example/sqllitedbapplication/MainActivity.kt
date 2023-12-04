package com.example.sqllitedbapplication

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }
    fun add_user(view: View){
        val db=DBHelper(this@MainActivity,null)
        val fname= findViewById<EditText>(R.id.fname).text.toString()
        val Lname= findViewById<EditText>(R.id.lname).text.toString()
        val age= findViewById<EditText>(R.id.age).text.toString().toInt()
        val email= findViewById<EditText>(R.id.email).text.toString()

        db.AddUser(fname,Lname,age,email)
        Toast.makeText(this@MainActivity,"user add successfully",Toast.LENGTH_LONG).show()


    }
    @SuppressLint("Range")
    fun get_user(view: View){
        val fname= findViewById<EditText>(R.id.fname).text.toString()
        val allusers=findViewById<TextView>(findViewById(R.id.alluser))
        val db=DBHelper(this@MainActivity,null)
        val userCursor=db.getUsers()
        userCursor!!.moveToFirst()
        do{
            userCursor.getString(userCursor.getColumnIndex(DBHelper.COL_FNAME))
            allusers.append(fname)
        }while (userCursor.moveToNext())

    }
    fun update_user(view:View){
        val db=DBHelper(this@MainActivity,null)
        val lname=findViewById<EditText>(R.id.lname).text.toString()
        val age=findViewById<EditText>(R.id.age).text.toString().toInt()
        db.updateUser(1,lname,age)


    }

}