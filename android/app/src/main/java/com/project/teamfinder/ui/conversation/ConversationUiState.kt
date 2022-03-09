package com.project.teamfinder.ui.conversation

import android.util.Log
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.mutableStateListOf
import com.project.teamfinder.R

class ConversationUiState(
    initialMessages: List<Message>
) {
    val messages: MutableList<Message> = mutableStateListOf(*initialMessages.toTypedArray())

    fun addMessage(msg: Message) {
        for(ms: Message in messages) {
            Log.d("AddMessage" , ms.content)
        }
        Log.d("AddMessage now", msg.content)
        messages.add(0, msg) // We want to add to the beginning of the list
    }

}

@Immutable
data class Message(
    val author: String,
    val content: String,
    val timestamp: String,
    var chatRoomID: String,
    val image: Int? = null,
    val authorImage: Int = if (author == "me") R.drawable.dinu1 else R.drawable.someone_else,
)