package com.lggpmlrg.canitaspadv2_a

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.lggpmlrg.canitaspadv2_a.notaDetails.ItemDetailsViewModel
import com.lggpmlrg.canitaspadv2_a.notasDetails.ItemEntryViewModel
import com.lggpmlrg.canitaspadv2_a.notasDetails.NotaEditViewModel
import com.lggpmlrg.canitaspadv2_a.tareasDetails.TareaDetailsViewModel
import com.lggpmlrg.canitaspadv2_a.tareasDetails.TareaEntryViewModel
import com.lggpmlrg.canitaspadv2_a.tareasDetails.TareasEditViewModel
import com.lggpmlrg.canitaspadv2_a.viewModel.NotasEditorViewModel
import com.lggpmlrg.canitaspadv2_a.viewModel.NotasScreenViewModel
import com.lggpmlrg.canitaspadv2_a.viewModel.TareasEditorViewModel
import com.lggpmlrg.canitaspadv2_a.viewModel.TareasScreenViewModel

object AppViewModelProvider{
    @RequiresApi(Build.VERSION_CODES.O)
    val Factory = viewModelFactory {
        initializer {
            NotasEditorViewModel(notasApplication().container.notasRepository)
        }

        initializer {
            TareasEditorViewModel(notasApplication().container.tareasRepository)
        }

        initializer {
            NotasScreenViewModel(notasApplication().container.notasRepository)
        }

        initializer {
            TareasScreenViewModel(notasApplication().container.tareasRepository)
        }
        // Initializer for ItemEditViewModel
        initializer {
            NotaEditViewModel(
                this.createSavedStateHandle(),
                notasApplication().container.notasRepository
            )
        }
        // Initializer for ItemEntryViewModel
        initializer {
            ItemEntryViewModel(notasApplication().container.notasRepository)
        }

        // Initializer for ItemDetailsViewModel
        initializer {
            ItemDetailsViewModel(
                this.createSavedStateHandle(),
                notasApplication().container.notasRepository
            )
        }

        // Initializer for ItemEditViewModel
        initializer {
            TareasEditViewModel(
                this.createSavedStateHandle(),
                notasApplication().container.tareasRepository
            )
        }

        // Initializer for ItemEntryViewModel
        initializer {
            TareaEntryViewModel(notasApplication().container.tareasRepository)
        }

        // Initializer for ItemDetailsViewModel
        initializer {
            TareaDetailsViewModel(
                this.createSavedStateHandle(),
                notasApplication().container.tareasRepository
            )
        }
    }
}

fun CreationExtras.notasApplication(): NotasApplication =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as NotasApplication)