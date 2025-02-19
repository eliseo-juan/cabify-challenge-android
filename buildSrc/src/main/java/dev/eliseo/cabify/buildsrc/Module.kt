package dev.eliseo.cabify.buildsrc

object Module {
    const val libBase = ":libBase"

    object Feature {
        const val store = ":featureStore"
        const val checkout = ":featureCheckout"
        const val productDetail = ":featureProductDetail"
    }

    object Core {
        const val ds = ":coreDS"
        const val navigation = ":coreNavigation"
        const val domain = ":domain"
        const val data = ":data"
        const val presentation = ":corePresentation"
    }
}