package com.lggpmlrg.canitaspadv2_a.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.lggpmlrg.canitaspadv2_a.tareasDetails.ItemEntryScreen
import com.lggpmlrg.canitaspadv2_a.tareasDetails.TareaDetailsDestination
import com.lggpmlrg.canitaspadv2_a.tareasDetails.TareaEditDestination
import com.lggpmlrg.canitaspadv2_a.tareasDetails.TareaEntryDestination
import com.lggpmlrg.canitaspadv2_a.notaDetails.ItemDetailsDestination
import com.lggpmlrg.canitaspadv2_a.notaDetails.ItemDetailsScreen
import com.lggpmlrg.canitaspadv2_a.notasDetails.ItemEditDestination
import com.lggpmlrg.canitaspadv2_a.notasDetails.ItemEditScreen
import com.lggpmlrg.canitaspadv2_a.model.NotasInfo
import com.lggpmlrg.canitaspadv2_a.model.TareasInfo
import com.lggpmlrg.canitaspadv2_a.tareasDetails.TareaDetailsScreen
import com.lggpmlrg.canitaspadv2_a.tareasDetails.TareaEditScreen
import com.lggpmlrg.canitaspadv2_a.ui.EditorNotas
import com.lggpmlrg.canitaspadv2_a.ui.EditorTareas
import com.lggpmlrg.canitaspadv2_a.ui.NotasList
import com.lggpmlrg.canitaspadv2_a.ui.Routes
import com.lggpmlrg.canitaspadv2_a.ui.TareasList
import com.lggpmlrg.canitaspadv2_a.viewModel.TareaDetails


/**
 * Provides Navigation graph for the application.
 */
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun InventoryNavHost(
    navController: NavHostController,
    windowSize : WindowSizeClass,
    modifier: Modifier = Modifier,
) {
    NavHost(navController = navController,
        startDestination = Routes.NotasScreen.route){
        composable(Routes.NotasScreen.route){
            NotasList(notas = NotasInfo.notas,
                navController = navController, windowSize = windowSize.widthSizeClass,
                navigateToItemUpdate = {
                    navController.navigate(
                        "${ItemDetailsDestination.route}/${it}")
                }
            )
            /*
            navigateToItemUpdate = {
                    navController.navigate(
                        "${ItemDetailsDestination.route}/${it}")
                }
             */
        }
        composable(Routes.NotasEditor.route){
            EditorNotas(navigateBack = { navController.popBackStack()},
                navController = navController)
        }

        composable(Routes.TareasEditor.route){
            EditorTareas(navigateBack = { navController.popBackStack()},
                navController = navController, TareaDetails = TareaDetails())
        }


        //Esta es la pequeña ventana que sale antes del esdit, es la preview de la nota
        composable(
            route = ItemDetailsDestination.routeWithArgs,
            arguments = listOf(navArgument(ItemDetailsDestination.itemIdArg) {
                type = NavType.IntType
            })
        ) {
            ItemDetailsScreen(
                navigateToEditItem = { navController.navigate("${ItemEditDestination.route}/$it") },
                navigateBack = { navController.navigateUp() }
            )
        }

        composable(
            route = ItemEditDestination.routeWithArgs,
            arguments = listOf(navArgument(ItemEditDestination.itemIdArg) {
                type = NavType.IntType
            })
        ) {
            ItemEditScreen(
                navigateBack = { navController.popBackStack() },
                onNavigateUp = { navController.navigateUp() }
            )
        }
        //}}}}}}}}}}}}}}}}}}}}}}}}}}

        composable(Routes.TareasScreen.route){
            TareasList(tareas = TareasInfo.tareas,
                navController = navController, windowSize = windowSize.widthSizeClass,
                navigateToItemUpdate = {
                    navController.navigate(
                        "${TareaDetailsDestination.route}/${it}")
                }
            )
            /*
            navigateToItemUpdate = {
                    navController.navigate(
                        "${ItemDetailsDestination.route}/${it}")
                }
             */
        }

        composable(route = TareaEntryDestination.route) {
            ItemEntryScreen(
                navigateBack = { navController.popBackStack() },
                onNavigateUp = { navController.navigateUp() }
            )
        }
        composable(
            route = TareaDetailsDestination.routeWithArgs,
            arguments = listOf(navArgument(TareaDetailsDestination.tareaIdArg) {
                type = NavType.IntType
            })
        ) {
            TareaDetailsScreen(
                navigateToEditItem = { navController.navigate("${TareaEditDestination.route}/$it") },
                navigateBack = { navController.navigateUp() }
            )
        }
        composable(
            route = TareaEditDestination.routeWithArgs,
            arguments = listOf(navArgument(TareaEditDestination.itemIdArg) {
                type = NavType.IntType
            })
        ) {
            TareaEditScreen(
                navigateBack = { navController.popBackStack() },
                onNavigateUp = { navController.navigateUp() }
            )
        }
    }
}
