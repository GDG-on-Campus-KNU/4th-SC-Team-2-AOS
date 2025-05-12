package com.example.soop.emotionlogs.widget

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
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
import androidx.compose.ui.platform.LocalDensity // LocalDensity 임포트
import androidx.compose.foundation.gestures.Orientation // Orientation 임포트

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun EmotionLogsCarousel(
    items: List<String>,
    modifier: Modifier = Modifier,
    // pageSpacing 값을 조정하여 아이템 간 간격을 제어합니다. 8.dp에서 10.dp로 2.dp 증가
    pageSpacing: Dp = 8.dp // 아이템 간 간격 (조정됨)
) {
    // 화면 너비 가져오기
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val density = LocalDensity.current

    // 아이템 크기 스케일 정의 (3:4:6 비율 유지)
    // 이 scaleFactor는 최종적으로 graphicsLayer에서 적용될 최대 스케일을 결정합니다.
    val scaleFactor = 1.1f // 아이템 크기를 키울 배율 (유지)

    val minScale = 0.7f * scaleFactor
    val midScale = (1.2f * (4f / 6f)) * scaleFactor
    val maxScale = 1.1f * scaleFactor

    // HorizontalPager 레이아웃의 pageSize 계산
    // 아이템의 레이아웃 상의 기본 크기를 계산합니다.
    // 화면 너비에서 아이템 사이의 총 간격을 뺀 값을, 화면에 보일 아이템 개수(대략 3개 정도)를 고려하여 나눕니다.
    // 이 값은 graphicsLayer에서 scale이 적용되기 전의 기본 크기이며, pageSpacing의 영향을 받습니다.
    // estimatedVisibleItems는 화면에 대략적으로 몇 개의 아이템이 보일지를 나타내는 값으로,
    // 이 값을 조정하여 pageSpacing의 영향을 받으면서도 아이템이 적절한 개수로 보이도록 합니다.
    // 3.5f는 중앙 아이템이 크게, 양 옆 아이템이 절반 이상 보이는 형태를 고려한 값입니다.
    val estimatedVisibleItems = 3.5f // 화면에 대략적으로 보일 아이템 개수 (이 값을 조정하여 보이는 아이템 수와 간격 미세 조정)
    val baseItemLayoutSize = (screenWidth - (pageSpacing * (estimatedVisibleItems - 1))) / estimatedVisibleItems

    // HorizontalPager의 pageSize를 아이템의 기본 레이아웃 크기로 설정합니다.
    // 이 pageSize는 pageSpacing과 함께 아이템들이 배치될 공간을 정의합니다.
    val pageSize = baseItemLayoutSize

    // 중앙 아이템의 최종 보이는 크기 (graphicsLayer의 scale이 적용된 후의 크기)
    // 이 값은 아이템 자체의 크기 계산에 사용됩니다.
    val centralItemFinalVisibleSize = pageSize * maxScale

    // 양쪽 끝 아이템이 중앙에 오도록 Content Padding 계산
    // contentPadding은 (화면 너비 - pageSize) / 2 로 계산하여 중앙 아이템이 Pager의 중앙에 오도록 합니다.
    val contentPadding = (screenWidth - pageSize) / 2

    // 아이템의 y축 위치 변화량 정의 (유지)
    val yTranslationStep1 = 50.dp
    val yTranslationStep2 = 100.dp // 필요에 따라 이 값 조절

    // 캐러셀 콘텐츠의 예상 최소 높이 계산 (아이템 크기 변화에 따라 조정)
    // 중앙 아이템의 최종 높이와 가장자리 아이템의 최대 y 이동 거리를 더하여 계산합니다.
    val minCarouselHeight = centralItemFinalVisibleSize + yTranslationStep2

    // PagerState 초기화 (유지)
    val pagerState = rememberPagerState(
        initialPage = 2, // 중앙에 위치시킬 초기 페이지 (0부터 시작)
        pageCount = { items.size }
    )

    // Pager 기본 flingBehavior 사용 (유지)
    val flingBehavior = PagerDefaults.flingBehavior(state = pagerState)

    // HorizontalPager 컴포저블
    HorizontalPager(
        state = pagerState,
        // pageSize를 아이템의 기본 레이아웃 크기로 설정합니다.
        pageSize = PageSize.Fixed(pageSize),
        contentPadding = PaddingValues(horizontal = contentPadding), // 양쪽 끝 패딩을 계산된 값으로 설정
        pageSpacing = pageSpacing, // 페이지 간 간격 (조정된 값 사용)
        flingBehavior = flingBehavior, // 스크롤 애니메이션 동작
        pageNestedScrollConnection = PagerDefaults.pageNestedScrollConnection(
            Orientation.Horizontal
        ),
        beyondBoundsPageCount = 2, // 화면에 보이는 아이템 개수를 고려하여 beyondBoundsPageCount를 늘릴 수 있습니다.
        modifier = modifier
            .wrapContentHeight(align = Alignment.CenterVertically)
            .heightIn(min = minCarouselHeight) // 예상 최소 높이를 minHeight로 설정 (유지)
    ) { page -> // HorizontalPager의 트레일링 람다에서 각 페이지의 콘텐츠를 정의
        // 현재 페이지와 Pager 중앙 위치 간의 상대적인 오프셋 계산 (유지)
        val pageOffset = ((pagerState.currentPage - page) + pagerState.currentPageOffsetFraction).absoluteValue

        // pageOffset에 따라 아이템 크기 스케일 계산 (조정된 scaleFactor 적용) (유지)
        val scale = when {
            pageOffset >= 2f -> minScale // 중앙에서 2칸 이상: minScale
            pageOffset >= 1f -> linearInterpolation(midScale, minScale, pageOffset - 1f) // 1~2칸: midScale에서 minScale로 보간
            else -> linearInterpolation(maxScale, midScale, pageOffset) // 0~1칸: maxScale에서 midScale로 보간
        }

        // pageOffset에 따라 아이템의 y축 위치 변화 계산 (^ 모양) (유지)
        val yTranslation = when {
            pageOffset <= 1f -> linearInterpolation(0f, yTranslationStep1.toPx(), pageOffset)
            pageOffset <= 2f -> linearInterpolation(yTranslationStep1.toPx(), yTranslationStep2.toPx(), pageOffset - 1f)
            else -> yTranslationStep2.toPx()
        }

        // 각 아이템 컴포저블 (원형 배경과 텍스트)
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                // Box의 기본 너비를 pageSize와 동일하게 설정합니다.
                // graphicsLayer에서 scale을 적용하여 최종 보이는 크기를 조절합니다.
                .width(pageSize)
                .aspectRatio(1f) // 너비와 높이 비율을 1:1로 설정하여 원형 모양 유지 (유지)
                .graphicsLayer {
                    // pageOffset에 따라 계산된 scale 및 yTranslation 적용
                    this.scaleX = scale
                    this.scaleY = scale
                    this.translationY = yTranslation // y축 위치 변화 적용 (아래로 이동)
                    this.transformOrigin = androidx.compose.ui.graphics.TransformOrigin(0.5f, 0.5f)
                }
                .background(Color.LightGray, CircleShape)
        ) {
            Text(text = items[page])
        }
    }
}

// 선형 보간 함수 (Lerp) (유지)
private fun linearInterpolation(start: Float, stop: Float, fraction: Float): Float {
    val clampedFraction = fraction.coerceIn(0f, 1f)
    return start + (stop - start) * clampedFraction
}

// dp 값을 픽셀 값으로 변환하기 위한 확장 함수 (유지)
@Composable
private fun Dp.toPx(): Float {
    val density = LocalDensity.current
    return with(density) { this@toPx.toPx() }
}