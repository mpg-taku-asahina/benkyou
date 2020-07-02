package jp.medpeer.benkyou.preference

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Build
import android.security.KeyPairGeneratorSpec
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import android.text.TextUtils
import android.util.Base64
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.internal.`$Gson$Types`
import jp.medpeer.benkyou.BaseApplication
import java.math.BigInteger
import java.security.KeyPairGenerator
import java.security.KeyStore
import java.security.spec.AlgorithmParameterSpec
import java.util.*
import javax.crypto.Cipher
import javax.security.auth.x500.X500Principal


class Preferences {

    fun clearData() {
        val editor = getPreference().edit()
        editor.clear()
        editor.apply()
    }


    //region トークン
    private val KEY_ACCESS_TOKEN = "KEY_ACCESS_TOKEN"

    private val KEY_REFRESH_TOKEN = "KEY_REFRESH_TOKEN"

    fun getAccessToken(): String? {
        return getObject(KEY_ACCESS_TOKEN, String::class.java, null)
    }

    fun getRefreshToken(): String? {
        return getObject(KEY_REFRESH_TOKEN, String::class.java, null)
    }

    fun setToken(accessToken: String, refreshToken: String) {
        setObject(KEY_ACCESS_TOKEN, accessToken)
        setObject(KEY_REFRESH_TOKEN, refreshToken)
    }
    //endregion


    //region ラッパーメソッドなどの定義
    private val SHARED_PREFERENCES_NAME = "PreferencesUtils"

    private fun getPreference(): SharedPreferences {
        return BaseApplication.context.getSharedPreferences(SHARED_PREFERENCES_NAME, MODE_PRIVATE)
    }

    private fun removeObject(key: String) {
        val editor = getPreference().edit()
        editor.remove(key)
        editor.apply()
    }

    private fun setObject(key: String, value: Any) {
        val editor = getPreference().edit()
        val json = createGson().toJson(value)
        editor.putString(key, json)
        editor.apply()
    }

    private fun <T> getObject(key: String, type: Class<T>, default: T?): T? {
        val json = getPreference().getString(key, null)
        return json?.let {
            createGson().fromJson(it, type)
        } ?: run {
            default
        }
    }

    private fun <T> getObjectList(key: String, type: Class<T>): MutableList<T> {
        val json = getPreference().getString(key, null)
        return json?.let {
            val typeClass = `$Gson$Types`.newParameterizedTypeWithOwner(null, MutableList::class.java, type)
            createGson().fromJson(it, typeClass) as MutableList<T>
        } ?: run {
            ArrayList<T>()
        }
    }

    private fun createGson(): Gson {
        val builder = GsonBuilder()
        builder.enableComplexMapKeySerialization()
        return builder.create()
    }
    //endregion
}
