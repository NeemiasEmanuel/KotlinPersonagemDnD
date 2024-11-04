package com.example.personagemdnd.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "personagem_table")
data class Personagem(
    @PrimaryKey(autoGenerate = true) val idPersonagem: Int = 0,
    val nome: String,
    val Raca: String,
    val strength: Int,
    val dexterity: Int,
    val constitution: Int,
    val intelligence: Int,
    val wisdom: Int,
    val charisma: Int
)
