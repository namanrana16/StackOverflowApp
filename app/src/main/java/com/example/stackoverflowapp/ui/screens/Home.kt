package com.example.stackoverflowapp.ui.screens

import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import com.example.stackoverflowapp.data.Item
import com.example.stackoverflowapp.data.StackOverflowModel
import java.time.Instant
import java.time.ZoneId

@Composable
fun HomeScreen() {


    var avgViewCount :Int = 0
    var avgAnsCount:Int = 0
    var indices :Int = 1

    val homeViewModel = viewModel(modelClass = HomeViewModel::class.java)
    val state by homeViewModel.state.collectAsState()
//
    for (item in state){
        avgViewCount += item.viewCount
        avgAnsCount += item.answerCount
        indices++
    }
    avgAnsCount /= indices
    avgViewCount /= indices






    Column(Modifier.fillMaxSize()) {

        Row(modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize(align = Alignment.TopCenter)) {

            Card(
                Modifier
                    .wrapContentSize(Alignment.Center)
                    .padding(10.dp)) {
                Text(text = "Avg View count  $avgViewCount")
            }

            Spacer(modifier = Modifier.width(10.dp))

            Card(
                Modifier
                    .wrapContentSize(Alignment.Center)
                    .padding(10.dp)) {
                Text(text = "Avg Ans Count  $avgAnsCount")
            }
        }

        Spacer(modifier = Modifier.width(10.dp))

        LazyColumn {
            if (state.isEmpty()) {
                item {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .fillMaxSize()
                            .wrapContentSize(align = Alignment.Center)
                    )
                }

            }

            items(state) { items:Item ->
                CharacterImageCard(items)

            }


        }

    }



}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CharacterImageCard(items:Item) {
    val imagerPainter = rememberImagePainter(items.owner.profileImage)
    val context = LocalContext.current

    val dt = Instant.ofEpochSecond(items.creationDate.toLong())
        .atZone(ZoneId.systemDefault())
        .toLocalDateTime()




    Card(
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier.padding(16.dp), onClick = { CustomTabsIntent.Builder().build().launchUrl(context, Uri.parse(items.link)) }
    ) {
        Box {

            Image(
                painter = imagerPainter,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.FillBounds
            )

            Surface(
                color = MaterialTheme.colors.onSurface.copy(alpha = .3f),
                modifier = Modifier.align(Alignment.BottomCenter),
                contentColor = MaterialTheme.colors.surface
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp)
                ) {
                    Text(text = "Username: ${items.owner.displayName}")
                    Text(text = "Question: ${items.title}", maxLines = 3)
                    Text(text = "Date: $dt")
                }
            }


        }


    }


}
