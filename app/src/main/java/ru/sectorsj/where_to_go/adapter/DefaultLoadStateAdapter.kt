package ru.sectorsj.where_to_go.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.sectorsj.where_to_go.databinding.CarddLoadStateBinding

typealias TryAgainAction = () -> Unit

class DefaultLoadStateAdapter(
    private val tryAgainAction: TryAgainAction
    ): LoadStateAdapter<LoadStateHolder>() {
    override fun onBindViewHolder(holder: LoadStateHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateHolder {
        val binding = CarddLoadStateBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LoadStateHolder(binding, tryAgainAction)
    }
}

class LoadStateHolder(
    private val binding: CarddLoadStateBinding,
    private val tryAgainAction: TryAgainAction
    ): RecyclerView.ViewHolder(binding.root) {


    fun bind(loadState: LoadState) {
        with(binding) {
            progressBar.isVisible = loadState is LoadState.Error
            tryAgainButton.isVisible = loadState is LoadState.Error
            messageTextView.isVisible = loadState is LoadState.Error

            tryAgainButton.setOnClickListener {
                tryAgainAction.invoke()
            }
        }

    }
}