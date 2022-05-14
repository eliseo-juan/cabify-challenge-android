package dev.eliseo.cabify.domain.usecase

interface GetSuggestionBySegmentServiceLocator {
    operator fun invoke(): GetSuggestionUseCase
}