package com.example.personagemdnd.repository

import com.example.personagemdnd.data.dao.PersonagemDAO
import com.example.personagemdnd.data.entity.Personagem

class PersonagemRepository(private val personagemDAO: PersonagemDAO) {

    suspend fun insert(personagem: Personagem) {
        personagemDAO.insert(personagem)
    }

     fun getAll(): List<Personagem> {
        return personagemDAO.getAll()
    }
    suspend fun delete(personagem: Personagem) {
        personagemDAO.delete(personagem)
    }
    suspend fun updatePersonagemName(id: Int, newName: String) {
        personagemDAO.updateName(id, newName)
    }
}
