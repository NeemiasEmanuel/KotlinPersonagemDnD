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
    var strength: Int = 8,
    var dexterity: Int = 8,
    var constitution: Int = 8,
    var intelligence: Int = 8,
    var wisdom: Int = 8,
    var charisma: Int = 8,
    var race: Race? = null
) {
    val strengthModifier: Int
        get() = calculateModifier(strength)

    val dexterityModifier: Int
        get() = calculateModifier(dexterity)

    val constitutionModifier: Int
        get() = calculateModifier(constitution)

    val intelligenceModifier: Int
        get() = calculateModifier(intelligence)

    val wisdomModifier: Int
        get() = calculateModifier(wisdom)

    val charismaModifier: Int
        get() = calculateModifier(charisma)

    val hitPoints: Int
        get() = 10 + constitutionModifier

    fun applyRacialBonus() {
        race?.applyRacialBonus(this)
    }
    fun showStats() {
        println("Força: $strength (Modificador: ${strengthModifier})")
        println("Destreza: $dexterity (Modificador: ${dexterityModifier})")
        println("Constituição: $constitution (Modificador: ${constitutionModifier})")
        println("Inteligência: $intelligence (Modificador: ${intelligenceModifier})")
        println("Sabedoria: $wisdom (Modificador: ${wisdomModifier})")
        println("Carisma: $charisma (Modificador: ${charismaModifier})")
        println("Pontos de Vida: $hitPoints")
    }
}