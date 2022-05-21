/*
 * Copyright (C) 2021 The Android Open Source Project.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.forage.data

import android.content.ClipData
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.goodbook.data.DAO.GoodBookDao
import com.example.goodbook.model.*

/**
 * Room database to persist data for the GoodBook app.
 */

@Database(entities = [User::class, Post::class, Rating::class,
    Notification::class, Like::class, Comment::class, BookCategory::class], version = 1, exportSchema = false)
abstract class GoodBookDatabase : RoomDatabase() {
    abstract fun goodBookDao() : GoodBookDao

    companion object {
        //INSTANCE will keep a reference to the database
        @Volatile
        private var INSTANCE: GoodBookDatabase? = null

        fun getDatabase(context: Context): GoodBookDatabase {
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
