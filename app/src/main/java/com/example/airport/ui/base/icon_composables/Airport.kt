package com.example.airport.ui.base.icon_composables

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

@Composable
fun airportIcon(): ImageVector {
    return remember {
        ImageVector.Builder(
            name = "Connecting_airports",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 960f,
            viewportHeight = 960f
        ).apply {
            path(
                fill = SolidColor(Color.Black),
                fillAlpha = 1.0f,
                stroke = null,
                strokeAlpha = 1.0f,
                strokeLineWidth = 1.0f,
                strokeLineCap = StrokeCap.Butt,
                strokeLineJoin = StrokeJoin.Miter,
                strokeLineMiter = 1.0f,
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(624f, 856f)
                lineTo(520f, 680f)
                horizontalLineTo(400f)
                quadToRelative(-17f, 0f, -28.5f, -11.5f)
                reflectiveQuadTo(360f, 640f)
                reflectiveQuadToRelative(11.5f, -28.5f)
                reflectiveQuadTo(400f, 600f)
                horizontalLineToRelative(120f)
                lineToRelative(104f, -176f)
                horizontalLineToRelative(44f)
                lineToRelative(-52f, 176f)
                horizontalLineToRelative(114f)
                lineToRelative(30f, -40f)
                horizontalLineToRelative(40f)
                lineToRelative(-24f, 80f)
                lineToRelative(24f, 80f)
                horizontalLineToRelative(-40f)
                lineToRelative(-30f, -40f)
                horizontalLineTo(616f)
                lineToRelative(52f, 176f)
                close()
                moveTo(292f, 536f)
                lineToRelative(52f, -176f)
                horizontalLineTo(230f)
                lineToRelative(-30f, 40f)
                horizontalLineToRelative(-40f)
                lineToRelative(24f, -80f)
                lineToRelative(-24f, -80f)
                horizontalLineToRelative(40f)
                lineToRelative(30f, 40f)
                horizontalLineToRelative(114f)
                lineToRelative(-52f, -176f)
                horizontalLineToRelative(44f)
                lineToRelative(104f, 176f)
                horizontalLineToRelative(120f)
                quadToRelative(17f, 0f, 28.5f, 11.5f)
                reflectiveQuadTo(600f, 320f)
                reflectiveQuadToRelative(-11.5f, 28.5f)
                reflectiveQuadTo(560f, 360f)
                horizontalLineTo(440f)
                lineTo(336f, 536f)
                close()
            }
        }.build()
    }

}


