package com.example.personagemdnd

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.personagemdnd.repository.PersonagemRepository
import com.example.personagemdnd.data.entity.Personagem
import kotlinx.coroutines.launch
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
class PersonagemViewModel(private val repository: PersonagemRepository) : ViewModel() {

    fun insertPersonagem(personagem: Personagem) {
        // Especificando o Dispatchers.IO para operações de banco de dados
        viewModelScope.launch(Dispatchers.IO) {
            repository.insert(personagem)
        }
    }

    suspend fun getAllPersonagens(): List<Personagem> {
        // Retorna a lista diretamente de uma corrotina
        return withContext(Dispatchers.IO) {
            repository.getAll()
        }
    }
    fun deletePersonagem(personagem: Personagem) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.delete(personagem)
        }
    }
    fun updatePersonagemName(id: Int, newName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updatePersonagemName(id, newName)
        }
    }
}
