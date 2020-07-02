package jp.medpeer.benkyou.extension

import androidx.databinding.Observable
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.core.util.Consumer

fun <T> ObservableField<T>.addOnPropertyChangedCallback(runnable: Runnable) {
    this.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
        override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
            runnable.run()
        }
    })
}

fun ObservableBoolean.addOnPropertyChangedCallback(runnable: Runnable) {
    this.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
        override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
            runnable.run()
        }
    })
}

fun <T> ObservableField<T>.addOnPropertyChangedCallback(consumer: Consumer<T>) {
    this.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
        override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
            consumer.accept(this@addOnPropertyChangedCallback.get())
        }
    })
}

fun ObservableBoolean.addOnPropertyChangedCallback(consumer: Consumer<Boolean>) {
    this.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
        override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
            consumer.accept(this@addOnPropertyChangedCallback.get())
        }
    })
}
