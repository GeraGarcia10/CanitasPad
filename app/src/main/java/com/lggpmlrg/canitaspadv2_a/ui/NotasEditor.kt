package com.lggpmlrg.canitaspadv2_a.ui

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.lggpmlrg.canitaspadv2_a.AppViewModelProvider
import com.lggpmlrg.canitaspadv2_a.R
import com.lggpmlrg.canitaspadv2_a.viewModel.NotaDetails
import com.lggpmlrg.canitaspadv2_a.viewModel.NotaUiState
import com.lggpmlrg.canitaspadv2_a.viewModel.NotasEditorViewModel

import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun EditorNotas(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    navController: NavHostController,
    viewModel: NotasEditorViewModel = viewModel(factory = AppViewModelProvider.Factory)
){
    val coroutineScope = rememberCoroutineScope()

    Column (
        modifier= Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row (horizontalArrangement = Arrangement.Start) {
            IconButton(onClick = {
                navController.navigate(Routes.NotasScreen.route) }
            ){
                Icon(imageVector = Icons.Filled.KeyboardArrowLeft, contentDescription = "Regresar",
                    modifier = Modifier
                        .size(32.dp)
                        .padding(0.dp)
                )
            }
        }
        NotaEntryBody(
            notaUiState = viewModel.notaUiState,
            onNotaValueChange = viewModel::updateUiState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.saveNota()
                    navigateBack()
                }
            },
            modifier = Modifier
                .padding(5.dp)
                .verticalScroll(rememberScrollState())
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.weight(1f))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(onClick = { /*TODO*/ }) {
                Image(
                    modifier = Modifier
                        .size(40.dp)
                        .padding(4.dp),
                    painter = painterResource(R.drawable.camara_fotografica),
                    contentDescription = null
                )
            }
            Button(onClick = { /*TODO*/ }) {
                Image(
                    modifier = Modifier
                        .size(40.dp)
                        .padding(4.dp),
                    painter = painterResource(R.drawable.carpeta),
                    contentDescription = null
                )
            }
            Button(onClick = { /*TODO*/ }) {
                Image(
                    modifier = Modifier
                        .size(40.dp)
                        .padding(4.dp),
                    painter = painterResource(R.drawable.microfono),
                    contentDescription = null
                )
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NotaEntryBody(
    notaUiState: NotaUiState,
    onNotaValueChange: (NotaDetails) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
){
    Column() {
        NotaInputForm(
            notaDetails = notaUiState.notaDetails,
            onValueChange = onNotaValueChange,
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = onSaveClick,
            enabled = notaUiState.isEntryValid,
            shape = MaterialTheme.shapes.small,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(stringResource(R.string.save_action))
        }
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NotaInputForm(
    notaDetails: NotaDetails,
    modifier: Modifier = Modifier,
    onValueChange: (NotaDetails) -> Unit = {},
    enabled: Boolean = true
) {
    Column(
    ) {
        OutlinedTextField(
            value = notaDetails.name,
            onValueChange = { onValueChange(notaDetails.copy(name = it)) },
            label = { Text(stringResource(R.string.titulo)) },
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                unfocusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                disabledContainerColor = MaterialTheme.colorScheme.secondaryContainer,
            ),
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        val currentDateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))
        Text(
            text = currentDateTime,
            style = MaterialTheme.typography.bodySmall
        )
        OutlinedTextField(
            value = notaDetails.contenido,
            onValueChange = { onValueChange(notaDetails.copy(contenido = it)) },
            label = { Text(stringResource(R.string.contenido)) },
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                unfocusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                disabledContainerColor = MaterialTheme.colorScheme.secondaryContainer,
            ),
            //leadingIcon = { Text(Currency.getInstance(Locale.getDefault()).symbol) },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = false
        )
    }
}

