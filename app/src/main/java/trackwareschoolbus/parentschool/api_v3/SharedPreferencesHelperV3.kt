package trackwareschoolbus.parentschool.api_v3

import android.annotation.SuppressLint
import android.app.Application
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import com.google.gson.Gson
import trackwareschoolbus.parentschool.toolsV2.ConstantsV2.SHARED_PREFERENCES_KEYS
import trackwareschoolbus.parentschool.utilityParent.UtilityParent

object SharedPreferencesHelperV3  {
    var sharedPreferencesInstance: SharedPreferences? = null


    fun sharedPreferencesInstance(): SharedPreferences? {
        return sharedPreferencesInstance
    }

    fun initSharedPrefInstance(applicationContext: Application): SharedPreferences? {
        if (sharedPreferencesInstance == null) {
            sharedPreferencesInstance =
                PreferenceManager.getDefaultSharedPreferences(applicationContext)
        }
        return sharedPreferencesInstance
    }

    @SuppressLint("ApplySharedPref")
    fun clearSharedPref() {
        sharedPreferencesInstance
            ?.edit()
            ?.clear()
            ?.commit()
    }

    fun setValue(key: String?, value: Any?): Boolean {
        return try {
            if (value == null || value is String) sharedPreferencesInstance?.edit()?.putString(key, value as String?)?.apply() else if (value is Boolean) {

                sharedPreferencesInstance?.edit()?.putBoolean(key, (value as Boolean?)!!)?.apply()
            } else if (value is Int) {
                sharedPreferencesInstance?.edit()?.putInt(key, (value as Int?)!!)?.apply()
            }
            true
        } catch (e: Exception) {
            false
        }
    }

    fun getBoolean(key: String?): Boolean {
        return try {
            sharedPreferencesInstance!!.getBoolean(key, false)
        } catch (e: Exception) {
            false
        }
    }

    fun getStringValue(key: String?): String? {
        return try {
            sharedPreferencesInstance!!.getString(key, "")
        } catch (e: Exception) {
            ""
        }
    }
    fun getAuthorization(): String? {
        return sharedPreferencesInstance!!
            .getString(SHARED_PREFERENCES_KEYS.AUTHORIZATION, "")!!
            .trim { it <= ' ' }
    }

    fun setAuthorization(authorization: String?): Boolean {
        return setValue(SHARED_PREFERENCES_KEYS.AUTHORIZATION, authorization)
    }

    fun getMobileNumber(): String? {
        return getStringValue(SHARED_PREFERENCES_KEYS.USER_SAVED_MOBILE_NUMBER)
    }

    fun getPin(): String? {
        return getStringValue(SHARED_PREFERENCES_KEYS.USER_SAVED_PIN)!!.trim()
    }

    fun getEmail(): String? {
        return getStringValue(SHARED_PREFERENCES_KEYS.USER_SAVED_EMAIL)
    }

    fun setName(name: String?): Boolean {
        return setValue(SHARED_PREFERENCES_KEYS.USER_SAVED_NAME, name)
    }

    fun getName(): String? {
        return getStringValue(SHARED_PREFERENCES_KEYS.USER_SAVED_NAME)
    }


    fun getCurrentAppLanguage(): String? {
        try {
            return UtilityParent.getStringShared(UtilityParent.LANGUAGE)
        }catch (e:Exception){
            return "en"
        }

//        return if (getStringValue(SHARED_PREFERENCES_KEYS.USER_SELECTED_LANGUAGE).equals("",false)) "en" else getStringValue(
//            SHARED_PREFERENCES_KEYS.USER_SELECTED_LANGUAGE
//        )!!.trim()
    }
//fun applySharedPrefValue(key: String?, value: Any?) {
//    try {
//        if (value == null) {
//            removeSharedPrefValue(key)
//        } else if (value is String) {
//            sharedPreferencesInstance?.edit()
//                ?.putString(key, value as String?)?.apply()
//        } else if (value is Boolean) {
//            sharedPreferencesInstance?.edit()?.putBoolean(key, (value as Boolean?)!!)?.apply()
//        } else if (value is Int) {
//            sharedPreferencesInstance?.edit()?.putInt(key, (value as Int?)!!)?.apply()
//        }
//    } catch (e: Exception) {
//    }
//}


