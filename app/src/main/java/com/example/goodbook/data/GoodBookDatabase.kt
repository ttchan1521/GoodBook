package com.example.goodbook.data

import android.content.ContentValues
import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.goodbook.data.DAO.GoodBookDao
import com.example.goodbook.model.*
import java.util.concurrent.Executors

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
        @Volatile private var INSTANCE: GoodBookDatabase? = null

        fun getInstance(context: Context): GoodBookDatabase{
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }
        }

        private fun buildDatabase(context: Context): GoodBookDatabase {
            return Room.databaseBuilder(context, GoodBookDatabase::class.java, "goodbook")
                .addCallback(object : RoomDatabase.Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        //pre-populate data
                        Executors.newSingleThreadExecutor().execute {
                            INSTANCE?.let {
                                it.goodBookDao().insertUsers(users = DataGenerator.getUsers())
                                it.goodBookDao().insertCategories(DataGenerator.getCategory())
                            }
                        }
                    }
                })
                .build()
        }
    }
}
