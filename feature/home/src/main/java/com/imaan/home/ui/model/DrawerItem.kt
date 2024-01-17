package com.imaan.home.ui.model


data class DrawerItem(
    val iconResId: Int,
    val label: String
)

val navDrawerItems = listOf(
    DrawerItem(
        iconResId = com.imaan.resources.R.drawable.home,
        label = "Home"
    ),
    DrawerItem(
        iconResId = com.imaan.resources.R.drawable.ic_favorite,
        label = "Wishlist"
    ),
    DrawerItem(
        iconResId = com.imaan.resources.R.drawable.ic_location,
        label = "Track Order"
    ),
    DrawerItem(
        iconResId = com.imaan.resources.R.drawable.ic_invite,
        label = "Invite a friend"
    ),
    DrawerItem(
        iconResId = com.imaan.resources.R.drawable.ic_privacy,
        label = "Privacy Policy"
    ),
    DrawerItem(
        iconResId = com.imaan.resources.R.drawable.ic_about,
        label = "About Us"
    ),
    DrawerItem(
        iconResId = com.imaan.resources.R.drawable.ic_contact,
        label = "Help & Support"
    ),
)