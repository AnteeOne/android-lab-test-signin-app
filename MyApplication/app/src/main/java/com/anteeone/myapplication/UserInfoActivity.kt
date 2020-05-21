package com.anteeone.myapplication

import android.content.Intent
import android.content.IntentSender
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_user_info.*

class UserInfoActivity : AppCompatActivity() {

    val APP_PREFERENCES = "mysettings"
    val APP_PREFERENCES_ACTIVE_EMAIL = "activeemail"
    var usersDatabase = UsersDatabase()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_info)
        lateinit var pref: SharedPreferences
        pref = getSharedPreferences(APP_PREFERENCES, MODE_PRIVATE)
        if (pref.contains(APP_PREFERENCES_ACTIVE_EMAIL)) {

            val activeEmail = pref.getString(APP_PREFERENCES_ACTIVE_EMAIL, "0");
            if (usersDatabase.getDatabase().containsKey(activeEmail)){

                id_userfirstname.text = usersDatabase.getDatabase().get(activeEmail)?.firstName
                id_usersurname.text = usersDatabase.getDatabase().get(activeEmail)?.surName
                id_useremail.text = usersDatabase.getDatabase().get(activeEmail)?.email

            }

            id_logoutbutton.setOnClickListener {

                val editor = pref.edit()
                editor.putString(APP_PREFERENCES_ACTIVE_EMAIL, "0")
                editor.apply()
                val intent = Intent(this,MainActivity::class.java)
                startActivity(intent)

            }


        }
    }
}
