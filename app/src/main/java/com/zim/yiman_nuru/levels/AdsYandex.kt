package com.zim.yiman_nuru.levels

import com.yandex.mobile.ads.banner.AdSize
import com.yandex.mobile.ads.banner.BannerAdView
import com.yandex.mobile.ads.common.AdRequest

class AdsYandex {
    private val _adsUnitId = "R-M-DEMO-320x100"
    fun ads(banner: BannerAdView) { //        Рекламный блок
        banner.setAdUnitId(_adsUnitId)
        banner.setAdSize(AdSize.BANNER_320x100)
        val adRequest = AdRequest.Builder().build()
        banner.loadAd(adRequest)
    }
    }