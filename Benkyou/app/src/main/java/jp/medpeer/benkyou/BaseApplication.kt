package jp.medpeer.benkyou

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.multidex.MultiDexApplication


/**
 * アプリ用にカスタムした Application。
 */
open class BaseApplication : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()

        context = applicationContext
    }


    companion object {

        lateinit var context: Context

        lateinit var activity: AppCompatActivity
    }
}
