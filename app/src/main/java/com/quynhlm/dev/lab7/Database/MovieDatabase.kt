package com.quynhlm.dev.lab7.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.quynhlm.dev.lab7.Dao.MovieDao
import com.quynhlm.dev.lab7.Models.Movie
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [Movie::class], version = 1)
abstract class MovieDatabase : RoomDatabase(){
    abstract fun  getMovieDao() : MovieDao

    companion object{
        @Volatile

        private var instance : MovieDatabase? = null
        fun getInstance(context: Context) : MovieDatabase {
            if(instance == null){
                instance = Room.databaseBuilder(context,MovieDatabase::class.java,"MovieDatabase").build()
            }
            return instance!!
        }
    }

    private class MovieDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            instance?.let { database ->
                scope.launch {
                    populateDatabase(database.getMovieDao())
                }
            }
        }

        suspend fun populateDatabase(movieDao: MovieDao) {
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
            movieDao.insert(movies)
        }
    }
}