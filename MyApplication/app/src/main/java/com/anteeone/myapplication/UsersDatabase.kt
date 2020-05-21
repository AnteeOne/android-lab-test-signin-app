package com.anteeone.myapplication

class UsersDatabase {


    fun getDatabase():Map<String,User>{


        val user1 = User("azatpscl@gmail.com","Azat","Gilyazov","NineZeroTwo902")
        val user2 = User("anime@master.ru","Alexey","Dyakonov","Qwerty-902")
        val user3 = User("coolfrontend@developer.com","Daniil","Trofimov","QWErty-902")
        val user4 = User("greatestwitcher@player.com","Ural","Sadretdinov","QWERTY-902")
        val usersDatabase = mapOf("azatpscl@gmail.com" to user1 ,
                                    "anime@master.ru" to user2 ,
                                    "coolfrontend@developer.com" to user3,
                                    "greatestwitcher@player.com" to user4)

        return usersDatabase

    }




}