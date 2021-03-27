package com.theaverageguy.roompractice.room.modelClasses

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "phone_book")
data class PhoneBook(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val firstName: String,
    val lastName: String,
    val phoneNumber: Int
): Parcelable
