class MountainDwarf : Race {
    override fun applyRacialBonus(character: Character) {
        character.strength += 2
        character.constitution += 2
    }
}

class Human : Race {
    override fun applyRacialBonus(character: Character) {
        character.strength += 1
        character.dexterity += 1
        character.constitution += 1
        character.intelligence += 1
        character.wisdom += 1
        character.charisma += 1
    }
}

class Draconato : Race {
    override fun applyRacialBonus(character: Character) {
        character.strength += 2
        character.charisma += 1
    }
}

class HalfOrc : Race {
    override fun applyRacialBonus(character: Character) {
        character.strength += 2
        character.constitution += 1
    }
}


class Tiefling : Race {
    override fun applyRacialBonus(character: Character) {
        character.intelligence += 1
        character.charisma += 2
    }
}

class HillDwarf : Race {
    override fun applyRacialBonus(character: Character) {
        character.constitution += 2
        character.wisdom += 1
    }
}

//halflings
open class Halfling : Race {
    override fun applyRacialBonus(character: Character) {
        character.dexterity += 2
    }
}

class StoutHalfling : Halfling() {
    override fun applyRacialBonus(character: Character) {
        super.applyRacialBonus(character)  // Aplica o bônus de Halfling (dex +2)
        character.constitution += 1        // Aplica o bônus específico de Halfling Robusto
    }
}

class LightfootHalfling : Halfling() {
    override fun applyRacialBonus(character: Character) {
        super.applyRacialBonus(character)  // Aplica o bônus de Halfling (dex +2)
        character.charisma += 1            // Aplica o bônus específico de Halfling Pés-Leves
    }
}

//gnomos
open class Gnome : Race {
    override fun applyRacialBonus(character: Character) {
        character.intelligence += 2
    }
}

class ForestGnome : Gnome() {
    override fun applyRacialBonus(character: Character) {
        super.applyRacialBonus(character)  // Aplica o bônus de Gnomo (int +2)
        character.dexterity += 1           // Aplica o bônus específico de Gnomo da Floresta
    }
}

class RockGnome : Gnome() {
    override fun applyRacialBonus(character: Character) {
        super.applyRacialBonus(character)  // Aplica o bônus de Gnomo (int +2)
        character.constitution += 1        // Aplica o bônus específico de Gnomo das Rochas
    }
}



//elfos
open class Elf : Race {  // 'open' permite que a classe seja herdada
    override fun applyRacialBonus(character: Character) {
        character.dexterity += 2
    }
}

class HighElf : Elf() {
    override fun applyRacialBonus(character: Character) {
        super.applyRacialBonus(character)  // Aplica o bônus de Elfo (dex +2)
        character.intelligence += 1        // Aplica o bônus específico de Alto Elfo
    }
}

class WoodElf : Elf() {
    override fun applyRacialBonus(character: Character) {
        super.applyRacialBonus(character)  // Aplica o bônus de Elfo (dex +2)
        character.wisdom += 1              // Aplica o bônus específico de Elfo da Floresta
    }
}

class HalfElf : Elf() {
    override fun applyRacialBonus(character: Character) {
        super.applyRacialBonus(character)
        character.charisma += 2            // Meio-Elfo tem bônus próprio (car +2)
    }
}
class Drow : Elf() {
    override fun applyRacialBonus(character: Character) {
        super.applyRacialBonus(character)  // Aplica o bônus de Elfo (dex +2)
        character.charisma += 1            // Aplica o bônus específico de Drow (car +1)
    }
}