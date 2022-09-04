package trackwareschoolbus.parentschool.basePage

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.BroadcastReceiver
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.afollestad.materialdialogs.MaterialDialog
import trackwareschoolbus.parentschool.API.ApiFacade
import trackwareschoolbus.parentschool.API.ApiRequest
import trackwareschoolbus.parentschool.API.enumApi.EnumMethodApi
import trackwareschoolbus.parentschool.API.enumApi.EnumNameApi
import trackwareschoolbus.parentschool.API.enumApi.EnumTypeHeader
import trackwareschoolbus.parentschool.API.interfaceApi.IRestCallBack
import trackwareschoolbus.parentschool.MainFragmentActivity
import trackwareschoolbus.parentschool.R
import trackwareschoolbus.parentschool.dataBase.OpenHelper
import trackwareschoolbus.parentschool.interfaceParent.OnActionDoneListener
import trackwareschoolbus.parentschool.toolsV2.DialogsTools
import trackwareschoolbus.parentschool.toolsV2.DialogsTools.CheckPinDialog
import trackwareschoolbus.parentschool.toolsV2.DialogsTools.ProcessDialog
import trackwareschoolbus.parentschool.toolsV2.ViewToolsV2
import trackwareschoolbus.parentschool.utilityParent.UtilityParent
import java.util.*

abstract class BaseFragment : Fragment() {

