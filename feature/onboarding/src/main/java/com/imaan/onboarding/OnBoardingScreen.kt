package com.imaan.onboarding

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.imaan.design_system.components.views.ImaanAppPageIndicator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoardingScreen(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues,
    onBoardingPagesProvider: IOnBoardingPagesProvider,
    onNextClick: () -> Unit,
    scope: CoroutineScope
) {
    val pagerState = rememberPagerState {
       onBoardingPagesProvider.pages.size
    }
    Column(
        modifier = modifier
            .padding(
                horizontal = 32.dp
            )
            .padding(
                top = paddingValues.calculateTopPadding(),
                bottom = paddingValues.calculateBottomPadding()
            )
    ) {
        HorizontalPager(
            modifier = Modifier
                .fillMaxHeight(0.75f),
            state = pagerState
        ) {
            OnBoardingPage(
                onBoardingPage = onBoardingPagesProvider.pages[it]
            )
        }
        Row(
            modifier = Modifier
                .padding(
                    bottom = paddingValues.calculateBottomPadding() + 48.dp
                )
                .fillMaxHeight(1f)
                .fillMaxWidth(),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            ImaanAppPageIndicator(
                maxPageCount = onBoardingPagesProvider.pages.size,
                currentPageNumber = pagerState.currentPage
            )
            TextButton(onClick = {
                if (pagerState.currentPage < onBoardingPagesProvider.pages.size - 1){
                    scope.launch {
                        pagerState.animateScrollToPage(
                            page = onBoardingPagesProvider.pages.size - 1
                        )
                    }
                } else {
                   onNextClick()
                }
            }) {
                Text(
                    text = if (pagerState.currentPage == onBoardingPagesProvider.pages.size-1) "Start" else "Skip",
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
    }
}

@Composable
fun OnBoardingPage(
    onBoardingPage: OnBoardingPage
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        Image(
            painter = painterResource(id = onBoardingPage.imageResId),
            contentDescription = ""
        )
        Spacer(modifier = Modifier.height(72.dp))
        Text(
            text = stringResource(id = onBoardingPage.titleResId),
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = stringResource(id = onBoardingPage.descriptionResId),
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center
        )
    }
}