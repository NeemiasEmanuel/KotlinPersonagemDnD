package com.example.personagemdnd

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.personagemdnd.ui.theme.PersonagemDNDTheme
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import com.example.personagemdnd.repository.PersonagemRepository
import com.example.personagemdnd.data.AppDatabase
import com.example.personagemdnd.data.entity.Personagem
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inicializa o banco de dados e o repositório
        val database = AppDatabase.getDatabase(this)
        val repository = PersonagemRepository(database.personagemDao())
        val viewModel = PersonagemViewModel(repository)

        setContent {
            PersonagemDNDTheme {
                val navController = rememberNavController()
                NavGraph(navController, viewModel)
            }
        }
    }
}

@Composable
fun NavGraph(navController: NavHostController, viewModel: PersonagemViewModel) {
    val character = remember { Character() }
    var selectedRace by remember { mutableStateOf<Race?>(null) }
    var bonusesApplied by remember { mutableStateOf(false) }

    NavHost(navController = navController, startDestination = "home_screen") {
        composable("home_screen") {
            HomeScreen(navController)
        }
        composable("race_selection") {
            RaceSelectionScreen(navController) { race ->
                selectedRace = race
                navController.navigate("attribute_assignment")
            }
        }
        composable("attribute_assignment") {
            AttributeAssignmentScreen(navController, character, selectedRace) {
                if (!bonusesApplied && selectedRace != null) {
                    selectedRace?.applyRacialBonus(character)
                    bonusesApplied = true
                }
                navController.navigate("summary_screen")
            }
        }
        composable("summary_screen") {
            SummaryScreen(character, selectedRace, viewModel)
        }
        composable("character_list") {
            CharacterListScreen(viewModel = viewModel)
        }
    }
}

@Composable
fun HomeScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Bem-vindo ao Criador de Personagens", style = MaterialTheme.typography.headlineSmall)

        Spacer(modifier = Modifier.height(40.dp))

        Button(
            onClick = { navController.navigate("race_selection") },
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
        ) {
            Text("Criação de Personagem")
        }

        Button(
            onClick = { navController.navigate("character_list") },
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
        ) {
            Text("Lista de Personagem")
        }
    }
}

// Primeira tela: Seleção de Raça
@Composable
fun RaceSelectionScreen(navController: NavHostController, onRaceSelected: (Race) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Selecione sua Raça", style = MaterialTheme.typography.headlineSmall)

        Spacer(modifier = Modifier.height(20.dp))

        val races = listOf(
            "Humano" to Human(),
            "Anão da Montanha" to MountainDwarf(),
            "Elfo" to Elf(),
            "Meio-Orc" to HalfOrc(),
            "Tiefling" to Tiefling(),
            "Draconato" to Draconato(),
            "Anão da Colina" to HillDwarf(),
            "Halfling Robusto" to StoutHalfling(),
            "Halfling Pés-Leves" to LightfootHalfling(),
            "Gnomo da Floresta" to ForestGnome(),
            "Gnomo das Rochas" to RockGnome(),
            "Alto Elfo" to HighElf(),
            "Elfo da Floresta" to WoodElf(),
            "Meio-Elfo" to HalfElf(),
            "Drow" to Drow()
        )

        // LazyColumn para permitir rolagem
        LazyColumn(
            modifier = Modifier.fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(races) { (raceName, raceInstance) ->
                Button(
                    onClick = {
                        onRaceSelected(raceInstance)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                ) {
                    Text(raceName)
                }
            }
        }
    }
}

