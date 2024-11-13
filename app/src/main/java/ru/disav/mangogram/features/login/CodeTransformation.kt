package ru.disav.mangogram.features.login

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText

fun codeTransformation(trimmed: AnnotatedString): TransformedText {
    val codeMask = "X X X X X X"

    val annotatedString = AnnotatedString
        .Builder()
        .run {
            for (i in trimmed.indices) {
                append(trimmed[i])
                if (length < 11) append(" ")
            }
            pushStyle(SpanStyle(color = Color.LightGray))
            append(codeMask.takeLast(codeMask.length - length))
            toAnnotatedString()
        }


    val offsetTranslator = object : OffsetMapping {
        override fun originalToTransformed(offset: Int) = when {
            offset == 0 -> 0
            else -> offset * 2 - 1
        }

        override fun transformedToOriginal(offset: Int) = when {
            offset == 0 -> 0
            else -> (offset / 2) + 1
        }.coerceAtMost(trimmed.length)
    }

    return TransformedText(annotatedString, offsetTranslator)
}
