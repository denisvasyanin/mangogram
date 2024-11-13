package ru.disav.mangogram.uikit.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import ru.disav.mangogram.uikit.KitIcons
import ru.disav.mangogram.uikit.theme.MangoGramTheme
import ru.disav.mangogram.uikit.theme.CornerRadius12
import ru.disav.mangogram.uikit.theme.Dp16
import ru.disav.mangogram.uikit.theme.Dp32
import ru.disav.mangogram.uikit.theme.Dp48
import ru.disav.mangogram.uikit.theme.Dp8

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KitAppBar(
    title: String,
    shadow: Boolean = true,
    onBackButtonClick: (() -> Unit)? = null,
    actions: @Composable RowScope.() -> Unit = {}
) {
    CenterAlignedTopAppBar(
        modifier = Modifier.then(
            if (shadow) {
                Modifier.shadow(
                    Dp8,
                    shape = RoundedCornerShape(0f, 0f, Dp32.value, Dp32.value),
                )
            } else {
                Modifier
            }
        ), navigationIcon = {
            if (onBackButtonClick != null) {
                Button(
                    modifier = Modifier
                        .width(Dp48)
                        .padding(start = Dp8),
                    onClick = onBackButtonClick,
                    shape = RoundedCornerShape(CornerRadius12),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MangoGramTheme.colorScheme.surfaceBright,
                    ),
                    contentPadding = PaddingValues(horizontal = Dp8)
                ) {
                    Icon(
                        painter = painterResource(id = KitIcons.ArrowBack),
                        contentDescription = null,
                        modifier = Modifier.size(Dp16),
                        tint = MangoGramTheme.colorScheme.onSurface
                    )
                }
            }
        },
        actions = actions,
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MangoGramTheme.colorScheme.surfaceBright,
        ),
        title = {
            Text(
                text = title, style = MangoGramTheme.typography.h3
            )
        })
}

@Preview
@Composable
private fun KitAppBarPreview() {
    MangoGramTheme {
        Surface {
            KitAppBar(title = "Test bar title", onBackButtonClick = {})
        }
    }
}
