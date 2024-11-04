package com.example.personagemdnd.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Delete
import com.example.personagemdnd.data.entity.Personagem

@Dao
interface PersonagemDAO {

    @Insert
     fun insert(personagem: Personagem)

    @Query("SELECT * FROM personagem_table")
    fun getAll(): List<Personagem> // Use o tipo de retorno correto aqui, como List<Personagem>

    @Delete
     fun delete(personagem: Personagem)
    @Query("UPDATE personagem_table SET nome = :newName WHERE idPersonagem = :id")
     fun updateName(id: Int, newName: String)
}
