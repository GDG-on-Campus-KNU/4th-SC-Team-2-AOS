package com.example.soop.home.data

import com.example.soop.home.response.ExpertResponseWrapper
import com.example.soop.home.response.MentalTipResponseWrapper

data class HomeData (
    var nickName: String = "",
    var mentaltipList: MentalTipResponseWrapper = MentalTipResponseWrapper(tips = emptyList()),
)