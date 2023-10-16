package com.helio.ui.imagelist

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.paging.PagingDataAdapter
import com.helio.api.model.ImageModel
import com.helio.ui.BindingAwareFragment
import com.helio.ui.ImageFragment
import com.helio.ui.R
import com.helio.ui.databinding.FragmentImageListBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ImageListFragment : BindingAwareFragment<FragmentImageListBinding>() {
    override var viewBinding: FragmentImageListBinding? = null

    private val viewModel by viewModels<ImageListViewModel> { factory }

    private var adapter: PagingDataAdapter<ImageModel, *>? = null


    private val isInPortrait
        get() = resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewBinding?.imageListRv?.scrollToPosition(0)
                viewModel.pagingDataFlow.collectLatest { pagingDataFlow->
                    pagingDataFlow.collect { pagingData ->
                        adapter?.submitData(pagingData)
                    }
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentImageListBinding.inflate(inflater, container, false)
            .also { viewBinding = it }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding?.let {

            it.queryField.doOnTextChanged { text, start, before, count ->
                viewModel.queryStateFlow.update { text.toString() }
            }

            adapter = configureImageListRecyclerView(
                rv = it.imageListRv,
                isInPortrait = isInPortrait,
                onItemClick = { url ->
                    findNavController().navigate(
                        R.id.action_imageListFragment_to_imageFragment,
                        args = bundleOf(ImageFragment.URL_KEY to url)
                    )
                }
            )
        }

    }
}