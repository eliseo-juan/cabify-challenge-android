package dev.eliseo.cabify.domain.usecase

import dev.eliseo.cabify.domain.repository.UserRepository

class GetSuggestionBySegmentServiceLocatorImpl(
    val userRepository: UserRepository
) : GetSuggestionBySegmentServiceLocator {
    override operator fun invoke(): GetSuggestionUseCase {
        return userRepository.getUserSegments().let {
            when {
                it.contains("SUG_A") -> GetSuggestionByMinValueUseCaseImpl()
                it.contains("SUG_B") -> GetSuggestionByMinObjectsUseCaseImpl()
                else -> GetSuggestionByMinValueUseCaseImpl()
            }
        }
    }
}