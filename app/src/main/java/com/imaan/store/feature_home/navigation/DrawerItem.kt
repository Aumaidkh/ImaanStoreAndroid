package com.imaan.store.feature_home.navigation

import com.imaan.store.R

data class DrawerItem(
    val iconResId: Int,
    val label: String
)

val navDrawerItems = listOf(
    DrawerItem(
        iconResId = R.drawable.home,
        label = "Home"
    ),
    DrawerItem(
        iconResId = R.drawable.ic_favorite,
        label = "Wishlist"
    ),
    DrawerItem(
        iconResId = R.drawable.ic_location,
        label = "Track Order"
    ),
    DrawerItem(
        iconResId = R.drawable.ic_invite,
        label = "Invite a friend"
    ),
    DrawerItem(
        iconResId = R.drawable.ic_privacy,
        label = "Privacy Policy"
    ),
    DrawerItem(
        iconResId = R.drawable.ic_about,
        label = "About Us"
    ),
    DrawerItem(
        iconResId = R.drawable.ic_contact,
        label = "Help & Support"
    ),
)
