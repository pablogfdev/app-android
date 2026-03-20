package com.example.airport.ui.base.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.airport.ui.base.composables.MediumSpace
import com.example.airport.R

@Preview(showBackground = true)
@Composable
private fun LoadingUiPreview() {
    LoadingUi()
}

@Composable
fun LoadingUi() {
    Box(modifier = Modifier.fillMaxSize().testTag("loadingIndicator"), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            CircularProgressIndicator()
            MediumSpace()
            Text(text = stringResource(id = R.string.progress_message))
        }
    }
}
