package com.example.goodbook.data.DAO

import androidx.room.*
import com.example.goodbook.model.Post
import kotlinx.coroutines.flow.Flow

@Dao
interface PostDao {
    @Query("SELECT * FROM posts")
    fun getAllPosts(): Flow<List<Post>>

    // A method to retrieve a Post from the database by id
    @Query("SELECT * FROM posts WHERE id = :id")
    fun getPost(id: Long) : Flow<Post>

    // A method to insert a Post into the database
    //  (use OnConflictStrategy.REPLACE)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(post: Post)


    // A method to update a Post that is already in the database
    @Update
    suspend fun update(post: Post)

    // A method to delete a Post from the database.
    @Delete
    suspend fun delete(post: Post)
}