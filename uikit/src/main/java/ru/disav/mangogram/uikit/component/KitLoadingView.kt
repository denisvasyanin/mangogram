package ru.disav.mangogram.uikit.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ru.disav.mangogram.uikit.theme.MangoGramTheme

@Composable
fun KitLoadingView() {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            color = MangoGramTheme.colorScheme.primary
        )
    }
}

@Preview
@Composable
internal fun KitLoadingPreview() {
    MangoGramTheme {
        Surface {
            KitLoadingView()
        }
    }
}
