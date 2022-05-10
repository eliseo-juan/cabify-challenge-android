package dev.eliseo.cabify.data.fake

interface FakeDataProvider {
    fun getFakeDescription(code: String) : String {
        return "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."
    }

    fun getFakeCurrencyCode(code: String) : String {
        return "EUR"
    }

    fun getFakeImageUrl(code: String) : String {
        return when(code) {
            "MUG" -> return "https://images.unsplash.com/photo-1605714262316-da2faa04731a"
            "TSHIRT" -> return "https://images.unsplash.com/photo-1633966887768-64f9a867bdba"
            "VOUCHER" -> return "https://images.unsplash.com/photo-1549465220-1a8b9238cd48"
            else -> "https://brandemia.org/sites/default/files/inline/images/cabify_logo_nuevo_2.png"
        }
    }
}