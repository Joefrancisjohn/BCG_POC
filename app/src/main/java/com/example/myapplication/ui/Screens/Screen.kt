package com.example.myapplication.ui.Screens

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.R
import com.example.myapplication.models.DetailsData
import com.example.myapplication.models.NetworkResult
import com.example.myapplication.models.Result
import com.example.myapplication.models.TopStories
import com.example.myapplication.topnews.TopNewsViewModel
import com.example.myapplication.ui.TopNewsNavScreens

@Composable
fun MainScreen(
    vm: TopNewsViewModel,
    detailsData: DetailsData,
    result: NetworkResult<TopStories>,
    onButtonClick: () -> Unit
) {
    when (result) {

        is NetworkResult.Loading -> LoadingScreen()
        is NetworkResult.Success -> TopNewsListScreen(results = result.data.results,
            detailsData = detailsData,
            viewModel = vm,
            navigateUp = {})

        is NetworkResult.Error -> {
            //IdleScreen(onButtonClick)
            Toast.makeText(LocalContext.current, result.message.toString(), Toast.LENGTH_SHORT)
                .show()
        }

        is NetworkResult.Exception -> Toast.makeText(
            LocalContext.current, result.e.message, Toast.LENGTH_SHORT
        ).show()
    }
}

@Composable
fun IdleScreen(onButtonClick: () -> Unit) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Button(onClick = onButtonClick) {
            Text(text = "Fetch Animals")
        }
    }
}

@Composable
fun LoadingScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}

@Composable
fun TopNewsListScreen(
    results: List<Result>,
    detailsData: DetailsData,
    viewModel: TopNewsViewModel,
    navigateUp: () -> Unit,
    navController: NavHostController = rememberNavController(),
) {

    val backStackEntry by navController.currentBackStackEntryAsState()
    TopNewsNavScreens.valueOf(
        backStackEntry?.destination?.route ?: TopNewsNavScreens.Main.name
    )
    Scaffold(topBar = {
        TopAppBar(title = { Text(stringResource(id = R.string.app_name)) },/*colors = TopAppBarDefaults.mediumTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer
            ),*/
            modifier = Modifier.padding(top = 20.dp), navigationIcon = {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }

            })
    }) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = TopNewsNavScreens.Main.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = TopNewsNavScreens.Main.name) {
                TopNewsScreen(results = results, onItemClicked = { detail ->
                    /*viewModel.detailsData.value = detail
                    navController.navigate(TopNewsNavScreens.Details.name)*/

                })
            }
            composable(route = TopNewsNavScreens.Details.name) {
                DetailsScreen(detailsData = detailsData, {})
            }
        }
    }

}



