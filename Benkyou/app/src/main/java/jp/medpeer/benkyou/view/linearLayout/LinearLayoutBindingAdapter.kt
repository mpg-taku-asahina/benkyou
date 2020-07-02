package jp.medpeer.benkyou.view.linearLayout

import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import androidx.core.util.Consumer
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableList
import androidx.databinding.ViewDataBinding
import jp.medpeer.benkyou.R
import jp.medpeer.benkyou.view.VariableLayoutPair


class LinearLayoutBindingAdapter<T>(val layout: LinearLayout, itemCollection: Collection<T>, val variableLayoutPair: VariableLayoutPair, val onItemClick: Consumer<Pair<Int, T>>?) : View.OnClickListener {

    private val onListChangedCallback: ObservableList.OnListChangedCallback<ObservableList<T>>

    private val itemList: ObservableList<T>

    private var inflater: LayoutInflater = LayoutInflater.from(layout.context)

    init {

        onListChangedCallback = object : ObservableList.OnListChangedCallback<ObservableList<T>>() {

            override fun onChanged(sender: ObservableList<T>) {
                notificationLayout()
            }

            override fun onItemRangeChanged(sender: ObservableList<T>, positionStart: Int, itemCount: Int) {
                notificationLayout()
            }

            override fun onItemRangeInserted(sender: ObservableList<T>, positionStart: Int, itemCount: Int) {
                notificationLayout()
            }

            override fun onItemRangeMoved(sender: ObservableList<T>, fromPosition: Int, toPosition: Int, itemCount: Int) {
                notificationLayout()
            }

            override fun onItemRangeRemoved(sender: ObservableList<T>, positionStart: Int, itemCount: Int) {
                notificationLayout()
            }
        }

        if (itemCollection is ObservableList<*>) {
            itemList = itemCollection as ObservableList<T>
        } else {
            itemList = ObservableArrayList()
            itemList.addAll(itemCollection)
        }

        notificationLayout()

        itemList.addOnListChangedCallback(onListChangedCallback)
    }

    fun notificationLayout() {
        layout.removeAllViews()

        if (itemList.isNotEmpty()) {
            itemList.forEachIndexed { index, item ->
                val viewBinding: ViewDataBinding = DataBindingUtil.inflate(inflater, variableLayoutPair.layoutId, null, false)
                viewBinding.setVariable(variableLayoutPair.variableId, item)
                val view = viewBinding.root

                view.setTag(R.id.key_sectioned_recycler_view_adapter_item, item)
                view.setTag(R.id.key_simple_recycler_view_adapter_position, index)

                if (onItemClick != null) {
                    view.setOnClickListener(this)
                    view.isClickable = true
                } else {
                    view.setOnClickListener(null)
                    view.isClickable = false
                }

                layout.addView(view)
            }
        }
    }


    @Suppress("UNCHECKED_CAST")
    override fun onClick(v: View?) {
        v?.let {
            onItemClick!!.accept(Pair(it.getTag(R.id.key_simple_recycler_view_adapter_position) as Int, it.getTag(R.id.key_sectioned_recycler_view_adapter_item) as T))
        }
    }
}
