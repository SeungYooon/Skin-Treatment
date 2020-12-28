package com.example.toyproject.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.toyproject.R
import com.example.toyproject.base.BaseFragment
import com.example.toyproject.data.SkinInfo
import com.example.toyproject.databinding.FragmentDetailBinding
import com.example.toyproject.extensions.GlideApp
import com.example.toyproject.utils.GridSpaceItemDecoration
import com.example.toyproject.data.SkinType
import com.example.toyproject.viewmodel.ChannelViewModel
import javax.inject.Inject

class DetailFragment :
    BaseFragment<FragmentDetailBinding, ChannelViewModel>() {

    @Inject
    lateinit var adapter: DetailAdapter

    override val viewModel: ChannelViewModel by viewModels()

    private val type by lazy { requireArguments().getSerializable(KEY) as SkinType }

    private var _binding: FragmentDetailBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            txtSkinName.setText(type.typeName)
            context?.let {
                GlideApp.with(it).load(type.skinImg)
                    .placeholder(R.mipmap.ic_launcher)
                    .error(R.drawable.ch_network_error_illust)
                    .into(ivMainSkin)
            }
        }

        adapter = DetailAdapter()
        binding.rvDetail.adapter = adapter

        val x = (resources.displayMetrics.density * 10).toInt()
        binding.rvDetail.addItemDecoration(GridSpaceItemDecoration(x))

        adapter.submitList(
            when (type.typeName) {
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val KEY = "skin"
    }
}
