package dev.eliseo.cabify.domain.repository

import dev.eliseo.cabify.domain.model.Segment

interface UserRepository {
    fun getUserSegments(): List<Segment>
}