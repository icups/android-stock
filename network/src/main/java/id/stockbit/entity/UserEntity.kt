package id.stockbit.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "user")
class UserEntity(
    @PrimaryKey(autoGenerate = true) var id: Long? = null,
    var profileId: String = "",
    var fullName: String = "User",
    var email: String = "",
    var phoneNumber: String = "",
    var avatarUrl: String = "",
    var balance: Int = 0
) : Parcelable