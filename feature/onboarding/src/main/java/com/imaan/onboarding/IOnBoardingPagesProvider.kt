package com.imaan.onboarding

interface IOnBoardingPagesProvider {
    val pages: List<OnBoardingPage>
}

class OnBoardingPagesProviderImpl: IOnBoardingPagesProvider{
    override val pages: List<OnBoardingPage>
        get() = listOf(
            OnBoardingPage(
                imageResId = R.drawable.shopping_bags,
                titleResId = R.string.easy_shopping,
                descriptionResId = R.string.easy_shopping_desc
            ),
            OnBoardingPage(
                imageResId = R.drawable.mobile_pay,
                titleResId = R.string.secure_payments,
                descriptionResId = R.string.secure_payments_desc
            ),
            OnBoardingPage(
                imageResId = R.drawable.delivery_truck,
                titleResId = R.string.quick_delivery,
                descriptionResId = R.string.quick_delivery_desc
            ),
        )
}