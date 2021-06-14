package id.stockbit.database

import androidx.room.Database
import androidx.room.RoomDatabase
import id.stockbit.dao.UserDao
import id.stockbit.entity.UserEntity

@Database(entities = [UserEntity::class], version = 1, exportSchema = false)
abstract class UserDatabase : RoomDatabase() {
    abstract fun dao(): UserDao
}