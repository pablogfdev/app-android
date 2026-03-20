package com.example.airport.ui.base.composables

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextOverflow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(icon: ImageVector?, onSort: () -> Unit) {
    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
        title = {
            Text(
                "Aeropuertos",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        actions = {
            icon?.let {
                IconButton(onClick = { onSort() }) {
                    Icon(
                        imageVector = it,
                        contentDescription = "Listar Aeropuertos"
                    )
                }
            }
        },
    )
}