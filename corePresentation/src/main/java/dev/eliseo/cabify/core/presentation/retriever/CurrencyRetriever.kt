package dev.eliseo.cabify.core.presentation.retriever

import java.text.NumberFormat
import java.util.*

interface CurrencyRetriever {

    fun Float.getWhitCurrencyFormat(currencyCode: String = "EUR"): String =
        this.toDouble().getWhitCurrencyFormat(currencyCode)

    fun Double.getWhitCurrencyFormat(currencyCode: String = "EUR"): String =
        NumberFormat.getCurrencyInstance().also {
            it.currency = Currency.getInstance(currencyCode)
        }.format(this)
}