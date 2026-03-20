package com.example.airport.ui.base.composables

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.airport.ui.common.Separations

@Composable
fun SmallSpace() = Spacer(modifier = Modifier.size(Separations.Small))

@Composable
fun MediumSpace() = Spacer(modifier = Modifier.size(Separations.Medium))