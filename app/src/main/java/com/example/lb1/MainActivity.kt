package com.example.lb1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge


import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.lb1.domain.model.Narrator
import kotlinx.coroutines.launch
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.lb1.ui.screens.ConfigureNarratorForm
import com.example.lb1.ui.screens.HomeScreen
import com.example.lb1.ui.screens.SearchScreen
import com.example.lb1.ui.screens.StreamScreen
import com.example.lb1.ui.screens.WatchConfigureScreen
import com.example.lb1.ui.screens.WatchScreen
import com.example.lb1.ui.theme.Lb1Theme
import com.example.lb1.ui.viewmodels.ConfigureNarratorViewModel
import com.example.lb1.ui.viewmodels.WatchConfigureViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lb1Theme {
                Lb1App()
            }
        }
    }
}

@PreviewScreenSizes
@Composable
fun Lb1App() {
    var navigationController = rememberNavController();
    var currentDestination = navigationController.currentBackStackEntryAsState().value?.destination;

    NavigationSuiteScaffold(
        navigationSuiteItems = {
            nav.forEach { appDestination ->
                run {
                    item(
                        icon = {
                            Icon(
                                appDestination.icon,
                                contentDescription = appDestination.label
                            )
                        },
                        label = { Text(appDestination.label) },
                        selected = currentDestination?.route == appDestination.path,
                        onClick = {
                            navigationController.navigate(appDestination.path) {
                                popUpTo(navigationController.graph.findStartDestination().id) {}
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) {
        NavHost(
            navController = navigationController,
            startDestination = Home.path,
            ) {
            composable(Home.path) {
                HomeScreen()
            }
            composable(Search.path) {
                SearchScreen()
            }

            navigation(
                startDestination = Watch.path,
                route = "watch_graph"
            ) {
                composable(Watch.path) {
                    WatchScreen(
                        onStreamClick = { streamId -> navigationController.navigate(Watch.Stream.createRoute(streamId)) },
                        onSettingsClick = { navigationController.navigate(Watch.Configure.createRoute())})
                }

                composable(Watch.Configure.path) {
                    WatchConfigureScreen(navController = navigationController, onNarratorConfigure = { navigationController.navigate(
                        Watch.Configure.Narrator.createRoute())})
                }

                composable(Watch.Configure.Narrator.path) {
                    val configureViewModel: WatchConfigureViewModel = viewModel()
                    val coroutineScope = rememberCoroutineScope()

                    ConfigureNarratorForm(
                        navController = navigationController,
                        onCreate = { dto ->
                            coroutineScope.launch {
                                configureViewModel.createNarrator(dto)
                            }
                        },
                        onUpdate = { dto ->
                            coroutineScope.launch {
                                configureViewModel.updateNarrator(
                                    Narrator(
                                        id = dto.id,
                                        name = dto.name,
                                        avatar = dto.avatar
                                    )
                                )
                            }
                        },
                        onDelete = { id ->
                            coroutineScope.launch {
                                configureViewModel.deleteNarrator(
                                    Narrator(
                                        id = id.toLong(),
                                        name = "",
                                        avatar = ""
                                    )
                                )
                            }
                        }
                    )
                }

                composable(Watch.Stream.path, arguments = Watch.Stream.navigationParams) { backStackEntry ->
                    val streamId = backStackEntry.arguments?.getString("streamId") ?: TODO()

                    StreamScreen(streamId, navigationController)
                }
            }
        }
    }
}

