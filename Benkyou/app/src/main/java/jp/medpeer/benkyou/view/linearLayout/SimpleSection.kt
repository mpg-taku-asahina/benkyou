package jp.medpeer.benkyou.view.linearLayout

import androidx.annotation.LayoutRes
import androidx.core.util.Consumer
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableField
import androidx.databinding.ObservableList
import jp.medpeer.benkyou.view.VariableLayoutPair

/**
 * SectionedRecyclerViewAdapter に表示するシンプルなセクション。
 */
class SimpleSection<H, T>(override val subheader: ObservableField<H>, override val subheaderVariableLayoutPair: VariableLayoutPair, itemCollection: Collection<T>, override val itemVariableLayoutPair: VariableLayoutPair, override val onItemClick: Consumer<Pair<Int, T>>?, override val onItemLongClick: Consumer<Pair<Int, T>>?) : SectionedLinearLayoutAdapter.Section<H, T> {

    override val itemList: ObservableList<T>

    init {
        if (itemCollection is ObservableList<*>) {
            itemList = itemCollection as ObservableList<T>
        } else {
            itemList = ObservableArrayList()
            itemList.addAll(itemCollection)
        }
    }


    companion object {

        //region ファクトリメソッド
        fun <H, T> create(subheader: ObservableField<H>, subheaderVariableId: Int, @LayoutRes subheaderLayoutId: Int, itemCollection: Collection<T>, itemVariableId: Int, @LayoutRes itemLayoutId: Int, onItemClick: Consumer<Pair<Int, T>>?, onItemLongClick: Consumer<Pair<Int, T>>?): SimpleSection<H, T> {
            return SimpleSection(subheader, VariableLayoutPair(subheaderVariableId, subheaderLayoutId), itemCollection, VariableLayoutPair(itemVariableId, itemLayoutId), onItemClick, onItemLongClick)
        }

        fun <H, T> create(subheader: ObservableField<H>, subheaderVariableId: Int, @LayoutRes subheaderLayoutId: Int, itemCollection: Collection<T>, itemVariableId: Int, @LayoutRes itemLayoutId: Int, onItemClick: Consumer<Pair<Int, T>>?): SimpleSection<H, T> {
            return create(subheader, subheaderVariableId, subheaderLayoutId, itemCollection, itemVariableId, itemLayoutId, onItemClick, null)
        }

        fun <H, T> create(subheader: ObservableField<H>, subheaderVariableId: Int, @LayoutRes subheaderLayoutId: Int, itemCollection: Collection<T>, itemVariableId: Int, @LayoutRes itemLayoutId: Int): SimpleSection<H, T> {
            return create(subheader, subheaderVariableId, subheaderLayoutId, itemCollection, itemVariableId, itemLayoutId, null, null)
        }

        fun <H, T> create(subheader: H, subheaderVariableId: Int, @LayoutRes subheaderLayoutId: Int, itemCollection: Collection<T>, itemVariableId: Int, @LayoutRes itemLayoutId: Int, onItemClick: Consumer<Pair<Int, T>>?, onItemLongClick: Consumer<Pair<Int, T>>?): SimpleSection<H, T> {
            return create(ObservableField(subheader), subheaderVariableId, subheaderLayoutId, itemCollection, itemVariableId, itemLayoutId, onItemClick, onItemLongClick)
        }

        fun <H, T> create(subheader: H, subheaderVariableId: Int, @LayoutRes subheaderLayoutId: Int, itemCollection: Collection<T>, itemVariableId: Int, @LayoutRes itemLayoutId: Int, onItemClick: Consumer<Pair<Int, T>>?): SimpleSection<H, T> {
            return create(ObservableField(subheader), subheaderVariableId, subheaderLayoutId, itemCollection, itemVariableId, itemLayoutId, onItemClick, null)
        }

        fun <H, T> create(subheader: H, subheaderVariableId: Int, @LayoutRes subheaderLayoutId: Int, itemCollection: Collection<T>, itemVariableId: Int, @LayoutRes itemLayoutId: Int): SimpleSection<H, T> {
            return create(ObservableField(subheader), subheaderVariableId, subheaderLayoutId, itemCollection, itemVariableId, itemLayoutId, null, null)
        }
    }
}
