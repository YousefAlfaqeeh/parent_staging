package trackwareschoolbus.parentschool.toolsV2

import android.R
import android.annotation.SuppressLint
import android.content.Context
import android.view.ContextThemeWrapper
import android.view.MenuItem
import android.view.View
import android.widget.PopupWindow
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.MenuRes
import androidx.annotation.StyleRes
import androidx.appcompat.view.menu.MenuBuilder
import androidx.appcompat.view.menu.MenuPopupHelper
import androidx.appcompat.widget.PopupMenu
import trackwareschoolbus.parentschool.api_v3.getTintedDrawable
import java.util.*

class GeneralPopupMenu {
    /**/
    private var popup: PopupMenu
    private var menuHelper: MenuPopupHelper? = null
    private var onMenuItemClickListener: PopupMenu.OnMenuItemClickListener? =
        null
    private var showIcons = true
    private var view: View
    private var onDismissListener: PopupWindow.OnDismissListener? = null
    private val menuItems =
        ArrayList<MenuItem>()

    @ColorRes
    private var iconsColorRes = R.color.black

    @StyleRes
    var themeResId = -1

    /** */
    constructor(view: View) {
        this.view = view
        popup = PopupMenu(view.context, view)
    }

    constructor(view: View, @StyleRes themeResId: Int) {
        this.view = view
        this.themeResId = themeResId
        val wrapper: Context =
            ContextThemeWrapper(view.context, themeResId)
        popup = PopupMenu(wrapper, view)
    }

    /** */
    constructor(view: View, @ColorRes iconsColorRes: Int, @StyleRes themeResId: Int) {
        this.view = view
        this.iconsColorRes = iconsColorRes
        popup = PopupMenu(view.context, view)
    }

    /** */
    fun usingMenuRes(@MenuRes menuId: Int): GeneralPopupMenu {
        popup.menuInflater.inflate(menuId, popup.menu)
        return this
    }

    /** */
    fun addItem(itemTitle: String?, @DrawableRes iconRes: Int): GeneralPopupMenu {
        val menuItem =
            popup.menu.add(1, menuItems.size, menuItems.size, itemTitle)
        menuItems.add(menuItem)
        return this
    }

    fun addItem(
        itemTitle: String?,
        @DrawableRes iconRes: Int,
        @ColorRes colorRes: Int
    ): GeneralPopupMenu {
        val menuItem =
            popup.menu.add(1, menuItems.size, menuItems.size, itemTitle)
        menuItem.icon = getTintedDrawable(
            context = view.context,
            drawableResId = iconRes,
            colorResId = colorRes
        )
        menuItems.add(menuItem)
        return this
    }

    /**/
    fun addItem(itemTitle: String?): GeneralPopupMenu {
        val menuItem =
            popup.menu.add(1, menuItems.size, menuItems.size, itemTitle)
        menuItems.add(menuItem)
        return this
    }

    /**/ /**/
    fun addItems(items: List<String?>): GeneralPopupMenu {
        popup = PopupMenu(view.context, view)
        for (item in items) {
            addItem(item)
        }
        return this
    }

    /**/
    fun addItems(vararg items: String?): GeneralPopupMenu {
        popup = PopupMenu(view.context, view)
        for (item in items) {
            addItem(item)
        }
        return this
    }

    /**/
    @SuppressLint("RestrictedApi")
    fun show(): GeneralPopupMenu {
        popup.setOnMenuItemClickListener(onMenuItemClickListener)
        /**/menuHelper = if (themeResId != -1 && themeResId != 0) {
            val wrapper: Context = ContextThemeWrapper(view.context, themeResId)
            MenuPopupHelper(wrapper, (popup.menu as MenuBuilder), view)
        } else MenuPopupHelper(view.context, (popup.menu as MenuBuilder), view)
        /**/menuHelper?.setOnDismissListener(onDismissListener)
        menuHelper?.setForceShowIcon(showIcons)
        menuHelper?.show()
        return this
    }

    fun withIconsVisible(iconsVisible: Boolean): GeneralPopupMenu {
        showIcons = iconsVisible
        return this
    }

    fun setOnDismissListener(onDismissListener: PopupWindow.OnDismissListener?): GeneralPopupMenu {
        this.onDismissListener = onDismissListener
        return this
    }

    @SuppressLint("RestrictedApi")
    fun withItems(iconsVisible: Boolean): GeneralPopupMenu {
        menuHelper?.setForceShowIcon(iconsVisible)
        return this
    }

    fun withItemsClickListener(onMenuItemClickListener: PopupMenu.OnMenuItemClickListener?): GeneralPopupMenu {
        this.onMenuItemClickListener = onMenuItemClickListener
        return this
    }
}