// Segunda tela: Atribuição de Atributos
@Composable
fun AttributeAssignmentScreen(navController: NavHostController, character: Character, selectedRace: Race?, onFinished: () -> Unit) {
    val pointBuySystem = PointBuySystem()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Distribua seus Pontos de Atributo", style = MaterialTheme.typography.headlineSmall)

        Spacer(modifier = Modifier.height(20.dp))

        Text("Pontos Restantes: ${pointBuySystem.getRemainingPoints(character)}")

        Spacer(modifier = Modifier.height(20.dp))

        // Inputs dos atributos base
        AttributeInput("Força", character.strengthBase) { newValue ->
            if (pointBuySystem.canAssignPoints(character, "strength", newValue)) {
                character.strengthBase = newValue
            }
        }
        AttributeInput("Destreza", character.dexterityBase) { newValue ->
            if (pointBuySystem.canAssignPoints(character, "dexterity", newValue)) {
                character.dexterityBase = newValue
            }
        }
        AttributeInput("Constituição", character.constitutionBase) { newValue ->
            if (pointBuySystem.canAssignPoints(character, "constitution", newValue)) {
                character.constitutionBase = newValue
            }
        }
        AttributeInput("Inteligência", character.intelligenceBase) { newValue ->
            if (pointBuySystem.canAssignPoints(character, "intelligence", newValue)) {
                character.intelligenceBase = newValue
            }
        }
        AttributeInput("Sabedoria", character.wisdomBase) { newValue ->
            if (pointBuySystem.canAssignPoints(character, "wisdom", newValue)) {
                character.wisdomBase = newValue
            }
        }
        AttributeInput("Carisma", character.charismaBase) { newValue ->
            if (pointBuySystem.canAssignPoints(character, "charisma", newValue)) {
                character.charismaBase = newValue
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Botão para ir para a tela de resumo
        Button(onClick = {
            onFinished() // Aplica os bônus e navega para o sumário
        }) {
            Text("Finalizar e Ver Resumo")
        }
    }
}

// Tela de Sumário
// Tela de Sumário


@Composable
fun SummaryScreen(character: Character, selectedRace: Race?, viewModel: PersonagemViewModel) {
    var personagemNome by remember { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Resumo Final", style = MaterialTheme.typography.headlineSmall)

        Spacer(modifier = Modifier.height(20.dp))

        Text("Raça Selecionada: ${selectedRace?.javaClass?.simpleName ?: "Nenhuma"}")

        Spacer(modifier = Modifier.height(10.dp))

        TextField(
            value = personagemNome,
            onValueChange = { personagemNome = it },
            label = { Text("Nome do Personagem") }
        )

        Spacer(modifier = Modifier.height(10.dp))

        // Mostra os valores finais após a aplicação dos bônus
        Text("Força: ${character.strengthBase} (Modificador: ${character.strengthModifier})")
        Text("Destreza: ${character.dexterityBase} (Modificador: ${character.dexterityModifier})")
        Text("Constituição: ${character.constitutionBase} (Modificador: ${character.constitutionModifier})")
        Text("Inteligência: ${character.intelligenceBase} (Modificador: ${character.intelligenceModifier})")
        Text("Sabedoria: ${character.wisdomBase} (Modificador: ${character.wisdomModifier})")
        Text("Carisma: ${character.charismaBase} (Modificador: ${character.charismaModifier})")
        Text("Pontos de Vida: ${character.hitPoints}")

        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = {
            coroutineScope.launch {
                val personagem = Personagem(
                    nome = personagemNome,
                    Raca = selectedRace?.javaClass?.simpleName ?: "Desconhecido",
                    strength = character.strengthBase,
                    dexterity = character.dexterityBase,
                    constitution = character.constitutionBase,
                    intelligence = character.intelligenceBase,
                    wisdom = character.wisdomBase,
                    charisma = character.charismaBase
                )
                viewModel.insertPersonagem(personagem)

            }
        }) {
            Text("Salvar Personagem")
        }
    }
}
@Composable
fun CharacterListScreen(viewModel: PersonagemViewModel) {
    val coroutineScope = rememberCoroutineScope()
    var personagens by remember { mutableStateOf<List<Personagem>>(emptyList()) }
    var editMode by remember { mutableStateOf<Pair<Int, Boolean>?>(null) }

    // Carrega os personagens sempre que o estado de `personagens` mudar
    LaunchedEffect(personagens) {
        personagens = viewModel.getAllPersonagens()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Lista de Personagens", style = MaterialTheme.typography.headlineSmall)

        Spacer(modifier = Modifier.height(20.dp))

        LazyColumn {
            items(personagens) { personagem ->
                var newName by remember { mutableStateOf(personagem.nome) }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text("Nome: ${personagem.nome}")
                        Text("Raça: ${personagem.Raca}")

                        if (editMode?.first == personagem.idPersonagem && editMode?.second == true) {
                            OutlinedTextField(
                                value = newName,
                                onValueChange = { newName = it },
                                label = { Text("Novo Nome") },
                                modifier = Modifier.fillMaxWidth()
                            )

                            Button(
                                onClick = {
                                    coroutineScope.launch {
                                        viewModel.updatePersonagemName(personagem.idPersonagem, newName)
                                        personagens = viewModel.getAllPersonagens() // Atualiza após a edição
                                        editMode = null // Sai do modo de edição
                                    }
                                },
                                modifier = Modifier.padding(top = 8.dp)
                            ) {
                                Text("Salvar Nome")
                            }
                        }
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    Button(onClick = { editMode = personagem.idPersonagem to true }) {
                        Text("Mudar Nome")
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    Button(
                        onClick = {
                            coroutineScope.launch {
                                viewModel.deletePersonagem(personagem)
                                personagens = viewModel.getAllPersonagens() // Atualiza a lista após a exclusão
                            }
                        },
                        colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.error)
                    ) {
                        Text("Deletar")
                    }
                }
            }
        }
    }
}





@Composable
fun AttributeInput(label: String, value: Int, onValueChange: (Int) -> Unit) {
    Column {
        Text(text = label)
        Row(verticalAlignment = Alignment.CenterVertically) {
            Button(onClick = { if (value > 8) onValueChange(value - 1) }) {
                Text("-")
            }
            Text(text = value.toString(), modifier = Modifier.padding(16.dp))
            Button(onClick = { if (value < 15) onValueChange(value + 1) }) {
                Text("+")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PersonagemDNDTheme {
        RaceSelectionScreen(rememberNavController()) {}
    }
}
