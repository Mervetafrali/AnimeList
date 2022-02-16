package com.example.animelist

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.example.animelist.helpers.AnimesView
import com.example.animelist.model.Animes
import com.example.animelist.ui.theme.AnimeListTheme



class MainActivity : ComponentActivity() {
    private val animeModel = AnimesView()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AnimeListTheme {
                Surface(color = MaterialTheme.colors.background) {
                    AnimeList(animeModel)
                }
            }
        }
    }
}

@Composable
fun AnimeList(aV: AnimesView) {
    LaunchedEffect(Unit, block = {
        aV.getTodoList()
    })
    LazyColumn {
        items(aV.animeList) { anime ->
            AnimeCard(anime)
        }
    }
}


@Composable
fun AnimeCard(anime: Animes) {
    Row(modifier = Modifier.padding(all = 20.dp)) {
        var url= "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRvV9aa86UM1C1LqGt3176-tFFxLUxLdd8TjU8VLjemDGJTEnnygTcwzqRueUJmeUvOWM8&usqp=CAU"
        if(!anime.image.equals("https://www.anime-planet.com/inc/img/card-load.svg"))
            url=anime.image.drop(28)
        Image(
            painter=rememberImagePainter(
                data = url,

            ),
            contentDescription = "Anime",
            modifier = Modifier
                .size(120.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        // Add a horizontal space between the image and the column
        Spacer(modifier = Modifier.width(8.dp))

        Column {
            Text(text = anime.title, color = MaterialTheme.colors.secondaryVariant)
            // Add a vertical space between the author and message texts
            Spacer(modifier = Modifier.height(4.dp))
            Surface(shape = MaterialTheme.shapes.medium, elevation = 1.dp) {
                Text(
                    text = anime.address,
                    modifier = Modifier.padding(all = 4.dp),
                    style = MaterialTheme.typography.body2
                )
            }

        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AnimeListTheme {
        AnimeCard(anime = Animes("1", "1", "1"))
    }
}



