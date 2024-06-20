package com.example.myapplication.ui.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.myapplication.models.DetailsData
import com.example.myapplication.models.Result

@Composable
fun TopNewsScreen(results: List<Result>, onItemClicked: (DetailsData) -> Unit) {
    LazyColumn {
        items(items = results) { result ->
            ResultItem(
                result = result,
                goToDetails = {
                    onItemClicked(
                        DetailsData(
                            abstract = result.abstract,
                            title = result.title,
                            url = result.multimedia?.let { it[0].url })
                    )
                })
            Divider(color = Color.LightGray, modifier = Modifier.padding(top = 4.dp, bottom = 4.dp))
        }

    }
}


//@Composable
fun openDetails(detailsData: DetailsData) {
    //Toast.makeText(LocalContext.current , "Clicked ${it.title}" , Toast.LENGTH_SHORT).show()

}

@OptIn(ExperimentalCoilApi::class)
@Composable
fun ResultItem(result: Result, goToDetails: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .clickable {
                goToDetails()
            }
    ) {
        val url = result.multimedia?.let { it[0].url }
        val painter = rememberImagePainter(data = url)
        Image(
            painter = painter,
            contentDescription = null,
            modifier = Modifier.size(100.dp),
            contentScale = ContentScale.FillHeight
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 4.dp)
        ) {
            Text(text = result.title, fontWeight = FontWeight.Bold)
            Text(text = result.title)
        }
    }
}