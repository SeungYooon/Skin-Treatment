package com.example.toyproject.data

import androidx.annotation.StringRes
import com.example.toyproject.R

enum class SkinType(
    @StringRes val skinDescription: Int,
    val skinImg: String,
    @StringRes val typeName: Int
) {
    TOXIN(
        R.string.toxin_title,
        "http://intranet.toxnfill.com/uploadFiles/C00001/eventImg/20200521162815_6.gif",
        R.string.toxin
    ),
    FILLER(
        R.string.filler_title,
        "http://intranet.toxnfill.com/uploadFiles/C00001/eventImg/20200521163223_6.gif",
        R.string.filler
    ),
    INJECTION(
        R.string.injection_title,
        "http://intranet.toxnfill.com/uploadFiles/C00001/eventImg/20200521162912_6.gif",
        R.string.injection
    ),
    LIFTING(
        R.string.lifting_title,
        "http://intranet.toxnfill.com/uploadFiles/C00001/eventImg/20200521162838_6.gif",
        R.string.lifting
    ),
    ACNE(
        R.string.acne_title,
        "http://intranet.toxnfill.com/uploadFiles/C00001/eventImg/20200522152846_6.gif",
        R.string.acne
    ),
    PIGMENT(
        R.string.pigment_title,
        "http://intranet.toxnfill.com/uploadFiles/C00001/eventImg/20200522152939_6.gif",
        R.string.pigment
    ),
    SKIN_BOOSTER(
        R.string.skin_booster_title,
        "http://intranet.toxnfill.com/uploadFiles/C00001/eventImg/20200522152627_6.gif",
        R.string.skin_booster
    ),
    SKIN_CARE(
        R.string.skin_care_title,
        "http://intranet.toxnfill.com/uploadFiles/C00001/eventImg/20200522152812_6.gif",
        R.string.skin_care
    ),
    WAXING(
        R.string.waxing_title,
        "http://intranet.toxnfill.com/uploadFiles/C00001/eventImg/20200522152736_6.gif",
        R.string.waxing
    ),
    BODY(
        R.string.body_title,
        "http://intranet.toxnfill.com/uploadFiles/C00001/eventImg/20200522152543_6.gif",
        R.string.body
    ),
    ANTI_AGING(
        R.string.acne_title,
        "http://intranet.toxnfill.com/uploadFiles/C00001/eventImg/20200522152801_6.gif",
        R.string.anti_aging
    )
}