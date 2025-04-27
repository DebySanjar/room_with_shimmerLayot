package com.uz.shimmerlayot.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.uz.shimmerlayot.data.entity.Person

@Dao
interface PersonDao {
    @Insert
    suspend fun insert(person: Person)

    @Delete
    suspend fun delete(person: Person)

    @Query("SELECT * FROM Person WHERE gender = :gender")
    fun getPersonsByGender(gender: String): LiveData<List<Person>>

    @Query("SELECT COUNT(*) FROM Person WHERE gender = :gender")
    fun getCountGender(gender: String): LiveData<Int>
}
