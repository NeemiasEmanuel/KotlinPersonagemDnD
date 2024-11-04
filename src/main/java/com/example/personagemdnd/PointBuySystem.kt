package com.example.personagemdnd

class PointBuySystem {
    private val pointCosts = mapOf(
        8 to 0, 9 to 1, 10 to 2, 11 to 3,
        12 to 4, 13 to 5, 14 to 7, 15 to 9,
        16 to 9, 17 to 9, 18 to 9, 19 to 9, 20 to 9
    )

    private val maxPoints = 27

    fun calculateCost(attributeValue: Int): Int {
        return pointCosts.getValue(attributeValue)
    }

    // Calcula o custo total baseado nos atributos base (não nos finais)
    fun calculateTotalCost(character: Character): Int {
        return calculateCost(character.strengthBase) +
                calculateCost(character.dexterityBase) +
                calculateCost(character.constitutionBase) +
                calculateCost(character.intelligenceBase) +
                calculateCost(character.wisdomBase) +
                calculateCost(character.charismaBase)
    }

    fun getRemainingPoints(character: Character): Int {
        return maxPoints - calculateTotalCost(character)
    }

    fun canAssignPoints(character: Character, attribute: String, desiredValue: Int): Boolean {
        val cleanAttribute = attribute.trim().lowercase()

        // Acessa os atributos base
        val currentAttributeValue = when (cleanAttribute) {
            "strength" -> character.strengthBase
            "dexterity" -> character.dexterityBase
            "constitution" -> character.constitutionBase
            "intelligence" -> character.intelligenceBase
            "wisdom" -> character.wisdomBase
            "charisma" -> character.charismaBase
            else -> return false
        }

        if (desiredValue !in 8..15) {
            return false
        }

        val currentTotalCost = calculateTotalCost(character)
        val currentAttributeCost = calculateCost(currentAttributeValue)
        val desiredAttributeCost = calculateCost(desiredValue)

        val newTotalCost = currentTotalCost - currentAttributeCost + desiredAttributeCost

        return newTotalCost <= maxPoints && newTotalCost >= 0
    }
}
