fun main() {
    // Cria uma instância de Character com os atributos iniciais definidos
    val character = Character()

    println("Selecione uma raça:")
    println("1. Anão da Montanha (str +2, cons +2)")
    println("2. Anão da Colina (cons +2, sab +1)")
    println("3. Humano (str +1, dex +1, cons +1, int +1, sab +1, car +1)")
    println("4. Draconato (str +2, car +1)")
    println("5. Meio-orc (str +2, cons +1)")
    println("6. Elfo (sub-raças disponíveis: Alto Elfo, Elfo da Floresta, Meio-Elfo, Drow)")
    println("7. Halfling (sub-raças disponíveis: Halfling Robusto, Halfling Pés-Leves)")
    println("8. Gnomo (sub-raças disponíveis: Gnomo da Floresta, Gnomo das Rochas)")
    println("9. Tiefling (int +1, car +2)")

    when (readLine()) {
        "1" -> character.race = MountainDwarf()
        "2" -> character.race = HillDwarf()
        "3" -> character.race = Human()
        "4" -> character.race = Draconato()
        "5" -> character.race = HalfOrc()
        "6" -> {
            println("Selecione uma sub-raça de Elfo:")
            println("1. Alto Elfo (dex +2, int +1)")
            println("2. Elfo da Floresta (dex +2, sab +1)")
            println("3. Meio-Elfo (dex +2,car +2)")
            println("4. Drow (dex +2, car +1)")
            when (readLine()) {
                "1" -> character.race = HighElf()
                "2" -> character.race = WoodElf()
                "3" -> character.race = HalfElf()
                "4" -> character.race = Drow()
            }
        }
        "7" -> {
            println("Selecione uma sub-raça de Halfling:")
            println("1. Halfling Robusto (dex +2, cons +1)")
            println("2. Halfling Pés-Leves (dex +2, car +1)")
            when (readLine()) {
                "1" -> character.race = StoutHalfling()
                "2" -> character.race = LightfootHalfling()
            }
        }
        "8" -> {
            println("Selecione uma sub-raça de Gnomo:")
            println("1. Gnomo da Floresta (int +2, dex +1)")
            println("2. Gnomo das Rochas (int +2, cons +1)")
            when (readLine()) {
                "1" -> character.race = ForestGnome()
                "2" -> character.race = RockGnome()
            }
        }
        "9" -> character.race = Tiefling()
        else -> println("Raça inválida, padrão para Humano.")
    }

    // Agora o sistema de distribuição de pontos
    val pointBuySystem = PointBuySystem()

    println("Distribua 27 pontos entre os atributos do seu personagem:")

    do {
        // Exibe os atributos atuais, o gasto de ponto para cada atributo e o gasto total
        println("Atributo       | Pontos de Atributo | Gasto de Ponto")
        println("---------------------------------------------")
        println(String.format("Força         | %-17d | %d", character.strength, pointBuySystem.calculateCost(character.strength)))
        println(String.format("Destreza      | %-17d | %d", character.dexterity, pointBuySystem.calculateCost(character.dexterity)))
        println(String.format("Constituição  | %-17d | %d", character.constitution, pointBuySystem.calculateCost(character.constitution)))
        println(String.format("Inteligência  | %-17d | %d", character.intelligence, pointBuySystem.calculateCost(character.intelligence)))
        println(String.format("Sabedoria     | %-17d | %d", character.wisdom, pointBuySystem.calculateCost(character.wisdom)))
        println(String.format("Carisma       | %-17d | %d", character.charisma, pointBuySystem.calculateCost(character.charisma)))
        println()
        println("Gasto total: ${pointBuySystem.calculateTotalCost(character)}")
        println("Pontos restantes: ${pointBuySystem.getRemainingPoints()}")
        println()

        // Coleta as entradas do usuário
        println("Escolha um atributo para aumentar (strength, dexterity, constitution, intelligence, wisdom, charisma):")
        val attribute = readLine()?.trim() ?: ""
        println("Escolha um valor para esse atributo (entre 8 e 15):")
        val desiredValue = readLine()?.toIntOrNull() ?: 8

        // Tenta atribuir os pontos
        val successful = pointBuySystem.assignPoints(character, attribute, desiredValue)

        if (!successful) {
            println("Não foi possível aplicar as alterações. Verifique se há pontos suficientes ou se o valor é válido.")
        }

    } while (pointBuySystem.getRemainingPoints() > 0)

    // Aplicar bônus racial depois da distribuição dos pontos
    character.applyRacialBonus()

    println("\nDistribuição final:")
    character.showStats()
}