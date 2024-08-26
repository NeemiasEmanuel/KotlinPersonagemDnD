class PointBuySystem {
    private val pointCosts = mapOf(
        8 to 0, 9 to 1, 10 to 2, 11 to 3,
        12 to 4, 13 to 5, 14 to 7, 15 to 9
    )

    private val maxPoints = 27
    private var remainingPoints = maxPoints
    fun assignPoints(character: Character, attribute: String, desiredValue: Int): Boolean {
        val cleanAttribute = attribute.trim().lowercase()

        val currentAttributeValue = when (cleanAttribute) {
            "strength" -> character.strength
            "dexterity" -> character.dexterity
            "constitution" -> character.constitution
            "intelligence" -> character.intelligence
            "wisdom" -> character.wisdom
            "charisma" -> character.charisma
            else -> {
                println("Atributo inválido. Tente novamente.")
                return false
            }
        }

        if (desiredValue !in 8..15) {
            println("O valor desejado deve estar entre 8 e 15.")
            return false
        }

        val cost = pointCosts.getValue(desiredValue) - pointCosts.getValue(currentAttributeValue)

        return if (cost <= remainingPoints && cost >= 0) {
            remainingPoints -= cost
            when (cleanAttribute) {
                "strength" -> character.strength = desiredValue
                "dexterity" -> character.dexterity = desiredValue
                "constitution" -> character.constitution = desiredValue
                "intelligence" -> character.intelligence = desiredValue
                "wisdom" -> character.wisdom = desiredValue
                "charisma" -> character.charisma = desiredValue
            }
            true
        } else {
            println("Pontos insuficientes ou valor inválido.")
            false
        }
    }
    fun calculateCost(attributeValue: Int): Int {
        return pointCosts.getValue(attributeValue)
    }

    fun calculateTotalCost(character: Character): Int {
        return calculateCost(character.strength) +
                calculateCost(character.dexterity) +
                calculateCost(character.constitution) +
                calculateCost(character.intelligence) +
                calculateCost(character.wisdom) +
                calculateCost(character.charisma)
    }


    fun getRemainingPoints() = remainingPoints
}