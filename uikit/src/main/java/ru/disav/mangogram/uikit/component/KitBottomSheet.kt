package ru.disav.mangogram.uikit.component

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KitBottomSheet(
    onDismiss: (() -> Unit)? = null,
    content: @Composable ColumnScope.(() -> Unit) -> Unit,
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val coroutineScope = rememberCoroutineScope()
    val hideModalBottomSheet: () -> Unit = { coroutineScope.launch { sheetState.hide() } }

    ModalBottomSheet(
        sheetState = sheetState,
        containerColor = Color.White,
        onDismissRequest = {
            onDismiss?.invoke()
        },
        content = {
            content(hideModalBottomSheet)
        },
    )
}
