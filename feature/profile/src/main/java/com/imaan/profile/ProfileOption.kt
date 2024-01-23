package com.imaan.profile

enum class ProfileOption(
    val iconResId: Int,
    val label: String
){
    EditProfile(
        iconResId = com.imaan.resources.R.drawable.ic_person,
        label = "Edit Profile"
    ),
    ShippingAddresses(
        iconResId = com.imaan.resources.R.drawable.ic_location,
        label = "Shipping Addresses"
    ),
    Wishlist(
        iconResId = com.imaan.resources.R.drawable.ic_favorite,
        label = "Wishlist"
    ),
    OrderHistory(
        iconResId = com.imaan.resources.R.drawable.ic_about,
        label = "Order History"
    ),
    DeleteAccount(
        iconResId = com.imaan.resources.R.drawable.ic_invite,
        label = "Delete Account"
    ),
    SignOut(
        iconResId = com.imaan.resources.R.drawable.ic_logout,
        label = "Sign Out"
    )
}
