package id.stockbit.dao

import androidx.room.*
import id.stockbit.entity.UserEntity

@Dao
interface UserDao {
    @Query("SELECT * FROM user ORDER BY id DESC LIMIT 1")
    suspend fun getUser(): UserEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: UserEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(user: UserEntity): Int

    @Delete
    suspend fun delete(user: UserEntity): Int
}