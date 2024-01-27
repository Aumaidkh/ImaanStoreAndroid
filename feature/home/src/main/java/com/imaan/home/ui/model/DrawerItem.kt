package com.imaan.home.ui.model


enum class DrawerItem(
    val iconResId: Int,
    val label: String
){
    Home(
        iconResId = com.imaan.resources.R.drawable.home,
        label = "Home"
    ),
    Wishlist(
        iconResId = com.imaan.resources.R.drawable.ic_favorite,
        label = "Wishlist"
    ),
    TrackOrder(
        iconResId = com.imaan.resources.R.drawable.ic_location,
        label = "Track Order"
    ),
    InviteFriend(
        iconResId = com.imaan.resources.R.drawable.ic_invite,
        label = "Invite a friend"
    ),
    PrivacyPolicy(
        iconResId = com.imaan.resources.R.drawable.ic_privacy,
        label = "Privacy Policy"
    ),
    AboutUs(
        iconResId = com.imaan.resources.R.drawable.ic_about,
        label = "About Us"
    ),
    HelpAndSupport(
        iconResId = com.imaan.resources.R.drawable.ic_contact,
        label = "Help & Support"
    ),
}
