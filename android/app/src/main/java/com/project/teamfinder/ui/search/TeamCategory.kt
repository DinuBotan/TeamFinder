package com.project.teamfinder.ui.search
import com.project.teamfinder.ui.search.TeamCategory.*

enum class TeamCategory(val value: String) {
    SPORT("Sport"),
    STUDY("Study"),
    GAMING("Gaming"),
    COOKING("Cooking"),
    PROGRAMMING("Programming"),
    READING("Reading")
}

fun getAllTeamCategories(): List<TeamCategory> {
    return listOf(SPORT, STUDY, GAMING, COOKING, PROGRAMMING, READING)
}


