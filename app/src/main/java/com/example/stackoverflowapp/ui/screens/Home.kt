package com.example.stackoverflowapp.ui.screens

import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
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
import com.example.stackoverflowapp.datastore.StoreUserClick
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.ZoneId

@Composable
fun HomeScreen() {

    val context = LocalContext.current
    // scope
    val scope = rememberCoroutineScope()
    // datastore Email
    val dataStore = StoreUserClick(context)
    // get saved email
    val savedCount = dataStore.getCount.collectAsState(initial = 0)

    var clickCount = savedCount.value
    var showAd: Boolean by remember {
        mutableStateOf(true)
    }
    if (clickCount != null) {
        if (clickCount>3){
            showAd = false
        }
    }
    var email :Int =0

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

    if (showAd) {
        Row(modifier = Modifier
            .fillMaxWidth().wrapContentSize(align = Alignment.BottomCenter)) {

            Image(painter = rememberImagePainter( "https://fakeimg.pl/350x200/?text=World&font=lobster"),
                contentDescription = "Stack Overflow",
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(300.dp)
                    .height(200.dp))

            Button(onClick = {
                ++email
                scope.launch {
                    dataStore.saveCount(email+clickCount!!)
                    showAd = false
                    //Toast.makeText(context, "$email  +  $clickCount", Toast.LENGTH_SHORT).show()
                }
            },
                Modifier.size(36.dp),
                shape = MaterialTheme.shapes.medium) {

            }
        }
    }



        Spacer(modifier = Modifier.width(10.dp))

        LazyColumn {
            if (state.isEmpty()) {
                item {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .wrapContentSize(align = Alignment.Center)
                    )
                }

            }

            items(state) { items:Item ->
                CharacterImageCard(items)

            }


        }

        Spacer(modifier = Modifier.width(10.dp))





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
