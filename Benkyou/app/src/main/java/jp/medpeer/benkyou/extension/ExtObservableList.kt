package jp.medpeer.benkyou.extension

import androidx.databinding.ObservableList

/**
 * android.databinding.ObservableList ユーティリティ
 */
object ObservableListUtils {

    /**
     * リストに何かしらの変更（要素の変更、追加、削除）があったときに呼ばれるコールバック
     *
     * @param <T> リスト要素の型
    </T> */
    abstract class OnListChangedCallback<T> : ObservableList.OnListChangedCallback<ObservableList<T>>() {

        abstract fun onListChanged(startPosition: Int, itemCount: Int)

        override fun onChanged(sender: ObservableList<T>) {
            // do nothing
        }

        override fun onItemRangeChanged(sender: ObservableList<T>, startPosition: Int, itemCount: Int) {
            onListChanged(startPosition, itemCount)
        }

        override fun onItemRangeInserted(sender: ObservableList<T>, startPosition: Int, itemCount: Int) {
            onListChanged(startPosition, itemCount)
        }

        override fun onItemRangeMoved(sender: ObservableList<T>, positionFrom: Int, positionTo: Int, itemCount: Int) {
        }

        override fun onItemRangeRemoved(sender: ObservableList<T>, startPosition: Int, itemCount: Int) {
            onListChanged(startPosition, itemCount)
        }
    }

    /**
     * リスト要素に変更があったときに呼ばれるコールバック
     *
     * @param <T> リスト要素の型
    </T> */
    abstract class OnItemRangeChangedCallback<T> : ObservableList.OnListChangedCallback<ObservableList<T>>() {

        abstract fun onChanged(startPosition: Int, itemCount: Int)

        override fun onChanged(sender: ObservableList<T>) {
            // do nothing
        }

        override fun onItemRangeChanged(sender: ObservableList<T>, startPosition: Int, itemCount: Int) {
            onChanged(startPosition, itemCount)
        }

        override fun onItemRangeInserted(sender: ObservableList<T>, startPosition: Int, itemCount: Int) {
            // do nothing
        }

        override fun onItemRangeMoved(sender: ObservableList<T>, positionFrom: Int, positionTo: Int, itemCount: Int) {
        }

        override fun onItemRangeRemoved(sender: ObservableList<T>, startPosition: Int, itemCount: Int) {
            // do nothing
        }
    }

    /**
     * リストに要素が挿入されたときに呼ばれるコールバック
     *
     * @param <T> リスト要素の型
    </T> */
    abstract class OnItemRangeInsertedCallback<T> : ObservableList.OnListChangedCallback<ObservableList<T>>() {

        abstract fun onInserted(startPosition: Int, itemCount: Int)

        override fun onChanged(sender: ObservableList<T>) {
            // do nothing
        }

        override fun onItemRangeChanged(sender: ObservableList<T>, startPosition: Int, itemCount: Int) {
            // do nothing
        }

        override fun onItemRangeInserted(sender: ObservableList<T>, startPosition: Int, itemCount: Int) {
            onInserted(startPosition, itemCount)
        }

        override fun onItemRangeMoved(sender: ObservableList<T>, positionFrom: Int, positionTo: Int, itemCount: Int) {
        }

        override fun onItemRangeRemoved(sender: ObservableList<T>, startPosition: Int, itemCount: Int) {
            // do nothing
        }
    }

    /**
     * リストから要素が削除されたときに呼ばれるコールバック
     *
     * @param <T> リスト要素の型
    </T> */
    abstract class OnItemRangeRemovedCallback<T> : ObservableList.OnListChangedCallback<ObservableList<T>>() {

        abstract fun onRemoved(startPosition: Int, itemCount: Int)

        override fun onChanged(sender: ObservableList<T>) {
            // do nothing
        }

        override fun onItemRangeChanged(sender: ObservableList<T>, startPosition: Int, itemCount: Int) {
            // do nothing
        }

        override fun onItemRangeInserted(sender: ObservableList<T>, startPosition: Int, itemCount: Int) {
            // do nothing
        }

        override fun onItemRangeMoved(sender: ObservableList<T>, positionFrom: Int, positionTo: Int, itemCount: Int) {
        }

        override fun onItemRangeRemoved(sender: ObservableList<T>, startPosition: Int, itemCount: Int) {
            onRemoved(startPosition, itemCount)
        }
    }

    /**
     * リストのサイズが変更されたときに呼ばれるコールバック
     *
     * @param <T> リスト要素の型
    </T> */
    abstract class OnListSizeChangedCallback<T> : ObservableList.OnListChangedCallback<ObservableList<T>>() {

        abstract fun onListSizeChanged(itemCount: Int)

        override fun onChanged(sender: ObservableList<T>) {
            // do nothing
        }

        override fun onItemRangeChanged(sender: ObservableList<T>, startPosition: Int, itemCount: Int) {
            // do nothing
        }

        override fun onItemRangeInserted(sender: ObservableList<T>, startPosition: Int, itemCount: Int) {
            onListSizeChanged(itemCount)
        }

        override fun onItemRangeMoved(sender: ObservableList<T>, positionFrom: Int, positionTo: Int, itemCount: Int) {
        }

        override fun onItemRangeRemoved(sender: ObservableList<T>, startPosition: Int, itemCount: Int) {
            onListSizeChanged(itemCount)
        }
    }

    /**
     * リスト内のある要素の位置が変更されたときに呼ばれるコールバック
     * ある要素が一つ削除された直後に要素が一つ挿入されたとき、要素の位置が変更されたとみなす（削除された要素と挿入された要素の参照が等しいかどうかはチェックしていない）。
     *
     * @param <T> リスト要素の型
    </T> */
    abstract class OnItemMovedCallback<T> : ObservableList.OnListChangedCallback<ObservableList<T>>() {

        private var fromPosition = -1

        abstract fun onMoved(fromPosition: Int, toPosition: Int)

        override fun onChanged(sender: ObservableList<T>) {
            fromPosition = -1
        }

        override fun onItemRangeChanged(sender: ObservableList<T>, positionStart: Int, itemCount: Int) {
            fromPosition = -1
        }

        override fun onItemRangeInserted(sender: ObservableList<T>, positionStart: Int, itemCount: Int) {
            if (itemCount == 1 && fromPosition != -1) {
                onMoved(fromPosition, positionStart)
            }

            fromPosition = -1
        }

        override fun onItemRangeMoved(sender: ObservableList<T>, fromPosition: Int, toPosition: Int, itemCount: Int) {
        }

        override fun onItemRangeRemoved(sender: ObservableList<T>, positionStart: Int, itemCount: Int) {
            fromPosition = if (itemCount == 1) positionStart else -1
        }
    }
}

fun <T> ObservableList<T>.addOnListChangedCallback(runnable: Runnable) {
    this.addOnListChangedCallback(object : ObservableListUtils.OnListChangedCallback<T>() {
        override fun onListChanged(startPosition: Int, itemCount: Int) {
            runnable.run()
        }
    })
}
fun <T> ObservableList<T>.addOnItemRangeChangedCallback(runnable: Runnable) {
    this.addOnListChangedCallback(object : ObservableListUtils.OnItemRangeChangedCallback<T>() {
        override fun onChanged(startPosition: Int, itemCount: Int) {
            runnable.run()
        }
    })
}
