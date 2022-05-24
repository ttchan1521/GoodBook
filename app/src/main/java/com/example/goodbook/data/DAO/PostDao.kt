package com.example.goodbook.data.DAO

import androidx.room.*
import com.example.goodbook.model.Post
import com.example.goodbook.model.PostDetail
import kotlinx.coroutines.flow.Flow

@Dao
interface PostDao {
    @Query("SELECT * FROM posts")
    fun getAllPosts(): Flow<List<Post>>

    // A method to retrieve a Post from the database by id
    @Query("SELECT * FROM posts WHERE id = :id")
    fun getPost(id: Int) : Flow<Post>

    @Query("SELECT title, img_scr, book_writer, description, posts.time, users.avt, category.type, AVG(star_quantity) as star FROM posts " +
            "INNER JOIN users ON posts.user = users.id " +
            "INNER JOIN category ON posts.category = category.id " +
            "LEFT JOIN ratings ON ratings.post_id = posts.id " +
            "WHERE posts.id = :id")
    fun getDetailPost(id: Int) : Flow<PostDetail>

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