package com.example.toyproject.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import io.reactivex.disposables.CompositeDisposable

abstract class BaseActivity<VB : ViewBinding, VM : BaseViewModel>(private val bindingFactory: (LayoutInflater) -> VB) :
    AppCompatActivity() {

    lateinit var binding: VB

    abstract val viewModel: VM

    open fun setupUI() {}

    open fun setupAPI() {}

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = bindingFactory.invoke(layoutInflater)
        setContentView(binding.root)

        setupUI()
        setupAPI()
    }

    private val disposables by lazy { CompositeDisposable() }

    @CallSuper
    override fun onDestroy() {
        super.onDestroy()
        disposables.clear()
    }
}