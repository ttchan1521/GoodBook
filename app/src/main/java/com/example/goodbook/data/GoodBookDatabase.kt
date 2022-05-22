package com.example.goodbook.data

import android.content.ContentValues
import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.goodbook.data.DAO.GoodBookDao
import com.example.goodbook.model.*

/**
 * Room database to persist data for the GoodBook app.
 */

@Database(entities = [User::class, Post::class, Rating::class,
    SavedBook::class, Like::class, Comment::class, BookCategory::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class GoodBookDatabase : RoomDatabase() {
    abstract fun goodBookDao() : GoodBookDao

    companion object {
        //INSTANCE will keep a reference to the database
        @Volatile
        private var INSTANCE: GoodBookDatabase? = null

        fun getDatabase(context: Context): GoodBookDatabase {
            Log.d(ContentValues.TAG, "khởi tạo db thành công: ")
            //return INSTANCE, if INSTANCE is null, initialize it inside a synchronized{} block
            return INSTANCE ?: synchronized(this) {
                //use the database builder to get the database
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    GoodBookDatabase::class.java,
                    "goodbook_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()

                INSTANCE = instance
                return instance
            }
        }
    }
}
