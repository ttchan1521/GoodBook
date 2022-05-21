package com.example.goodbook.data.DAO

import androidx.room.*
import com.example.goodbook.model.*
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object for database interaction.
 */
@Dao
interface GoodBookDao {
    // A method to retrieve all Users from the database
    @Query("SELECT * FROM users")
    fun getAllUsers(): Flow<List<User>>

    // A method to retrieve a User from the database by id
    @Query("SELECT * FROM users WHERE id = :id")
    fun getUser(id: Long) : Flow<User>

    // A method to insert a User into the database
    //  (use OnConflictStrategy.REPLACE)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: User)


    // A method to update a User that is already in the database
    @Update
    suspend fun update(user: User)

    // A method to delete a User from the database.
    @Delete
    suspend fun delete(user: User)

    // A method to retrieve all Ratings from the database
    @Query("SELECT * FROM ratings")
    fun getAllRatings(): Flow<List<Rating>>

    // A method to retrieve a Rating from the database by id
    @Query("SELECT * FROM ratings WHERE id = :id")
    fun getRating(id: Long) : Flow<Rating>

    // A method to insert a Rating into the database
    //  (use OnConflictStrategy.REPLACE)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(rating: Rating)


    // A method to update a Rating that is already in the database
    @Update
    suspend fun update(rating: Rating)

    // A method to delete a Rating from the database.
    @Delete
    suspend fun delete(rating: Rating)

    // A method to retrieve all Posts from the database
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

    // A method to retrieve all Notifications from the database
    @Query("SELECT * FROM notifications")
    fun getAllNotifications(): Flow<List<Notification>>

    // A method to retrieve a Notification from the database by id
    @Query("SELECT * FROM notifications WHERE id = :id")
    fun getNotification(id: Long) : Flow<Notification>

    // A method to insert a Notification into the database
    //  (use OnConflictStrategy.REPLACE)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(notification: Notification)


    // A method to update a Notification that is already in the database
    @Update
    suspend fun update(notification: Notification)

    // A method to delete a Notification from the database.
    @Delete
    suspend fun delete(notification: Notification)

    // A method to retrieve all Likes from the database
    @Query("SELECT * FROM likes")
    fun getAllLikes(): Flow<List<Like>>

    // A method to retrieve a Like from the database by id
    @Query("SELECT * FROM likes WHERE id = :id")
    fun getLike(id: Long) : Flow<Like>

    // A method to insert a Like into the database
    //  (use OnConflictStrategy.REPLACE)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(like: Like)


    // A method to update a Like that is already in the database
    @Update
    suspend fun update(like: Like)

    // A method to delete a Like from the database.
    @Delete
    suspend fun delete(like: Like)

    // A method to retrieve all Comments from the database
    @Query("SELECT * FROM comments")
    fun getAllComments(): Flow<List<Comment>>

    // A method to retrieve a Comment from the database by id
    @Query("SELECT * FROM comments WHERE id = :id")
    fun getComment(id: Long) : Flow<Comment>

    // A method to insert a Comment into the database
    //  (use OnConflictStrategy.REPLACE)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(comment: Comment)


    // A method to update a Comment that is already in the database
    @Update
    suspend fun update(comment: Comment)

    // A method to delete a Comment from the database.
    @Delete
    suspend fun delete(comment: Comment)

    // A method to retrieve all Categories from the database
    @Query("SELECT * FROM category")
    fun getAllCategories(): Flow<List<BookCategory>>

    // A method to retrieve a Category from the database by id
    @Query("SELECT * FROM category WHERE id = :id")
    fun getCategory(id: Long) : Flow<BookCategory>

    // A method to insert a Category into the database
    //  (use OnConflictStrategy.REPLACE)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(category: BookCategory)


    // A method to update a Category that is already in the database
    @Update
    suspend fun update(category: BookCategory)

    // A method to delete a Category from the database.
    @Delete
    suspend fun delete(category: BookCategory)

}