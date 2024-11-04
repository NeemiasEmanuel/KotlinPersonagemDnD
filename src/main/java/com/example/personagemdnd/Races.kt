package com.example.personagemdnd

class MountainDwarf : Race {
    override fun applyRacialBonus(character: Character) {
        character.strengthBase += 2
        character.constitutionBase += 2
    }
}

class Human : Race {
    override fun applyRacialBonus(character: Character) {
        character.strengthBase += 1
        character.dexterityBase += 1
        character.constitutionBase += 1
        character.intelligenceBase += 1
        character.wisdomBase += 1
        character.charismaBase += 1
    }
}




class Draconato : Race {
    override fun applyRacialBonus(character: Character) {
        character.strengthBase += 2
        character.charismaBase += 1
    }
}

class HalfOrc : Race {
    override fun applyRacialBonus(character: Character) {
        character.strengthBase += 2
        character.constitutionBase += 1
    }
}

class Tiefling : Race {
    override fun applyRacialBonus(character: Character) {
        character.intelligenceBase += 1
        character.charismaBase += 2
    }
}

class HillDwarf : Race {
    override fun applyRacialBonus(character: Character) {
        character.constitutionBase += 2
        character.wisdomBase += 1
    }
}

// Halflings
open class Halfling : Race {
    override fun applyRacialBonus(character: Character) {
        character.dexterityBase += 2
    }
}

class StoutHalfling : Halfling() {
    override fun applyRacialBonus(character: Character) {
        super.applyRacialBonus(character)  // Aplica o bônus de Halfling (dex +2)
        character.constitutionBase += 1    // Aplica o bônus específico de Halfling Robusto
    }
}

class LightfootHalfling : Halfling() {
    override fun applyRacialBonus(character: Character) {
        super.applyRacialBonus(character)  // Aplica o bônus de Halfling (dex +2)
        character.charismaBase += 1        // Aplica o bônus específico de Halfling Pés-Leves
    }
}

// Gnomos
open class Gnome : Race {
    override fun applyRacialBonus(character: Character) {
        character.intelligenceBase += 2
    }
}

class ForestGnome : Gnome() {
    override fun applyRacialBonus(character: Character) {
        super.applyRacialBonus(character)  // Aplica o bônus de Gnomo (int +2)
        character.dexterityBase += 1       // Aplica o bônus específico de Gnomo da Floresta
    }
}

class RockGnome : Gnome() {
    override fun applyRacialBonus(character: Character) {
        super.applyRacialBonus(character)  // Aplica o bônus de Gnomo (int +2)
        character.constitutionBase += 1    // Aplica o bônus específico de Gnomo das Rochas
    }
}

// Elfos
open class Elf : Race {
    override fun applyRacialBonus(character: Character) {
        character.dexterityBase += 2
    }
}

class HighElf : Elf() {
    override fun applyRacialBonus(character: Character) {
        super.applyRacialBonus(character)  // Aplica o bônus de Elfo (dex +2)
        character.intelligenceBase += 1    // Aplica o bônus específico de Alto Elfo
    }
}

class WoodElf : Elf() {
    override fun applyRacialBonus(character: Character) {
        super.applyRacialBonus(character)  // Aplica o bônus de Elfo (dex +2)
        character.wisdomBase += 1          // Aplica o bônus específico de Elfo da Floresta
    }
}

class HalfElf : Elf() {
    override fun applyRacialBonus(character: Character) {
        super.applyRacialBonus(character)
        character.charismaBase += 2        // Meio-Elfo tem bônus próprio (car +2)
    }
}

class Drow : Elf() {
    override fun applyRacialBonus(character: Character) {
        super.applyRacialBonus(character)  // Aplica o bônus de Elfo (dex +2)
        character.charismaBase += 1        // Aplica o bônus específico de Drow (car +1)
    }
}
