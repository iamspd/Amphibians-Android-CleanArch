package com.example.apipractice.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.apipractice.R
import com.example.apipractice.di.AppViewModelProvider
import com.example.apipractice.domain.model.Amphibian

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val uiState by homeViewModel.homeUiState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = stringResource(id = R.string.app_name)) }
            )
        }
    ) { innerPadding ->

        when {
            uiState.isLoading -> {
                LoadingScreen(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues = innerPadding)
                )
            }

            uiState.errorMessage != null -> {
                ErrorScreen(
                    onRetryClick = homeViewModel::getAmphibians,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues = innerPadding)
                )
            }

            else -> {
                AmphibianList(
                    amphibians = uiState.amphibians,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues = innerPadding)
                )
            }
        }
    }
}

@Composable
fun ErrorScreen(
    onRetryClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_connection_error),
            contentDescription = null,
            modifier = Modifier
                .size(dimensionResource(id = R.dimen.img_scale))
                .padding(bottom = dimensionResource(id = R.dimen.spacing_medium))
        )
        Text(
            text = stringResource(R.string.connection_error_text),
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(
            dimensionResource(R.dimen.spacing_medium)))
        Button(onClick = onRetryClick) {
            Text(text = stringResource(R.string.retry_button))
        }
    }
}

@Composable
fun LoadingScreen(
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Image(
            painter = painterResource(id = R.drawable.loading_img),
            modifier = Modifier.size(
                dimensionResource(R.dimen.img_scale)
            ),
            contentDescription = null
        )
    }
}

@Composable
fun AmphibianList(
    amphibians: List<Amphibian>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(
            space = dimensionResource(id = R.dimen.spacing_medium)
        ),
        contentPadding = PaddingValues(
            horizontal = dimensionResource(id = R.dimen.spacing_medium),
            vertical = dimensionResource(id = R.dimen.spacing_medium)
        ),
        modifier = modifier
    ) {
        items(items = amphibians, key = { amphibian -> amphibian.name }) { amphibian ->
            AmphibianCardListItem(
                amphibian = amphibian,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
fun AmphibianCardListItem(
    amphibian: Amphibian,
    modifier: Modifier = Modifier
) {
    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = dimensionResource(id = R.dimen.card_elevation)
        ),
        modifier = modifier
    ) {
        AsyncImage(
            model = ImageRequest.Builder(context = LocalContext.current)
                .data(data = amphibian.imgUrl)
                .crossfade(enable = true)
                .build(),
            modifier = Modifier
                .fillMaxWidth()
                .height(dimensionResource(R.dimen.img_scale)),
            placeholder = painterResource(id = R.drawable.loading_img),
            error = painterResource(id = R.drawable.ic_broken_image),
            contentDescription = null,
            contentScale = ContentScale.FillWidth
        )

        Column(
            verticalArrangement = Arrangement.spacedBy(
                dimensionResource(R.dimen.spacing_extra_small)
            ),
            modifier = Modifier
                .padding(
                    top = dimensionResource(R.dimen.spacing_small),
                    start = dimensionResource(id = R.dimen.spacing_medium),
                    end = dimensionResource(id = R.dimen.spacing_medium),
                    bottom = dimensionResource(id = R.dimen.spacing_medium)
                )
        ) {
            Text(
                text = amphibian.name,
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                text = amphibian.description,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}