package com.project.teamfinder.data

import com.project.teamfinder.ui.conversation.ConversationUiState
import com.project.teamfinder.ui.conversation.Message

public var initialMessages = listOf(
    Message(
        "me",
        "Check it out!",
        "8:07 PM",
        ""
    ),
    Message(
        "me",
        "Thank you!",
        "8:06 PM",
        ""
    ),
    Message(
        "Person 2",
        "You can use all the same stuff",
        "8:05 PM",
        ""
    ),
    Message(
        "me",
        "And this is yet another message, hurray! ",
        "8:05 PM",
        ""
    ),
    Message(
        "Person 2",
        "This is a nice chat indeed, testing it as well! ",
        "8:04 PM",
        ""
    ),
    Message(
        "me",
        "This is a first view of the chat, " +
                "writing random stuff to check how static data is shown on screen ",
        "8:03 PM",
        ""
    )
)
val exampleUiState = ConversationUiState(
    initialMessages = initialMessages,
)