    @SuppressLint("ApplySharedPref")
    fun setSharedPrefValue(key: String?, value: Any?) {
        try {
            if (value == null) {
                removeSharedPrefValue(key)
            } else if (value is String) {
                sharedPreferencesInstance?.edit()?.putString(key, value as String?)?.commit()
            } else if (value is Boolean) {
                sharedPreferencesInstance?.edit()?.putBoolean(key, (value as Boolean?)!!)?.commit()
            } else if (value is Int) {
                sharedPreferencesInstance?.edit()?.putInt(key, (value as Int?)!!)?.commit()
            }
        } catch (e: java.lang.Exception) {
        }
    }

    fun removeSharedPrefValue(vararg keys: String?) {
        try {
            for (key in keys) {
                sharedPreferencesInstance?.edit()?.remove(key)?.apply()
            }
        } catch (e: java.lang.Exception) {

        }
    }

    fun <GSON_TYPE : Any> setGsonValue(key: String?, gsonObject: GSON_TYPE) {
        val json = Gson().toJson(gsonObject, gsonObject.javaClass)
        setSharedPrefValue(key, json)
    }

    fun <GSON_TYPE> getGsonValue(
        key: String = "",
        logInDataClass: Class<GSON_TYPE>?
    ): GSON_TYPE? {
        return try {
            val gson = Gson()
            gson.fromJson(sharedPreferencesInstance?.getString(key, ""), logInDataClass)
        } catch (error: Error) {
            null
        }
    }

//    fun isFirstRun(): Boolean {
//        return sharedPreferencesInstance?.getBoolean(ConstantsV3.USER_DATA.IS_FIRST_TIME, true)
//            ?: false
//    }


    fun saveAuthorizationList(authorization: String) {
        return setSharedPrefValue(
            "AUTHORIZATIONS_LIST",
            authorization
        )
    }

    fun getAuthorizationList(): String {
        return sharedPreferencesInstance?.getString(
            "AUTHORIZATIONS_LIST",
            ""
        ) ?: ""
    }


    fun saveAuthorization(stringKey:String,authorization: String) {
        return setSharedPrefValue(
            stringKey,
            authorization
        )
    }

    fun getAuthorization(stringKey:String): String {
        return sharedPreferencesInstance?.getString(
            stringKey,
            ""
        ) ?: ""
    }

//    fun save(favoratesList: FavoratesRecipes) {
//        return setGsonValue(
//            AppConstant.USER_DATA.SAVED_RECIPES,
//            favoratesList
//        )
//    }
//
//    fun getSavedRecipes(): FavoratesRecipes {
//        return getGsonValue(AppConstant.USER_DATA.SAVED_RECIPES, FavoratesRecipes::class.java)
//            ?: FavoratesRecipes()
//    }
//
//
//    fun save(savedList: ShoppingList) {
//        return setGsonValue(
//            AppConstant.USER_DATA.SAVED_RECIPES,
//            savedList
//        )
//    }
//
//    fun getShoppingList(): ShoppingList {
//        return getGsonValue(AppConstant.USER_DATA.SAVED_RECIPES, ShoppingList::class.java)
//            ?: ShoppingList()
//    }
//
//
//    fun saveLanguage(authorization: String) {
//        return setSharedPrefValue(
//            AppConstant.LANGUAGE,
//            authorization
//        )
//    }
//
//    fun getLanguage(): String {
//        return sharedPreferencesInstance?.getString(
//            AppConstant.LANGUAGE,
//            "en"
//        ) ?: "en"
//    }
//
//
//
//
//    fun saveUserName(userName: String) {
//        return setSharedPrefValue(
//            AppConstant.USER_DATA.USR_NAM,
//            userName
//        )
//    }
//
//    fun getUserName(): String {
//        return sharedPreferencesInstance?.getString(
//            AppConstant.USER_DATA.USR_NAM,
//            ""
//        ) ?: ""
//    }
//
//
//    fun savePass(pass: String) {
//        return setSharedPrefValue(
//            AppConstant.USER_DATA.PASS,
//            pass
//        )
//    }
//
//    fun getPass(): String {
//        return sharedPreferencesInstance?.getString(
//            AppConstant.USER_DATA.PASS,
//            ""
//        ) ?: ""
//    }

}