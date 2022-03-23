package com.gonzalo.vega.tzuzulcodesegundoproyectofinanzapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.gonzalo.vega.tzuzulcodesegundoproyectofinanzapp.database.converters.DateConverter
import com.gonzalo.vega.tzuzulcodesegundoproyectofinanzapp.models.Card
import com.gonzalo.vega.tzuzulcodesegundoproyectofinanzapp.models.Payment

@Database(entities = [Card::class, Payment::class], version = 2, exportSchema = false) //exportSchema is for logs
@TypeConverters(DateConverter::class)
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
                ).fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
            }
            return instance
        }
    }
}