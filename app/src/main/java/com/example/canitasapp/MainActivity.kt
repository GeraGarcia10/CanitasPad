package com.example.canitasapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

import com.example.canitasapp.ui.theme.CanitasAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CanitasAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainScreen(Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    var selectedTabIndex by remember { mutableStateOf(0) }
    val tabs = listOf("Tareas", "Notas")
    var taskList by remember { mutableStateOf(mutableListOf("Tarea")) }
    var noteList by remember { mutableStateOf(mutableListOf("Nota")) }
    var searchQuery by remember { mutableStateOf("") }

    // Estado para el diálogo
    var showDialog by remember { mutableStateOf(false) }
    var newItemName by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Canitas Pad") }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                showDialog = true // Mostrar el diálogo al hacer clic
            }) {
                Icon(Icons.Default.Add, contentDescription = "Añadir")
            }
        },
        modifier = modifier
    ) {
        Column {
            // Barra de búsqueda
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                label = { Text("Buscar") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )

            // Pestañas de Tasks y Notes
            TabRow(selectedTabIndex = selectedTabIndex) {
                tabs.forEachIndexed { index, tab ->
                    Tab(
                        selected = selectedTabIndex == index,
                        onClick = { selectedTabIndex = index },
                        text = { Text(tab) }
                    )
                }
            }

            // Mostrar la lista dependiendo de la pestaña seleccionada y el término de búsqueda
            if (selectedTabIndex == 0) {
                TaskList(taskList.filter { it.contains(searchQuery, ignoreCase = true) })
            } else {
                NoteList(noteList.filter { it.contains(searchQuery, ignoreCase = true) })
            }
        }
    }

    // Diálogo para agregar tarea o nota
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("Añadir nueva ${if (selectedTabIndex == 0) "tarea" else "nota"}") },
            text = {
                OutlinedTextField(
                    value = newItemName,
                    onValueChange = { newItemName = it },
                    label = { Text("Ingresa titulo") }
                )
            },
            confirmButton = {
                TextButton(onClick = {
                    if (newItemName.isNotBlank()) {
                        if (selectedTabIndex == 0) {
                            taskList.add(newItemName) // Agregar nueva tarea
                        } else {
                            noteList.add(newItemName) // Agregar nueva nota
                        }
                        newItemName = "" // Reiniciar el texto
                        showDialog = false // Cerrar el diálogo
                    }
                }) {
                    Text("Añadir")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDialog = false }) {
                    Text("Cancelar")
                }
            }
        )
    }
}

@Composable
fun TaskList(tasks: List<String>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(tasks.size) { index ->
            TaskItem(title = tasks[index], description = "Descripción de ${tasks[index]}")
        }
    }
}

@Composable
fun TaskItem(title: String, description: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = title, style = MaterialTheme.typography.titleLarge)
            Text(text = description, style = MaterialTheme.typography.bodyMedium)
        }
    }
}

@Composable
fun NoteList(notes: List<String>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(notes.size) { index ->
            NoteItem(title = notes[index], description = "Descripción de ${notes[index]}")
        }
    }
}

@Composable
fun NoteItem(title: String, description: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = title, style = MaterialTheme.typography.titleLarge)
            Text(text = description, style = MaterialTheme.typography.bodyMedium)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    CanitasAppTheme {
        MainScreen()
    }
}


