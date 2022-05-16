package com.project.teamfinder

import com.project.model.TeamRepository
import com.project.model.api.TeamsWebService
import com.project.model.response.MessageResponse
import com.project.model.response.MessagesResponse
import com.project.model.response.TeamResponse
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Test

class TeamRepositoryUnitTests {
    private val webService = mockk<TeamsWebService>()
    private val repository: TeamRepository = TeamRepository(webService)
    private val team = TeamResponse(
        "",
        "",
        10,
        1,
        ArrayList(),
        "",
        "",
        "",
        "",
        "",
        ""
    )
    private val messageList = ArrayList<MessageResponse>()
    private val message = MessageResponse(
        "999",
        "",
        "",
        ""
    )
    private val messages = MessagesResponse(messageList)

    @Test
    fun `Assert repository return team by id`() {
        // coEvery is used to mock a coroutine when running the test
        coEvery { webService.getTeamById(any()) } returns team

        runBlockingTest {
            val team = repository.getTeamById("")
            assertEquals(team.size, 10)
        }

    }

    @Test
    fun `Assert repository return message by id`() {
        coEvery {
            messageList.add(message)
            webService.getMessagesById(any())
        } returns messages

        runBlockingTest {
            val messages = repository.getMessagesById("")
            assertEquals(messages.messages.get(0).authorId, "999")
        }
    }

}