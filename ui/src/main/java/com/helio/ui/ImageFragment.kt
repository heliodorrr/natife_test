package com.helio.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.helio.ui.databinding.FragmentImageBinding

class ImageFragment: BindingAwareFragment<FragmentImageBinding>() {
    companion object {
        const val URL_KEY = "URL_KEY"
    }
    override var viewBinding: FragmentImageBinding? = null

    private val urlArg: String get() = arguments?.getString(URL_KEY)!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentImageBinding.inflate(inflater, container, false)
            .also { viewBinding = it }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding?.let {
            Glide.with(this)
                .load(urlArg)
                .into(it.image)
        }
    }
}