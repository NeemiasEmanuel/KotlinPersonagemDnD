package com.example.personagemdnd
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

// Função de cálculo do modificador fora da classe Character
fun calculateModifier(attributeValue: Int): Int {
    return when (attributeValue) {
        1 -> -5
        in 2..3 -> -4
        in 4..5 -> -3
        in 6..7 -> -2
        in 8..9 -> -1
        in 10..11 -> 0
        in 12..13 -> 1
        in 14..15 -> 2
        in 16..17 -> 3
        in 18..19 -> 4
        in 20..21 -> 5
        else -> throw IllegalArgumentException("Valor de atributo inválido: $attributeValue")
    }
}

class Character(
    strengthBase: Int = 8,
    dexterityBase: Int = 8,
    constitutionBase: Int = 8,
    intelligenceBase: Int = 8,
    wisdomBase: Int = 8,
    charismaBase: Int = 8,
    var race: Race? = null
) {
    // Atributos base
    var strengthBase by mutableStateOf(strengthBase)
    var dexterityBase by mutableStateOf(dexterityBase)
    var constitutionBase by mutableStateOf(constitutionBase)
    var intelligenceBase by mutableStateOf(intelligenceBase)
    var wisdomBase by mutableStateOf(wisdomBase)
    var charismaBase by mutableStateOf(charismaBase)

    // Modificadores calculados diretamente a partir dos atributos base
    val strengthModifier: Int
        get() = calculateModifier(strengthBase)

    val dexterityModifier: Int
        get() = calculateModifier(dexterityBase)

    val constitutionModifier: Int
        get() = calculateModifier(constitutionBase)

    val intelligenceModifier: Int
        get() = calculateModifier(intelligenceBase)

    val wisdomModifier: Int
        get() = calculateModifier(wisdomBase)

    val charismaModifier: Int
        get() = calculateModifier(charismaBase)

    val hitPoints: Int
        get() = 10 + constitutionModifier

    // Aplica os bônus raciais diretamente nos atributos base
    fun applyRacialBonus() {
        race?.applyRacialBonus(this)
    }

    fun showStats() {
        println("Força: $strengthBase (Modificador: ${strengthModifier})")
        println("Destreza: $dexterityBase (Modificador: ${dexterityModifier})")
        println("Constituição: $constitutionBase (Modificador: ${constitutionModifier})")
        println("Inteligência: $intelligenceBase (Modificador: ${intelligenceModifier})")
        println("Sabedoria: $wisdomBase (Modificador: ${wisdomModifier})")
        println("Carisma: $charismaBase (Modificador: ${charismaModifier})")
        println("Pontos de Vida: $hitPoints")
    }
}
