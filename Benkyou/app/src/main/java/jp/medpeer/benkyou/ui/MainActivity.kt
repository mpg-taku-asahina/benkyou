package jp.medpeer.benkyou.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableField
import jp.medpeer.benkyou.R
import jp.medpeer.benkyou.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    val number: ObservableField<String> = ObservableField("0")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.view = this
    }

    fun selectNumber(key: Int) {
        if (number.get() == "0") {
            number.set("")
        }

        val aa = number.get() + key.toString()
        number.set(aa)
    }
}
