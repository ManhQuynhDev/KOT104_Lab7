package com.quynhlm.dev.lab7

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.quynhlm.dev.lab7.Models.Movie
import com.quynhlm.dev.lab7.ViewModel.MovieViewModel

enum class Screen(val route: String) {
    LOGIN("Login"),
    MOVIE_SCREEN("MovieListGrid"),
    SCREEN1("Screen1"),
    SCREEN2("Screen2"),
    SCREEN3("Screen3")}

@Composable
fun ScreenNavigation(movieViewModel: MovieViewModel) {
    val navController = rememberNavController()

    var movies = listOf(
        Movie(
            title = "The Shawshank Redemption",
            time = "2h 22m",
            imageUrl = "https://m.media-amazon.com/images/M/MV5BNDE3ODcxYzMtY2YzZC00NmNlLWJiNDMtZDViZWM2MzIxZDYwXkEyXkFqcGdeQXVyNjAwNDUxODI@._V1_.jpg",
            describe = "Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency.",
            startUp = "October 14, 1994",
        ),
        Movie(
            title = "The Shawshank Redemption",
            time = "2h 22m",
            imageUrl = "https://m.media-amazon.com/images/M/MV5BNDE3ODcxYzMtY2YzZC00NmNlLWJiNDMtZDViZWM2MzIxZDYwXkEyXkFqcGdeQXVyNjAwNDUxODI@._V1_.jpg",
            describe = "Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency.",
            startUp = "October 14, 1994",
        ),
        Movie(
            title = "The Shawshank Redemption",
            time = "2h 22m",
            imageUrl = "https://m.media-amazon.com/images/M/MV5BNDE3ODcxYzMtY2YzZC00NmNlLWJiNDMtZDViZWM2MzIxZDYwXkEyXkFqcGdeQXVyNjAwNDUxODI@._V1_.jpg",
            describe = "Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency.",
            startUp = "October 14, 1994",
        ),
    )

    movieViewModel.insertNote(movies)

    NavHost(
        navController = navController,
        startDestination = Screen.LOGIN.route,
    ) {
        composable(Screen.LOGIN.route) { LoginScreen(navController) }
        composable(Screen.MOVIE_SCREEN.route) { MovieListGrid(movieViewModel = movieViewModel) }
        composable(Screen.SCREEN1.route) { Screen1(navController = navController) }
        composable(Screen.SCREEN2.route) { Screen2(navController = navController) }
        composable(Screen.SCREEN3.route) { Screen3(navController = navController) }
    }
}

@Composable
fun Screen1(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Red)
            .clickable { navController.navigate(Screen.SCREEN2.route) },
        contentAlignment = Alignment.Center
    ) {
        Text("Screen 1", color = Color.White, fontSize = 20.sp)
    }
}
@Composable
fun Screen2(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Yellow)
            .clickable { navController.navigate(Screen.SCREEN3.route) },
        contentAlignment = Alignment.Center
    ) {
        Text("Screen 2", color = Color.White, fontSize = 20.sp)
    }
}
@Composable
fun Screen3(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Green)
            .clickable { navController.navigate(Screen.SCREEN1.route) },
        contentAlignment = Alignment.Center
    ) {
        Text("Screen 3", color = Color.White, fontSize = 20.sp)
    }
}