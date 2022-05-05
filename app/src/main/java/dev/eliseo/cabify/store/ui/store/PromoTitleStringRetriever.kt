package dev.eliseo.cabify.store.ui.store

import android.content.Context
import dev.eliseo.cabify.domain.model.Discount
import dev.eliseo.cabify.store.R

interface DiscountTitleStringRetriever {

    fun Discount.getTitle(context: Context): String {
        return when(this) {
            is Discount.DiscountByAmount -> context.getString(R.string.discount_title_discount_by_amount, this.minNumberOfProducts, (this.discountPercentage*100).toInt())
            is Discount.TakeXGetY -> context.getString(R.string.discount_title_take_x_get_y, this.numberOfProduts + this.numberOfGifts, this.numberOfProduts)
        }
    }
}