    private var sqlitedatabase: SQLiteDatabase? = null
    protected var toolBarHome: View? = null
    protected var toolBarNotification: View? = null
    protected var toolBarback: View? = null
    protected var toolBarSettings: View? = null
    protected var toolBarMainView: View? = null
    private var toolBarTitle: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        try {
            sqlitedatabase = OpenHelper.getDatabase(requireActivity().applicationContext)
            super.onCreate(savedInstanceState)
        } catch (e: Exception) {

        }

    }

    //    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
    protected fun callRestAPI(
        PATH_URL: String?,
        params: HashMap<*, *>?,
        verb: EnumMethodApi?,
        restCallBack: IRestCallBack?,
        enumNameApi: EnumNameApi?,
        mapHeader: Map<String?, String?>?,
        enumTypeHeader: EnumTypeHeader?
    ) {
        try {
            val callApi = ApiFacade()
            callApi.onStartVolley(
                ApiRequest(
                    PATH_URL,
                    params,
                    verb,
                    restCallBack,
                    enumNameApi,
                    mapHeader
                ),
                enumTypeHeader
            )
        }catch (e:Exception){

        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        hideKeyboardOnClickingOutside(view)
        initToolBarButtons(view)
    }

    val database: SQLiteDatabase?
        get() {
            if (sqlitedatabase == null) sqlitedatabase =
                OpenHelper.getDatabase(requireActivity().applicationContext)
            return sqlitedatabase
        }

//    fun registerMessageReceiver(messageBroadcastReceiver: BroadcastReceiver?) {
//        (activity as MainFragmentActivity?)?.registerMessageReceiver(messageBroadcastReceiver)
//    }

    override fun onStop() {
        super.onStop()
//        (activity as MainFragmentActivity?)?.unRegisterMessageReceiver()
    }

    protected val myId: Int
        protected get() = try {
            UtilityParent.getIntShared(UtilityParent.PARENT_ID)
        } catch (e: Exception) {
            -1
        }

    protected fun initToolBarButtons(rootView: View) {
        try {
            toolBarHome = rootView.findViewById(R.id.toolBarHome)
            toolBarNotification = rootView.findViewById(R.id.toolBarNotification)
            toolBarback = rootView.findViewById(R.id.toolBarback)
            toolBarSettings = rootView.findViewById(R.id.toolBarSettings)
            toolBarTitle = rootView.findViewById(R.id.toolBarTitle)
            toolBarMainView = rootView.findViewById(R.id.toolBarMainView)
            /////////////
            if (toolBarHome != null) {
                toolBarHome?.setOnClickListener { v: View? -> MainFragmentActivity.showMyKidsFragment() }
                if (showToolBarHome()) {
                    toolBarHome?.visibility = View.VISIBLE
                } else {
                    toolBarHome?.visibility = View.GONE
                }
            }
            if (toolBarNotification != null) {
                toolBarNotification?.setOnClickListener { v: View? -> MainFragmentActivity.showNotificationFragment() }
                if (showToolBarNotification()) {
                    toolBarNotification?.visibility = View.VISIBLE
                } else {
                    toolBarNotification?.visibility = View.INVISIBLE
                }
            }
            if (toolBarback != null) {
                if (showToolBack()) {
                    toolBarback?.visibility = View.VISIBLE
                } else {
                    toolBarback?.visibility = View.INVISIBLE
                }
                toolBarback?.setOnClickListener { v: View? -> getMainActivity()?.onBackPressed() }
            }
            if (toolBarSettings != null) {
                toolBarSettings?.setOnClickListener { v: View? -> MainFragmentActivity.showSettingFragment() }
                if (showToolBarSettings()) {
                    toolBarSettings?.visibility = View.VISIBLE
                } else {
                    toolBarSettings?.visibility = View.INVISIBLE
                }
            }


            //////////////

            //////////////

            //////////////
            if (toolBarMainView != null) {
                if (showToolBarView()) {
                    toolBarMainView?.visibility = View.VISIBLE
                } else {
                    toolBarMainView?.visibility = View.GONE
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        try {
            toolBarTitle?.text = initToolBarTitle()

//            new Handler().postDelayed(() -> {
//                toolBarTitle.setText(showToolBarTitle());
//            }, 1000);
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    //    protected void checkNotificationCount(View v) {
    //        try {
    //            if (v.findViewById(R.id.imgNotification_count) != null) {
    //                int notCount = DAO.haseNotification(OpenHelper.getDatabase(getActivity()));
    //                if (notCount > 0) {
    //                    ((AppCompatButton) v.findViewById(R.id.imgNotification_count)).setVisibility(View.VISIBLE);
    //                    ((AppCompatButton) v.findViewById(R.id.imgNotification_count)).setText(notCount + "");
    //                } else {
    //                    ((AppCompatButton) v.findViewById(R.id.imgNotification_count)).setVisibility(View.GONE);
    //                }
    //            }
    //
    //        } catch (Exception e) {
    //            e.printStackTrace();
    //        }
    //
    //
    //    }
    protected fun goneByFade(v: View) {
        v.animate().setDuration(400).alpha(0f).setListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                v.visibility = View.GONE
            }
        })
    }

    protected fun visibleByFade(v: View) {
        v.animate().setDuration(400).alpha(1f).setListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                v.visibility = View.VISIBLE
            }
        })
    }

    override fun onResume() {
        super.onResume()
        getMainActivity().checkImportantMessages()
        //        getMainActivity().checkNotificationMesseges();
//        getMainActivity().checkForConfirmPickup();
    }

    fun getMainActivity() = requireActivity() as MainFragmentActivity

    //    public void gainAllPermissions(PermissionsHandlerActivity.PermissionsLisiner afterPermissionsDone) {
    //        getMainActivity().gainAllPermissions(afterPermissionsDone);
    //    }
    //    public void gainPermission(PermissionsHandlerActivity.PermissionsLisiner afterPermissionsDone, String... permissions) {
    //        getMainActivity().gainPermission(afterPermissionsDone, permissions);
    //    }
    fun hideKeyboardOnClickingOutside(view: View) {
        try {
            // Set up touch listener for non-text box views to hide keyboard.
            if (view !is EditText) {
                view.setOnTouchListener { v, event ->
                    ViewToolsV2.hideKeyboard(activity)
                    false
                }
            }

            //If a layout container, iterate over children and seed recursion.
            if (view is ViewGroup) {
                for (i in 0 until view.childCount) {
                    val innerView = view.getChildAt(i)
                    hideKeyboardOnClickingOutside(innerView)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    var checkPinDialog: CheckPinDialog? = null;
    fun checkPin(afterCheck: OnActionDoneListener<Boolean?>?) {
        if (checkPinDialog == null) {
            checkPinDialog = CheckPinDialog()
        }else {
            try {
                checkPinDialog?.dismiss()
            }catch (e:Exception){

            }
        }
        checkPinDialog?.show(getMainActivity())?.setOnPinSubmit(afterCheck)
    }

    private var processDialog: MaterialDialog? = null
    fun showLoading() {
        try {
            if (processDialog == null) processDialog = ProcessDialog.show(getMainActivity())
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun showError() {
        try {
            DialogsTools.showGeneralErrorDialog(activity, null).show(activity)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun showError(message: String?) {
        try {
            DialogsTools.showGeneralErrorDialog(activity, message).show(activity)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun hideLoading() {
        try {
            if (processDialog != null) {
                processDialog?.cancel()
                processDialog = null
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onDestroy() {
        hideLoading()
        super.onDestroy()
    }

    protected fun showToolBarHome(): Boolean {
        return false
    }

    protected abstract fun showToolBarNotification(): Boolean
    protected abstract fun showToolBack(): Boolean
    protected abstract fun showToolBarSettings(): Boolean
    protected open fun showToolBarView(): Boolean {
        return true
    }

    protected open fun initToolBarTitle(): String? {
        return ""
    } //    public void callKidsListAPI() {
    //        swipeRefreshLayout.setRefreshing(true);
    //        ConnectionFactory.getInstance().getKidsListForAllKids()
    //                .observe(getViewLifecycleOwner(), new Observer<ApiResponse<GetKidsResp>>() {
    //                    @Override
    //                    public void onChanged(ApiResponse<GetKidsResp> response) {
    //
    //                        try {
    //                            if (response instanceof ApiSuccessResponse){
    //                                ArrayList<GetKidsResp.Student> students = ((ApiSuccessResponse<GetKidsResp>) response).getBody().getStudents();
    //                                addKidsList(students);
    //                            }else {
    //
    //                            }
    //                        }catch (Exception e){
    //
    //                        }
    //                    }
    //                });
    //    }
}