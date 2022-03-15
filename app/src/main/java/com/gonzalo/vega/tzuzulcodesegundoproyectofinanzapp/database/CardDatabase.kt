package com.gonzalo.vega.tzuzulcodesegundoproyectofinanzapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.gonzalo.vega.tzuzulcodesegundoproyectofinanzapp.models.Card

@Database(entities = [Card::class], version = 1, exportSchema = false) //exportSchema is for logs
abstract class CardDatabase : RoomDatabase() {
    abstract val cardDao: CardDao

    companion object {
        @Volatile //Changes are visible to others threads
        private var INSTANCE: CardDatabase? = null

        //Singleton of db connection (Singleton = Design Pattern)
        fun getInstance(context: Context): CardDatabase {
            var instance = INSTANCE
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    CardDatabase::class.java,
                    "card_database"
                ).build()
                INSTANCE = instance
            }
            return instance
        }
    }
}