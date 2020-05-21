package com.anteeone.myapplication

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
class MainActivity : AppCompatActivity() {

    val APP_PREFERENCES = "mysettings"
    val APP_PREFERENCES_ACTIVE_EMAIL = "activeemail"
    var usersDatabase = UsersDatabase()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        lateinit var pref: SharedPreferences
        pref = getSharedPreferences(APP_PREFERENCES, MODE_PRIVATE)
        if (pref.contains(APP_PREFERENCES_ACTIVE_EMAIL)) {

            val active_email = pref.getString(APP_PREFERENCES_ACTIVE_EMAIL, "0");
            if(!active_email.equals("0")){
                val intent = Intent(this,UserInfoActivity::class.java)
                startActivity(intent)
            }

        }


        id_login.setOnClickListener {

            val emailString = id_email.text.toString()
            val passwordString = id_password.text.toString()
            val emailRegex = Regex(pattern = "^([a-z0-9_-]+\\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}\$")
            val passwordRegex = Regex(pattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z*]{6,}")

            if(passwordRegex.containsMatchIn(passwordString) && emailRegex.containsMatchIn(emailString))
                if(checkData(emailString,passwordString)){
                    val editor = pref.edit()
                    editor.putString(APP_PREFERENCES_ACTIVE_EMAIL, emailString)
                    editor.apply()
                    val intent = Intent(this,UserInfoActivity::class.java)
                    startActivity(intent)

                }
                else{
                    val status = "Incorrect email OR password"
                    Toast.makeText(this,status,Toast.LENGTH_SHORT).show();
                }

            else{

                val status_regexp = "Incorrect format of email OR password.Password must contains 6+ symbols ," +
                        " at least one number ,big char and lower char"
                Toast.makeText(this,status_regexp,Toast.LENGTH_LONG).show();

            }
        }

    }

    fun checkData(stringEmail : String , stringPassword : String):Boolean{

        if (usersDatabase.getDatabase().containsKey(stringEmail)){

            return usersDatabase.getDatabase().getValue(stringEmail).password.equals(stringPassword)
        }
        else{

            return false

        }



    }


}
