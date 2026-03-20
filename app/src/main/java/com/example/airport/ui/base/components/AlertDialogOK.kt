package com.example.airport.ui.base.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties


@Preview(showBackground = true)
@Composable
fun AlertDialogOKPreview() {
    AlertDialogOK(message = "Mensaje del cuadro dialogo", onDismiss = {})
}

@Composable
fun AlertDialogOK(
    title: String = "",
    message: String,
    onDismiss: () -> Unit // Acción al cerrar el diálogo
) {
    // Estado del diálogo
    val dialogState = remember { mutableStateOf(true) }

    if (dialogState.value) {
        AlertDialog(
            onDismissRequest = onDismiss,
            properties = DialogProperties(
                dismissOnBackPress = false,
                dismissOnClickOutside = false
            ),
            title = {
                if (title.isNotEmpty()) {
                    Text(
                        text = title,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary,
                    )
                }
            },
            text = {
                Text(
                    text = message,
                    fontSize = 18.sp,
                    color = Color.Gray,
                    lineHeight = 22.sp
                )
            },
            confirmButton = {
                Button(
                    onClick = {
                        dialogState.value = false
                        onDismiss()
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = Color.White
                    ),
                    modifier = Modifier.padding(8.dp)
                ) {
                    Text(
                        text = "OK",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                }
            },
            shape = RoundedCornerShape(16.dp), // Bordes redondeados del diálogo
            containerColor = Color.White, // Color de fondo del diálogo
        )
    }
}

@Composable
fun AlertDialogOkOrNot(
    title: String,
    text: String,
    onAccept: () -> Unit,
    onCancel: () -> Unit,
) {
    AlertDialog(
        onDismissRequest = { onCancel() },
        confirmButton = {
            Button(
                onClick = { onAccept() },
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary, contentColor = Color.White),
                modifier = Modifier.padding(8.dp)
            ) { Text(text = "Aceptar", fontWeight = FontWeight.Bold, fontSize = 16.sp) }
        },
        dismissButton = {
            Button(
                onClick = { onCancel() },
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary, contentColor = Color.White),
                modifier = Modifier.padding(8.dp)
            ) { Text(text = "Cancelar", fontWeight = FontWeight.Bold, fontSize = 16.sp) }
        },
        text = { Text(text) },
        title = { Text(title) }

    )

}
