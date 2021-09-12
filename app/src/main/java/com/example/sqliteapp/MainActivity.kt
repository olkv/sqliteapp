package com.example.sqliteapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import com.example.sqliteapp.db.MyDBManager

class MainActivity : AppCompatActivity() {

    val myDBManager = MyDBManager(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



    }

    fun onClickSave(view: android.view.View) {

        val editTitle = findViewById<EditText>(R.id.editTitle)
        val editContext = findViewById<EditText>(R.id.editContext)
        val textView = findViewById<TextView>(R.id.txtView)

        textView.text = ""

        myDBManager.openDB()

        myDBManager.insertToDB(editTitle.text.toString(), editContext.text.toString())

        var dataList = myDBManager.readDBData()
        for(item in dataList) {
            textView.append(item)
            textView.append("\n")
        }


    }

    override fun onDestroy() {
        super.onDestroy()

        myDBManager.closeDB()
    }

}