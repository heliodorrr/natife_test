package com.helio.ui.imagelist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.updateLayoutParams
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.helio.api.model.ImageModel
import com.helio.ui.R
import com.helio.ui.databinding.ItemImageBinding

private class ImageListAdapter(
    private val isInPortrait: Boolean,
    private val onItemClick: (String) -> Unit
) : PagingDataAdapter<ImageModel, ImageListAdapter.ImageViewHolder>(
    diffCallback = object : DiffUtil.ItemCallback<ImageModel>() {
        override fun areContentsTheSame(oldItem: ImageModel, newItem: ImageModel): Boolean {
            return oldItem == newItem
        }

        override fun areItemsTheSame(oldItem: ImageModel, newItem: ImageModel): Boolean {
            return oldItem == newItem
        }
    }
) {
    class ImageViewHolder(
        private val isInPortrait: Boolean,
        private val onItemClick: (url: String) -> Unit,
        private val binding: ItemImageBinding
    )
        : RecyclerView.ViewHolder(binding.root) {


        init {
            val dimension = binding.root
                .context.resources.getDimensionPixelSize(R.dimen.item_image_size)

            binding.root.updateLayoutParams {
                width = if (isInPortrait) ViewGroup.LayoutParams.MATCH_PARENT else dimension
                height = if(isInPortrait) dimension else ViewGroup.LayoutParams.MATCH_PARENT
            }


        }

        fun bind(item: ImageModel) {
            binding.root.setOnClickListener { onItemClick(item.url) }
            Glide.with(binding.root.context)
                .load(item.url)
                .into(binding.imageView)
        }
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val binding = ItemImageBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ImageViewHolder(isInPortrait, onItemClick, binding)
    }
}

fun configureImageListRecyclerView(
    rv: RecyclerView,
    isInPortrait: Boolean,
    onItemClick: (String)->Unit
): PagingDataAdapter<ImageModel, *> {

    val lm = GridLayoutManager(
        rv.context,
        2,
        if (isInPortrait) GridLayoutManager.VERTICAL else GridLayoutManager.HORIZONTAL,
        false
    )

    rv.layoutManager = lm

    val adapter = ImageListAdapter(
        isInPortrait, onItemClick
    )

    rv.adapter = adapter
    return adapter
}
