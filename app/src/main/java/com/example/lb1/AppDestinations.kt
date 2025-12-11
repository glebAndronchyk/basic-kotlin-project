package com.example.lb1

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed interface AppDestination {
    val icon: ImageVector;
    val label: String;
    val path: String;
    val navigationParams:  List<NamedNavArgument> get() = emptyList()
    fun createRoute(): String = path
}

object Home : AppDestination {
    override val icon: ImageVector
        get() = Icons.Default.Home
    override val label: String
        get() = "Home"
    override val path: String
        get() = "/home"
}

object Watch : AppDestination {
    override val icon: ImageVector
        get() = Icons.Default.Face
    override val label: String
        get() = "Watch"
    override val path: String
        get() = "/watch"

    object Configure : AppDestination {
        override val icon: ImageVector
            get() = TODO("Not yet implemented")
        override val label: String
            get() = TODO("Not yet implemented")
        override val path: String
            get() = "/configure"
        override fun createRoute(): String = "/configure"

        object Narrator : AppDestination {
            override val icon: ImageVector
                get() = TODO("Not yet implemented")
            override val label: String
                get() = TODO("Not yet implemented")
            override val path: String
                get() = "/configure/narrator"
            override fun createRoute(): String = "/configure/narrator"
        }
    }

    object Stream : AppDestination {
        override val icon: ImageVector
            get() = TODO("Not yet implemented")
        override val label: String
            get() = TODO("Not yet implemented")
        override val navigationParams: List<NamedNavArgument>
            get() = listOf(
                navArgument("streamId") { type = NavType.StringType }
            )
        override val path: String
            get() = "/stream/{streamId}"

        fun createRoute(streamId: Number): String = "/stream/$streamId"
    }
}

object Search : AppDestination {
    override val icon: ImageVector
        get() = Icons.Default.Search
    override val label: String
        get() = "Search"
    override val path: String
        get() = "/search"
}

val nav = listOf<AppDestination>(Home, Watch, Search)
