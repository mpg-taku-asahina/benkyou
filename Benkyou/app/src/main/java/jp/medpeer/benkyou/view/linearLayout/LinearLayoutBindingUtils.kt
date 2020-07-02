package jp.medpeer.benkyou.view.linearLayout

import android.widget.LinearLayout
import androidx.annotation.LayoutRes
import androidx.core.util.Consumer
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableList
import jp.medpeer.benkyou.view.VariableLayoutPair

object LinearLayoutBindingUtils {

    fun <T> bind(layout: LinearLayout, itemCollection: Collection<T>, variableId: Int, @LayoutRes layoutId: Int, onItemLongClick: Consumer<Pair<Int, T>>?) {
        LinearLayoutBindingAdapter(layout, itemCollection, VariableLayoutPair(variableId, layoutId), onItemLongClick)
    }

    fun <T> bind(layout: LinearLayout, itemCollection: Collection<T>, variableId: Int, @LayoutRes layoutId: Int) {
        bind(layout, itemCollection, variableId, layoutId, null)
    }


    fun <H, T> bindSectioned(layout: LinearLayout, vararg sections: SimpleSection<H, T>) {
        val list: ObservableList<SimpleSection<H, T>> = ObservableArrayList()
        list.addAll(sections.toList())
        SectionedLinearLayoutAdapter(layout, list)
    }

    fun <H, T> bindSectioned(layout: LinearLayout, list: ObservableList<SimpleSection<H, T>>) {
        SectionedLinearLayoutAdapter(layout, list)
    }
}
