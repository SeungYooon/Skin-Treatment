package com.example.toyproject.domain.model

import android.os.Parcelable
import androidx.annotation.StringRes
import com.example.toyproject.R
import kotlinx.android.parcel.Parcelize

@Parcelize
enum class SkinType(
    @StringRes val skinDescription: Int,
    val skinImg: String,
    @StringRes val typeName: Int
) : Parcelable {
    TOXIN(
        R.string.toxin_title,
        "http://intranet.toxnfill.com/uploadFiles/C00001/eventImg/20200521162815_6.gif",
        R.string.toxin,
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
    WAXING(
        R.string.waxing_title,
        "http://intranet.toxnfill.com/uploadFiles/C00001/eventImg/20200522152736_6.gif",
        R.string.waxing
    )
}