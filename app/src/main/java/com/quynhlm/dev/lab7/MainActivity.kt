package com.quynhlm.dev.lab7

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import coil.compose.AsyncImage
import com.quynhlm.dev.lab7.Models.Movie
import com.quynhlm.dev.lab7.ViewModel.MovieViewModel
import com.quynhlm.dev.lab7.ui.theme.Lab7Theme

class MainActivity : ComponentActivity() {
    private val movieViewModel: MovieViewModel by lazy {
        ViewModelProvider(this, MovieViewModel.MovieViewModelFactory(this.application)).get(
            MovieViewModel::class.java
        )
    }
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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lab7Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
//                    movieViewModel.insertNote(movies = movies)
//                    MovieListGrid(movieViewModel = movieViewModel)
                    ScreenNavigation(movieViewModel = movieViewModel)
                }
            }
        }
    }
}

@Composable
fun MovieListGrid(movieViewModel: MovieViewModel){
    val moviesState =
        movieViewModel.getAllNote().observeAsState(initial = emptyList())
    val gridState = rememberLazyStaggeredGridState()
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        state = gridState,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalItemSpacing = 8.dp,
        contentPadding = PaddingValues(8.dp)
    ) {
        items(moviesState.value) { index ->
            MovieItem(movie = index)
        }
    }
}

@Composable
fun MovieItem(movie : Movie) {
    Card (colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)){
        Column (modifier = Modifier
            .width(175.dp)
            .height(330.dp)){
            AsyncImage(
                model = movie.imageUrl,
                contentScale = ContentScale.Crop,
                contentDescription = null,
                modifier = Modifier
                    .height(255.dp)
                    .fillMaxWidth()
                    .clip(
                        RoundedCornerShape(
                            topEnd = 8.dp, topStart =
                            8.dp
                        )
                    ),
            )
            Column(modifier = Modifier.padding(8.dp)) {
                Text(text = movie.title, style =
                MaterialTheme.typography.titleSmall, maxLines = 2)
                BoldValueText(label = "Thời lượng: ", value = movie.time+"'")
                BoldValueText(label = "Khởi chiếu: ", value = movie.startUp)
            }
        }
    }
}

@Composable
fun BoldValueText(label: String, value: String) {
    Text(
        text = buildAnnotatedString {
            withStyle(style = SpanStyle(fontSize = 14.sp, fontWeight = FontWeight.Normal)) {
                append(label)
            }
            withStyle(style = SpanStyle(fontSize = 14.sp, fontWeight = FontWeight.Bold)) {
                append(value)
            }
        }
    )
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Lab7Theme {
        Greeting("Android")
    }
}