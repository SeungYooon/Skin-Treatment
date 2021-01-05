package com.example.toyproject.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.os.bundleOf
import androidx.core.view.ViewCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.example.toyproject.R
import com.example.toyproject.base.BaseFragment
import com.example.toyproject.data.entities.SkinInfo
import com.example.toyproject.data.entities.SkinType
import com.example.toyproject.databinding.FragmentDetailBinding
import com.example.toyproject.extensions.GlideApp
import com.example.toyproject.extensions.setAdapter
import com.example.toyproject.ui.detail.DetailActivity.Companion.KEY
import com.example.toyproject.utils.GridSpaceItemDecoration
import com.example.toyproject.viewmodel.ChannelViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DetailFragment :
    BaseFragment<FragmentDetailBinding, ChannelViewModel>(),
    DetailAdapter.OnClickListener {

    @Inject
    lateinit var adapter: DetailAdapter

    override val viewModel: ChannelViewModel by viewModels()

    private val type by lazy { requireArguments().getSerializable(KEY) as SkinType? }

    private val spaceItemDecoration by lazy { (resources.displayMetrics.density * 10).toInt() }

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentDetailBinding =
        FragmentDetailBinding::inflate

    override fun setUp() {
        binding.apply {
            type?.let { txtSkinName.setText(it.typeName) }
            context?.let {
                GlideApp.with(it).load(type?.skinImg)
                    .placeholder(R.mipmap.ic_launcher)
                    .transform(CircleCrop())
                    .error(R.drawable.ch_network_error_illust)
                    .into(ivMainSkin)

                setAdapter(rvDetail, adapter)
                rvDetail.addItemDecoration(GridSpaceItemDecoration(spaceItemDecoration))

                ViewCompat.setTransitionName(ivMainSkin, ivMainSkin.transitionName)
            }
        }

        adapter.submitList(
            when (type?.typeName) {
                R.string.toxin -> mutableListOf(
                    SkinInfo(
                        R.string.t1,
                        "dd",
                        "https://intranet.toxnfill.com/uploadFiles/C00001/eventImg/20201027140955_4_6_8.jpg",
                        "Dd"
                    ),
                    SkinInfo(
                        R.string.t2,
                        "dd",
                        "https://intranet.toxnfill.com/uploadFiles/C00001/eventImg/20201027140955_4_1_8.jpg",
                        "Dd"
                    ),
                    SkinInfo(
                        R.string.t3,
                        "dd",
                        "https://intranet.toxnfill.com/uploadFiles/C00001/eventImg/20201027140955_4_2_8.jpg",
                        "Dd"
                    ),
                    SkinInfo(
                        R.string.t4,
                        "dd",
                        "https://intranet.toxnfill.com/uploadFiles/C00001/eventImg/20201027140955_4_3_8.jpg",
                        "Dd"
                    )
                )
                R.string.filler -> mutableListOf(
                    SkinInfo(
                        R.string.f1,
                        "dd",
                        "https://intranet.toxnfill.com/uploadFiles/C00001/eventImg/20201026152554_4_3_8.jpg",
                        "Dd"
                    ),
                    SkinInfo(
                        R.string.f2,
                        "dd",
                        "https://intranet.toxnfill.com/uploadFiles/C00001/eventImg/20190509181931_1.jpg",
                        "Dd"
                    ),
                    SkinInfo(
                        R.string.f3,
                        "dd",
                        "https://intranet.toxnfill.com/uploadFiles/C00001/eventImg/20201026152554_4_4_8.jpg",
                        "Dd"
                    ),
                    SkinInfo(
                        R.string.f4,
                        "dd",
                        "https://intranet.toxnfill.com/uploadFiles/C00001/eventImg/20200808151143_1.jpg",
                        "Dd"
                    )
                )
                R.string.injection -> mutableListOf(
                    SkinInfo(
                        R.string.i1,
                        "dd",
                        "https://intranet.toxnfill.com/uploadFiles/C00001/eventImg/20201026091209_4_2_8.jpg",
                        "Dd"
                    ),
                    SkinInfo(
                        R.string.i2,
                        "dd",
                        "https://intranet.toxnfill.com/uploadFiles/C00001/eventImg/20201026091209_4_3_8.jpg",
                        "Dd"
                    ),
                    SkinInfo(
                        R.string.i3,
                        "dd",
                        "http://intranet.toxnfill.com/uploadFiles/C00001/eventImg/20190719151302_1.jpg",
                        "Dd"
                    ),
                    SkinInfo(
                        R.string.i4,
                        "dd",
                        "https://intranet.toxnfill.com/uploadFiles/C00001/eventImg/20200617161829_1.png",
                        "Dd"
                    )
                )
                R.string.lifting -> mutableListOf(
                    SkinInfo(
                        R.string.l1,
                        "dd",
                        "https://intranet.toxnfill.com/uploadFiles/T00002/eventImg/20201026152643_4_1_8.jpg",
                        "Dd"
                    ),
                    SkinInfo(
                        R.string.l2,
                        "dd",
                        "https://intranet.toxnfill.com/uploadFiles/C00001/eventImg/20201026152643_4_2_8.jpg",
                        "Dd"
                    ),
                    SkinInfo(
                        R.string.l3,
                        "dd",
                        "https://intranet.toxnfill.com/uploadFiles/C00001/eventImg/20201202155118_1.jpg",
                        "Dd"
                    ),
                    SkinInfo(
                        R.string.l4,
                        "dd",
                        "https://intranet.toxnfill.com/uploadFiles/C00001/eventImg/20201026152643_4_3_8.jpg",
                        "Dd"
                    ), SkinInfo(
                        R.string.l5,
                        "dd",
                        "https://intranet.toxnfill.com/uploadFiles/C00001/eventImg/20201026152643_4_5_8.jpg",
                        "Dd"
                    ),
                    SkinInfo(
                        R.string.l6,
                        "dd",
                        "https://intranet.toxnfill.com/uploadFiles/C00001/eventImg/20201026152643_4_6_8.jpg",
                        "Dd"
                    )
                )
                R.string.acne -> mutableListOf(
                    SkinInfo(
                        R.string.a1,
                        "dd",
                        "https://intranet.toxnfill.com/uploadFiles/C00001/eventImg/20201026152731_4_4_8.jpg",
                        "Dd"
                    ),
                    SkinInfo(
                        R.string.a2,
                        "dd",
                        "https://intranet.toxnfill.com/uploadFiles/C00001/eventImg/20200121175711_1.jpg",
                        "Dd"
                    ),
                    SkinInfo(
                        R.string.a3,
                        "dd",
                        "https://intranet.toxnfill.com/uploadFiles/C00001/eventImg/20190509171430_1.jpg",
                        "Dd"
                    ),
                    SkinInfo(
                        R.string.a4,
                        "dd",
                        "https://intranet.toxnfill.com/uploadFiles/C00001/eventImg/20201026152731_4_8_8.jpg",
                        "Dd"
                    ), SkinInfo(
                        R.string.a5,
                        "dd",
                        "https://intranet.toxnfill.com/uploadFiles/C00001/eventImg/20201026152731_4_9_8.jpg",
                        "Dd"
                    ),
                    SkinInfo(
                        R.string.a6,
                        "dd",
                        "https://d10fvx8ndeqwvu.cloudfront.net/upfiles/product/232390366.jpg",
                        "Dd"
                    ),
                    SkinInfo(
                        R.string.a7,
                        "dd",
                        "https://intranet.toxnfill.com/uploadFiles/T00046/eventImg/20190918113656_1.jpg",
                        "Dd"
                    )
                )
                else -> mutableListOf(
                    SkinInfo(
                        R.string.a1,
                        "dd",
                        "https://intranet.toxnfill.com/uploadFiles/C00001/eventImg/20201026152731_4_4_8.jpg",
                        "Dd"
                    ),
                    SkinInfo(
                        R.string.a2,
                        "dd",
                        "https://intranet.toxnfill.com/uploadFiles/C00001/eventImg/20200121175711_1.jpg",
                        "Dd"
                    ),
                    SkinInfo(
                        R.string.a3,
                        "dd",
                        "https://intranet.toxnfill.com/uploadFiles/C00001/eventImg/20190509171430_1.jpg",
                        "Dd"
                    ),
                    SkinInfo(
                        R.string.a4,
                        "dd",
                        "https://intranet.toxnfill.com/uploadFiles/C00001/eventImg/20201026152731_4_8_8.jpg",
                        "Dd"
                    ), SkinInfo(
                        R.string.a5,
                        "dd",
                        "https://intranet.toxnfill.com/uploadFiles/C00001/eventImg/20201026152731_4_9_8.jpg",
                        "Dd"
                    ),
                    SkinInfo(
                        R.string.a6,
                        "dd",
                        "https://d10fvx8ndeqwvu.cloudfront.net/upfiles/product/232390366.jpg",
                        "Dd"
                    ),
                    SkinInfo(
                        R.string.a7,
                        "dd",
                        "https://intranet.toxnfill.com/uploadFiles/T00046/eventImg/20190918113656_1.jpg",
                        "Dd"
                    )
                )
            }
        )
    }
    /**
     * Using
     * 1. MaterialDialog
     * 2. Navigation
     */
    override fun onClick(imageView: ImageView, skinInfo: SkinInfo) {
        /*MaterialDialog(requireContext()).show {
            customView(
                view = FragmentCustomDialogBinding.inflate(
                    LayoutInflater.from(context),
                    null,
                    false
                ).also {
                    GlideApp.with(context)
                        .load("https://intranet.toxnfill.com/uploadFiles/C00001/eventImg/20201027140955_4_6_8.jpg")
                        .into(it.ivDialog)
                    it.ivDialog.clipToOutline = true
                }.root
            )
            cornerRadius(context.resources.getDimension(R.dimen.dialog_dimen))
        }*/

        val bundle = bundleOf(KEY to type)
        findNavController().navigate(R.id.action_global_detailCustomFragment, bundle)
    }
}
