package dev.eliseo.cabify.data.repository

import dev.eliseo.cabify.domain.model.Segment
import dev.eliseo.cabify.domain.repository.UserRepository

class UserRepositoryImpl : UserRepository {
    override fun getUserSegments(): List<Segment> {
        return listOf(
            "SUG_A",
        )
    }
}