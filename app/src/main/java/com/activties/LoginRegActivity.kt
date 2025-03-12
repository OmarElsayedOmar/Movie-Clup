package com.activties

import android.content.Intent
import android.content.IntentFilter
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.StrictMode
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import com.helpel.ArticalDbHleper
import com.helpel.MyBrodcastReceiver
import okhttp3.OkHttpClient
import java.io.InputStreamReader
import java.net.URL


class LoginRegActivity :AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        addListener()
        service()
        BrodcastReceiver()

    }

    private fun BrodcastReceiver() {
        val BrodcastReceiver = MyBrodcastReceiver()
        val filter = IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED)
        registerReceiver(BrodcastReceiver, filter)
    }

    private fun service() {
        val intent = Intent(this, MyService::class.java)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForegroundService(intent)
        } else {
            startService(intent)
        }
    }

    private fun addListener() {
        val button = findViewById<View>(R.id.login_button)
        Snackbar.make(button, "hello", Snackbar.LENGTH_SHORT).show()

        binding.loginButton.setOnClickListener {

            val intent=Intent(this,MoviesActivty::class.java)
            startActivity(intent)

            /*
            val myDialog = AlertDialog.Builder(this)
            myDialog.setMessage("Hello in My New App")
                .setCancelable(false)
                .setPositiveButton("Ok", DialogInterface.OnClickListener {

                        dialog, id ->
                    finish()
                })
                .setNegativeButton("Cancel", DialogInterface.OnClickListener {

                        dialog, id ->
                    dialog.cancel()
                })
            val alert = myDialog.create()
               alert.setTitle("HI")
            alert.setIcon(R.drawable.facebook_login)
            alert.show()

             */
        }


        /*
            //readdata()
           makeareqrest()
            val Fristname=binding.Firstname.text.toString()
            val Lastname=binding.Lastname.text.toString()
            val entry=ContentValues().apply {
                put(DB.DB_Title,Fristname)
                put(DB.body,Lastname)
            }
            databaseHelper.writableDatabase.insert(DB.Table_Name,null,entry)

             */
        binding.facebook.setOnClickListener {
            val vistaFacebook = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/"))
            startActivity(vistaFacebook)
        }
        binding.Google.setOnClickListener {
            val vistaGoogle = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.gmail.com/"))
            startActivity(vistaGoogle)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return  when (item.itemId) {
            R.id.add -> {
                Toast.makeText(this, "add", Toast.LENGTH_SHORT).show()
                true
            }

            R.id.delete -> {
                Toast.makeText(this, "delete", Toast.LENGTH_SHORT).show()
                true
            }

            R.id.search -> {
                Toast.makeText(this, "Search", Toast.LENGTH_SHORT).show()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }





    private fun makeareqrest() {

        val policy=StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        val url=URL("https://v2.jokeapi.dev/joke/Any")
        val connection= url.openConnection()
        val inputStream =  connection.getInputStream()
        val inputStreamReader = InputStreamReader (inputStream)
        val result=inputStreamReader.readText()
        binding.QUILTY.text=result
    }
/*
    private fun getSms(){
        val Uri= Uri.parse("content://sms/inbox")
        val projection= arrayOf(SMS_ADDRESS, SMS_BODY)
        val cursor:Cursor=contentResolver.query(Uri,projection,null,null,null)!!
        while (cursor.moveToNext()){
                for (i in 0 until cursor.columnCount){
                    Log.i(LOG_TAG, "$i- ${cursor.getColumnName(i)} - ${cursor.getString(i)}")

                }
        }


    }
     private fun readdata() {
        val cursor =databaseHelper.readableDatabase.rawQuery("SELECT * FROM  ${DB.Table_Name}",
            arrayOf<String>()
        )
while(cursor.moveToNext()){

    val id =cursor.getInt(0)
    val Fristname=cursor.getString(1)
    val Lastname=cursor.getString(2)
    Log.d("Main_activity", "$id-$Fristname-$Lastname")

}
    }
*/



    companion object {
        private const val LOG_TAG = "MAIN"
        private const val SMS_BODY="body"
        private const val SMS_ADDRESS="address"
    }



}