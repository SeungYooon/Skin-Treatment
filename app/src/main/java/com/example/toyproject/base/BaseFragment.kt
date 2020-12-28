package com.example.toyproject.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import io.reactivex.disposables.CompositeDisposable

abstract class BaseFragment<B : ViewBinding, VM : BaseViewModel>() :
    Fragment() {

    lateinit var bindingFactory: (LayoutInflater, ViewGroup?, Bundle?) -> B

    abstract val viewModel: VM

    private val disposables by lazy { CompositeDisposable() }

    @CallSuper
    override fun onDestroy() {
        super.onDestroy()
        disposables.clear()
    }
}