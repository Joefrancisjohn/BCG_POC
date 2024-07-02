package com.example.myapplication.ui.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.myapplication.models.DetailsData
import com.example.myapplication.theme.TopNewsComposeTheme


@OptIn(ExperimentalCoilApi::class)
@Composable
fun DetailsScreen(detailsData: DetailsData, goBack: () -> Unit) {
    Scaffold() { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(innerPadding)
        ) {

            val url = detailsData.url
            val painter = rememberImagePainter(data = url)
            Text(text = detailsData.title, fontWeight = FontWeight.Bold)
            Image(
                painter = painter,
                contentDescription = null,
                modifier = Modifier.size(100.dp),
                contentScale = ContentScale.FillHeight
            )
            Text(text = detailsData.abstract)
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 4.dp)
            ) {
                Text(text = detailsData.title, fontWeight = FontWeight.Bold)
                Text(text = detailsData.abstract)
            }

        }
    }

}

@Preview
@Composable
fun DetailsScreenPreview() {
    TopNewsComposeTheme {
        // A surface container using the 'background' color from the theme
        Surface(color = MaterialTheme.colors.background) {
            DetailsScreen(DetailsData(
                url = "https://static01.nyt.com/images/2024/06/10/multimedia/10france-election-explainer-bphq/10france-election-explainer-bphq-superJumbo.jpg",
                title = "BLA BLA BLA",
                abstract = "BLI BLI BLI"

            ), {})
        }
    }
}