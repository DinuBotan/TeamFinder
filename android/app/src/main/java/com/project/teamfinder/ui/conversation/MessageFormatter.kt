package com.project.teamfinder.ui.conversation

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.buildAnnotatedString


@Composable
fun messageFormatter(
    text: String
): AnnotatedString {

    return buildAnnotatedString {
        var cursorPosition = 0
            append(text.slice(cursorPosition..text.lastIndex))

    }
}


