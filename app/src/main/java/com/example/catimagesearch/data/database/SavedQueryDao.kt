package com.example.catimagesearch.data.database

import androidx.room.*
import com.example.catimagesearch.data.entity.SavedQueryModel

@Dao
interface SavedQueryDao {

    @Insert
    suspend fun insert(query : SavedQueryModel)

    @Delete
    suspend fun delete(query : SavedQueryModel)

    @Update
    suspend fun update(query : SavedQueryModel)

    @Query("select distinct `query`  from (select * from SavedQuery order by id desc) limit 5")
    suspend fun getStringQuery() : List<String>

    @Query("select * from SavedQuery")
    suspend fun getAllQuery() : List<SavedQueryModel>
}