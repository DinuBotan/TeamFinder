package com.project.teamfinder.ui.conversation

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.LocalContentColor
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.project.teamfinder.R
import com.project.teamfinder.ui.team.TeamViewModel

@Composable
fun ConversationContent(
    uiState: ConversationUiState,
    teamViewModel: TeamViewModel,
    modifier: Modifier = Modifier
    ) {

    val authorMe = stringResource(R.string.author_me)
    val timeNow = stringResource(id = R.string.now)

    val scrollState = rememberLazyListState()

    Surface(modifier = modifier) {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(Modifier.fillMaxSize()) {
                Messages(
                    messages = uiState.messages,
                    modifier = Modifier.weight(1f),
                    scrollState = scrollState
                )
                UserInput(
                    onMessageSent = { content ->
                        uiState.addMessage(
                            Message(authorMe, content, timeNow)
                        )
                        teamViewModel.addMessage(Message(authorMe, content, timeNow))
                    }
                )
            }
        }
    }
}

@Composable
fun Messages(
    messages: List<Message>,
    scrollState: LazyListState,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {

        LazyColumn(
            reverseLayout = true,
            state = scrollState,
            modifier = Modifier

        ) {
            for (index in messages.indices) {
                val nextAuthor = messages.getOrNull(index + 1)?.author
                val content = messages[index]
                val isLastMessageByAuthor = nextAuthor != content.author

                item {
                    Message(
                        msg = content,
                        isLastMessageByAuthor = isLastMessageByAuthor
                    )
                }
            }
        }
    }
}

@Composable
fun Message(
    msg: Message,
    isLastMessageByAuthor: Boolean
) {
    val borderColor = MaterialTheme.colors.primary
    val spaceBetweenAuthors = if (isLastMessageByAuthor) Modifier.padding(top = 8.dp) else Modifier
    Row(modifier = spaceBetweenAuthors) {
        if (isLastMessageByAuthor) {
            // Avatar
            Image(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .size(42.dp)
                    .border(1.5.dp, borderColor, CircleShape)
                    .border(3.dp, MaterialTheme.colors.surface, CircleShape)
                    .clip(CircleShape)
                    .align(Alignment.Top),
                painter = painterResource(id = msg.authorImage),
                contentScale = ContentScale.Crop,
                contentDescription = null,
            )
        } else {
            // Space under avatar
            Spacer(modifier = Modifier.width(74.dp))
        }
        AuthorAndTextMessage(
            msg = msg,
            modifier = Modifier
                .padding(end = 16.dp)
                .weight(1f)
        )
    }
}

@Composable
fun AuthorAndTextMessage(
    msg: Message,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        ChatItemBubble(msg)
            Spacer(modifier = Modifier.height(8.dp))
    }
}

private val ChatBubbleShape = RoundedCornerShape(0.dp, 8.dp, 8.dp, 0.dp)

@Composable
fun ChatItemBubble(
    message: Message,
) {

    val backgroundBubbleColor = Color(0xFFF5F5F5)

    val bubbleShape = ChatBubbleShape
    Column {
        Surface(color = backgroundBubbleColor, shape = bubbleShape) {
            ClickableMessage(
                message = message
            )
        }
    }
}
@Composable
fun ClickableMessage(message: Message) {

    val styledMessage = messageFormatter(text = message.content)

    ClickableText(
        text = styledMessage,
        style = MaterialTheme.typography.body1.copy(color = LocalContentColor.current),
        modifier = Modifier.padding(8.dp),
        onClick = {

        }
    )
}


@Preview
@Composable
fun ConversationPreview() {
//    ConversationContent(uiState = exampleUiState, teamViewModel = )
}