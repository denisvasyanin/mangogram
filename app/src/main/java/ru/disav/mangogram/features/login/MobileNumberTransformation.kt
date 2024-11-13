package ru.disav.mangogram.features.login

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText

fun mobileNumberTransformation(trimmed: AnnotatedString): TransformedText {
    val phoneMask = "+7 (999) 999-99-99"
    val prefix = "+7 "

    val annotatedString = AnnotatedString
        .Builder()
        .run {
            append(prefix)
            for (i in trimmed.indices) {
                if (i == 0) append("(")
                append(trimmed[i])
                if (i == 5 || i == 7) append("-")
                if (i == 2) append(") ")
            }
            pushStyle(SpanStyle(color = Color.LightGray))
            append(phoneMask.takeLast(phoneMask.length - length))
            toAnnotatedString()
        }

    val phoneNumberOffsetTranslator = object : OffsetMapping {
        override fun originalToTransformed(offset: Int) = when {
            offset < 3 -> offset + prefix.length + 1
            offset < 6 -> offset + prefix.length + 3
            offset < 8 -> offset + prefix.length + 4
            else -> offset + prefix.length + 5
        }

        override fun transformedToOriginal(offset: Int) = when {
            offset == 0 -> 0
            offset < 3 -> offset + prefix.length - 1
            offset < 6 -> offset + prefix.length - 3
            offset < 8 -> offset + prefix.length - 4
            else -> offset + prefix.length - 5
        }.coerceAtMost(trimmed.length)
    }

    return TransformedText(annotatedString, phoneNumberOffsetTranslator)
}
