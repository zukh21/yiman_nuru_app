package com.zim.yiman_nuru.levels

import com.yandex.mobile.ads.banner.AdSize
import com.yandex.mobile.ads.banner.BannerAdView
import com.yandex.mobile.ads.common.AdRequest
import com.zim.yiman_nuru.R

class AdsYandex {
    fun ads(banner: BannerAdView) { //        Рекламный блок
        banner.setAdUnitId("R-M-DEMO-320x100")
        banner.setAdSize(AdSize.BANNER_320x100)
        val adRequest = AdRequest.Builder().build()
        banner.loadAd(adRequest)
    }
    }