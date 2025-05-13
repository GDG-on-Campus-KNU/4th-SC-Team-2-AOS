package com.example.soop.emotionlogs.widget

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerDefaults
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlin.math.absoluteValue
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.soop.R
import com.example.soop.database.Emotion
import com.example.soop.emotionlogs.viewmodel.EmotionViewModel
import com.example.soop.itemlist.GradationCircleImage
import com.example.soop.widget.DashedBorderModifier

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun EmotionLogsCarousel(
    items: List<Emotion>,
    modifier: Modifier = Modifier,
    pageSpacing: Dp = 8.dp,
    onLastItemClick: (String) -> Unit,
    viewModel: EmotionViewModel,
    emotionTextField: EmotionTextField
) {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val scaleFactor = 1.1f

    val minScale = 0.7f * scaleFactor
    val midScale = (1.2f * (4f / 6f)) * scaleFactor
    val maxScale = 1.1f * scaleFactor

    val estimatedVisibleItems = 3.5f
    val baseItemLayoutSize = (screenWidth - (pageSpacing * (estimatedVisibleItems - 1))) / estimatedVisibleItems

    val pageSize = baseItemLayoutSize
    val centralItemFinalVisibleSize = pageSize * maxScale

    val contentPadding = (screenWidth - pageSize) / 2
    val yTranslationStep1 = 10.dp
    val yTranslationStep2 = 60.dp

    val minCarouselHeight = centralItemFinalVisibleSize + yTranslationStep2

    val pagerState = rememberPagerState(
        initialPage = 2,
        pageCount = { items.size }
    )

    val flingBehavior = PagerDefaults.flingBehavior(state = pagerState)

    HorizontalPager(
        state = pagerState,
        pageSize = PageSize.Fixed(pageSize),
        contentPadding = PaddingValues(horizontal = contentPadding),
        pageSpacing = pageSpacing,
        flingBehavior = flingBehavior,
        pageNestedScrollConnection = PagerDefaults.pageNestedScrollConnection(
            Orientation.Horizontal
        ),
        beyondBoundsPageCount = 2,
        modifier = modifier
            .wrapContentHeight(align = Alignment.CenterVertically)
            .heightIn(min = minCarouselHeight)
    ) { page ->
        val pageOffset = ((pagerState.currentPage - page) + pagerState.currentPageOffsetFraction).absoluteValue

        val scale = when {
            pageOffset >= 2f -> minScale
            pageOffset >= 1f -> linearInterpolation(midScale, minScale, pageOffset - 1f)
            else -> linearInterpolation(maxScale, midScale, pageOffset)
        }

        val yTranslation = when {
            pageOffset <= 1f -> linearInterpolation(0f, yTranslationStep1.toPx(), pageOffset)
            pageOffset <= 2f -> linearInterpolation(yTranslationStep1.toPx(), yTranslationStep2.toPx(), pageOffset - 1f)
            else -> yTranslationStep2.toPx()
        }

        val isLastItem = page == items.size - 1
        val isSelectedItem = page == pagerState.currentPage
        var textState by rememberSaveable { mutableStateOf("") }

        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.SpaceBetween) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .width(pageSize)
                    .aspectRatio(1f)
                    .graphicsLayer {
                        this.scaleX = scale
                        this.scaleY = scale
                        this.translationY = yTranslation
                        this.transformOrigin = androidx.compose.ui.graphics.TransformOrigin(0.5f, 0.5f)
                    }
                    .background(Color.Transparent, CircleShape)
                    .then(if (isLastItem) Modifier.clickable {
                        onLastItemClick(textState) // ✅ 클릭 시 콜백 실행
                        textState = ""
                    } else Modifier)

            ) {
                if(!isLastItem){
                    GradationCircleImage(items[page].imageIdx, modifier = Modifier.fillMaxSize())
                }
                else Box(
                    modifier = Modifier.fillMaxSize().then(DashedBorderModifier(color = Color(0xFF1CE0A9)))
                ){
                    Image(painter = painterResource(R.drawable.add_green), contentDescription = "add emotion", Modifier.size(32.dp).align(Alignment.Center))
                }
            }
            if (isSelectedItem) {
                if (!isLastItem) {
                    Spacer(Modifier.padding(20.dp))
                    Box(
                        modifier = Modifier
                            .padding(top = 8.dp)
                            .background(
                                color = Color.Black,
                                shape = RoundedCornerShape(24.dp)
                            )
                            .wrapContentSize()
                            .padding(horizontal = 8.dp, vertical = 7.dp)
                            .heightIn(min = 0.dp)
                            .defaultMinSize(minHeight = 0.dp),
                    ) {
                        Text(
                            text = items[page].name,
                            fontSize = 14.sp,
                            color = Color.White,
                            textAlign = TextAlign.Center
                        )
                    }
                } else {
                    Spacer(Modifier.padding(5.dp))
                    emotionTextField.AddEmotionTextField(
                        value = textState,
                        onValueChange = { newValue ->
                            textState = newValue
                        },
                        modifier = Modifier
                            .padding(top = 8.dp)
                            .width(pageSize),
                        viewModel = viewModel
                    )
                    Spacer(Modifier.padding(3.dp))
                    emotionTextField.EmotionGroupSelector(viewModel = viewModel)
                }
            }
        }
    }
}

private fun linearInterpolation(start: Float, stop: Float, fraction: Float): Float {
    val clampedFraction = fraction.coerceIn(0f, 1f)
    return start + (stop - start) * clampedFraction
}

@Composable
private fun Dp.toPx(): Float {
    val density = LocalDensity.current
    return with(density) { this@toPx.toPx() }
}