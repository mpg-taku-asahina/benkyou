package jp.medpeer.benkyou.view.linearLayout

import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import androidx.core.util.Consumer
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableField
import androidx.databinding.ObservableList
import androidx.databinding.ViewDataBinding
import jp.medpeer.benkyou.R
import jp.medpeer.benkyou.view.VariableLayoutPair
import jp.medpeer.benkyou.extension.addOnListChangedCallback

/**
 * データバインディング対応 RecyclerViewAdapter。セクション分け対応。
 *
 *
 * 参考： https://github.com/radzio/android-data-binding-recyclerview
 */
class SectionedLinearLayoutAdapter<H, T>(val layout: LinearLayout, private val sectionList: ObservableList<SimpleSection<H, T>>) : View.OnClickListener, View.OnLongClickListener {

    var inflater: LayoutInflater = LayoutInflater.from(layout.context)

    init {
        notificationLayout()

        sectionList.addOnListChangedCallback(Runnable {
            notificationLayout()
        })
    }

    private fun notificationLayout() {
        layout.removeAllViews()

        if (sectionList.isNotEmpty()) {
            sectionList.forEachIndexed { index, item ->
                val headerVariableLayoutPair = item.subheaderVariableLayoutPair
                val headerViewBinding: ViewDataBinding = DataBindingUtil.inflate(inflater, headerVariableLayoutPair.layoutId, null, false)
                headerViewBinding.setVariable(headerVariableLayoutPair.variableId, item.subheader.get())

                val headerView = headerViewBinding.root
                headerView.setTag(R.id.key_sectioned_recycler_view_adapter_item, null)
                layout.addView(headerView)

                item.itemList.forEachIndexed { listIndex, listItem ->
                    val variableLayoutPair = item.itemVariableLayoutPair
                    val viewBinding: ViewDataBinding = DataBindingUtil.inflate(inflater, variableLayoutPair.layoutId, null, false)
                    viewBinding.setVariable(variableLayoutPair.variableId, listItem)

                    val view = viewBinding.root
                    view.setTag(R.id.key_sectioned_recycler_view_adapter_item, listItem)
                    view.setTag(R.id.key_simple_recycler_view_adapter_position, listIndex)
                    view.setTag(R.id.key_sectioned_recycler_view_adapter_section_index, index)

                    if (item.onItemClick != null) {
                        view.setOnClickListener(this)
                        view.isClickable = true
                    } else {
                        view.setOnClickListener(null)
                        view.isClickable = false
                    }

                    if (item.onItemLongClick != null) {
                        view.setOnLongClickListener(this)
                        view.isLongClickable = true
                    } else {
                        view.setOnLongClickListener(null)
                        view.isLongClickable = false
                    }

                    layout.addView(view)
                }
            }
        }
    }


    @Suppress("UNCHECKED_CAST")
    override fun onClick(v: View) {
        val sectionIndex = v.getTag(R.id.key_sectioned_recycler_view_adapter_section_index) as Int

        val onItemClick = sectionList[sectionIndex].onItemClick ?: return

        onItemClick.accept(Pair(v.getTag(R.id.key_simple_recycler_view_adapter_position) as Int, v.getTag(R.id.key_sectioned_recycler_view_adapter_item) as T))
    }

    @Suppress("UNCHECKED_CAST")
    override fun onLongClick(v: View): Boolean {
        val sectionIndex = v.getTag(R.id.key_sectioned_recycler_view_adapter_section_index) as Int

        val onItemLongClick = sectionList[sectionIndex].onItemLongClick ?: return false

        onItemLongClick.accept(Pair(v.getTag(R.id.key_simple_recycler_view_adapter_position) as Int, v.getTag(R.id.key_sectioned_recycler_view_adapter_item) as T))
        return true
    }


    interface Section<H, T> {

        val subheader: ObservableField<H>

        val subheaderVariableLayoutPair: VariableLayoutPair

        val itemList: ObservableList<T>

        val itemVariableLayoutPair: VariableLayoutPair

        val onItemClick: Consumer<Pair<Int, T>>?

        val onItemLongClick: Consumer<Pair<Int, T>>?

    }
}
