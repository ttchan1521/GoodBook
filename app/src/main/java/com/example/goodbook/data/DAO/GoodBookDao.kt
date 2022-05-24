package com.example.goodbook.data.DAO

import android.provider.ContactsContract
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
    fun getUser(id: Int) : Flow<User>

    // A method to retrieve a User from the database by mail or phone
    @Query("SELECT * FROM users WHERE (email = :mail_phone OR phone = :mail_phone) LIMIT 1")
    fun getUser(mail_phone: String) : Flow<User>

    // A method to retrieve a User from the database by mail or phone and to check password
    @Query("SELECT * FROM users WHERE (email = :mail_phone OR phone = :mail_phone) AND password = :pass LIMIT 1")
    fun getUser(mail_phone: String, pass: String) : Flow<User>

    // A method to insert a User into the database
    //  (use OnConflictStrategy.REPLACE)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: User)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUsers(users: List<User>)

    // A method to retrieve a Rating from the database by post_id
//    @Query("SELECT * FROM ratings WHERE post_id = :post_id")
//    fun getRating(post_id: Int) : Flow<Rating>

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

    @Query("SELECT SUM(star_quantity) FROM ratings WHERE post_id = :id")
    fun getRating(id: Int) : Flow<Int>

    @Query("SELECT COUNT(*) FROM ratings WHERE post_id = :id")
    fun getRatingCount(id: Int) : Flow<Int>

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


    // A method to retrieve all Likes from the database
    @Query("SELECT * FROM likes")
    fun getAllLikes(): Flow<List<Like>>

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
    @Query("SELECT users.name, users.avt, comments.description FROM comments " +
            "INNER JOIN users ON comments.user_id = users.id " +
            "WHERE post_id = :id")
    fun getCommentPost(id: Int) : Flow<List<DetailComment>>

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

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCategories(categories: List<BookCategory>)
    // A method to update a Category that is already in the database
    @Update
    suspend fun update(category: BookCategory)

    // A method to delete a Category from the database.
    @Delete
    suspend fun delete(category: BookCategory)

    @Query("SELECT * FROM posts")
    fun getAllPosts(): Flow<List<Post>>

    @Query("SELECT * FROM posts ORDER BY time DESC LIMIT 7")
    fun get7MostRecentlyPosts(): Flow<List<Post>>

    @Query("SELECT * FROM posts ORDER BY time DESC")
    fun getAllMostRecentlyPosts(): Flow<List<Post>>

    @Query("SELECT * FROM posts " +
            "JOIN ratings ON (posts.id = ratings.post_id) " +
            "GROUP BY post_id " +
            "ORDER BY COUNT(user_id) DESC " +
            "LIMIT 7;")
    fun get7MostRatePosts(): Flow<List<Post>>

    @Query("SELECT * FROM posts " +
            "JOIN ratings ON (posts.id = ratings.post_id) " +
            "GROUP BY post_id " +
            "ORDER BY COUNT(user_id) DESC")
    fun getAllMostRatePosts(): Flow<List<Post>>

    @Query("SELECT * FROM posts " +
            "WHERE (category = :cate_id) " +
            "LIMIT 7;")
    fun get7PostsRelateToCategory(cate_id: Int): Flow<List<Post>>

    @Query("SELECT * FROM posts " +
            "WHERE (category = :cate_id)")
    fun getAllPostsRelatedToCategoryById(cate_id: Int): Flow<List<Post>>

    @Query("SELECT * FROM posts " +
            "WHERE (title LIKE :keyword OR book_writer LIKE :keyword OR description LIKE :keyword )")
    fun getPostsRelatedToKeyWord(keyword: String) : Flow<List<Post>>

    // A method to insert a Post into the database
    //  (use OnConflictStrategy.REPLACE)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(post: Post)

    @Query("SELECT * FROM posts WHERE id = :id")
    fun getPost(id: Int) : Flow<Post>

    // A method to get all Post that User has userId saved
    @Query("SELECT posts.* FROM save JOIN posts ON (posts.id = save.post_id) WHERE save.user_id = :userId")
    fun getSavedPostsByUserId(userId: Int): Flow<List<Post>>

}