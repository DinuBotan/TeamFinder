package com.project.teamfinder.ui.conversation

import androidx.compose.runtime.Immutable
import com.project.teamfinder.R

class ConversationUiState(
    initialMessages: List<Message>
) {
    val messages: MutableList<Message> = mutableListOf(*initialMessages.toTypedArray())

    fun addMessage(msg: Message) {
        messages.add(0, msg)
    }

}

@Immutable
data class Message(
    val author: String,
    val content: String,
    val timestamp: String,
    val image: Int? = null,
    val authorImage: Int = if (author == "me") R.drawable.dinu1 else R.drawable.someone_else
)