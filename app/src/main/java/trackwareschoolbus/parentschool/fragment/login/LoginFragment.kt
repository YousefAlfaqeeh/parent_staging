package trackwareschoolbus.parentschool.fragment.login


import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.android.gms.tasks.Task
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.iid.InstanceIdResult
import me.linshen.retrofit2.adapter.ApiSuccessResponse
import trackwareschoolbus.parentschool.API_V2.ConnectionFactory
import trackwareschoolbus.parentschool.MainFragmentActivity
import trackwareschoolbus.parentschool.R
import trackwareschoolbus.parentschool.api_v3.*
import trackwareschoolbus.parentschool.api_v3.GetKidsResp.Student
import trackwareschoolbus.parentschool.basePage.BaseFragment
import trackwareschoolbus.parentschool.toolsV2.GeneralPopupMenu
import trackwareschoolbus.parentschool.utilityParent.StaticValue
import trackwareschoolbus.parentschool.utilityParent.UtilityParent
import java.util.*
import java.util.function.Consumer
import kotlinx.android.synthetic.main.fragment_login_v2.*

class LoginFragment : BaseFragment() {
    var btnLogIn: Button? = null
    var mActivity: Activity? =null
    var btnSelectLanguge: Button? = null
//    var btnSelectLanguge: View? = null
    var school_list_layout: View? = null
    var txtPin: EditText? = null
    var txtpass: EditText? = null
    var school_list: TextView? = null
    var txtPrivacyPolicy: TextView? = null

    //    var mActivity: Activity? = null

    private val schoolsListItems = HashSet<SchoolsListItem>()
    private val schoolsListItemsWithoutSelected = HashSet<SchoolsListItem>()

    private val schoolDataHolder = SchoolDataHolder()
    private var selectedSchool: SchoolsListItem? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_login_v2, container, false)
//        val btnSelectLanguge: Button = view.findViewById(R.id.btnSelectLanguge)
//        button.text = "Submit"
//        mLogInPresenter = new LogInPresenter(mActivity);
//             testAdapter();
        StaticValue.loginFragment = this@LoginFragment
        mActivity = getActivity()
        // txtParentCode=(EditText) view.findViewById(R.id.txtParent_Code);
        txtPin = view.findViewById(R.id.txtPin)
        btnSelectLanguge =view.findViewById(R.id.btnSelectLanguge)
        txtpass = view.findViewById(R.id.txtpass)
        school_list = view.findViewById(R.id.school_list)
        school_list_layout = view.findViewById(R.id.school_list_layout)
        txtPrivacyPolicy = view.findViewById(R.id.txtPrivacyPolicy)
        txtPrivacyPolicy?.setMovementMethod(LinkMovementMethod.getInstance())



        btnLogIn = view.findViewById(R.id.btnLogIn)
        btnLogIn?.setOnClickListener {callLogIn() }
        if (Locale.getDefault().getDisplayLanguage().equals("English")) {
            btnSelectLanguge?.setText(mActivity!!.getString(R.string.ar));


        } else {
            btnSelectLanguge?.setText(mActivity!!.getString(R.string.en))
        }
        btnSelectLanguge?.setOnClickListener {

            if (UtilityParent.getStringShared(UtilityParent.LANGUAGE).equals("ar")) {
                UtilityParent.setStringShared(UtilityParent.LANGUAGE, "en")
                UtilityParent.setStringShared(UtilityParent.LANGUAGE_SELECTED, "en")
                UtilityParent.setLocale("en", this.requireActivity().getApplication())
                val intent = Intent(requireActivity(), MainFragmentActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                startActivity(intent)

        } else {





                UtilityParent.setStringShared(UtilityParent.LANGUAGE, "ar")
                UtilityParent.setStringShared(UtilityParent.LANGUAGE_SELECTED, "ar")
                UtilityParent.setLocale("ar", this.requireActivity().getApplication())
                val intent = Intent(requireActivity(), MainFragmentActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                startActivity(intent)


        }}

//        txtPin.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//                if(hasFocus){
//                   txtPin.setBackgroundResource(R.drawable.edittext_background_whith_border);
//                    logInhasFocus=true;
//                }else {
//                    txtPin.setBackgroundResource(R.drawable.edittext_background_whith_out_border);
//                }
//            }
//        });

//        btnSelectLanguge = view.findViewById(R.id.btnSelectLanguge);
//        if (UtilityParent.getStringShared(UtilityParent.LANGUAGE).equals("ar")){
//            btnSelectLanguge.setText(mActivity.getString(R.string.en));
//        }else{
//            btnSelectLanguge.setText(mActivity.getString(R.string.ar));
//        }
//        btnSelectLanguge.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (UtilityParent.getStringShared(UtilityParent.LANGUAGE).equals("ar")) {
//                    UtilityParent.setStringShared(UtilityParent.LANGUAGE, "en");
//                    UtilityParent.setLocale("en", mActivity, MainFragmentActivity.class);
//                } else {
//                    UtilityParent.setStringShared(UtilityParent.LANGUAGE, "ar");
//                    UtilityParent.setLocale("ar", mActivity, MainFragmentActivity.class);
//                }
//
//
//            }
//        });

//        if (UtilityParent.getStringShared(UtilityParent.TUTORIAL_DONE).equals("")) {
//            mActivity.startActivity(new Intent(mActivity, TutorialActivity.class));
//        }
        school_list?.setOnClickListener { showSchoolsList() }
        val stringShared = UtilityParent.getStringShared(UtilityParent.FIREBASE_TOKEN)
        if (stringShared == null || stringShared.isEmpty()) {
            firebaseToken
        } else {
            callGetSchools()
        }
        if (UtilityParent.getSavedlDataHolders().size < 0) {
            toolBarback?.setOnClickListener { requireActivity().finish() }
        }
        return view
    }

    override fun onResume() {
        super.onResume()

        getMainActivity().refreshFireBase()
        view?.isFocusableInTouchMode = true
        view?.requestFocus()
        if (UtilityParent.getSavedlDataHolders().size < 0) {
            view?.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
                if (event.action == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
//                    Toast.makeText(getActivity(),"efsf",Toast.LENGTH_LONG).show();
                    requireActivity().finish()
                    return@OnKeyListener true
                }
                false
            })
        }
    }

    override fun showToolBarNotification(): Boolean {
        return false
    }

    override fun showToolBack(): Boolean {
        return UtilityParent.getSavedlDataHolders().size > 0
    }

    override fun showToolBarSettings(): Boolean {
        return false
    }

    override fun showToolBarView(): Boolean {
        return UtilityParent.getSavedlDataHolders().size > 0
    }

    override fun initToolBarTitle(): String {
        return getString(R.string.login_add_children)
    }

    override fun onPause() {
        super.onPause()
        StaticValue.loginFragment = null
    }

    override fun onStop() {
        super.onStop()


        /*  getView().setFocusableInTouchMode(true);
             getView().requestFocus();
            getView().setOnKeyListener(new View.OnKeyListener() {
                @Override
                public boolean onKey(View v, int keyCode, KeyEvent event) {
                    if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                        Toast.makeText(getActivity(),"efsf",Toast.LENGTH_LONG).show();
                        System.exit(0);
                        return true;
                    }
                    return false;
                }
            });*/
    }

    var firebase_token = ""

    ////////////////
    val firebaseToken: Unit
        get() {
            FirebaseInstanceId.getInstance().instanceId
                .addOnCompleteListener { task: Task<InstanceIdResult> ->
                    try {
                        if (!task.isSuccessful) {
                            Log.v("FirebaseInstanceId", "getInstanceId failed", task.exception)
                            return@addOnCompleteListener
                        }

                        ////////////////
                        val token = task.result?.token
                        if (!token.isNullOrEmpty()) {
                            UtilityParent.setStringShared(UtilityParent.FIREBASE_TOKEN, token)
                            firebase_token = token
                        } else {
                            firebase_token = UtilityParent.getStringShared(UtilityParent.FIREBASE_TOKEN)
                        }
                        callGetSchools()
                    } catch (e: Exception) {
                    }
                }
        }

    fun callGetSchools() {
        showLoading()
        ConnectionFactory.getInstance().schoolsList()
            .observe(viewLifecycleOwner, { response ->
                hideLoading()
                try {
                    val schoolsListItemArrayList =
                        (response as ApiSuccessResponse<ArrayList<SchoolsListItem>?>).body
                    schoolsListItems.clear()
                    schoolsListItemsWithoutSelected.clear()
                    schoolsListItems.addAll(schoolsListItemArrayList!!)
                    if (UtilityParent.getSavedlDataHolders().size > 0) {
                        for (i in schoolsListItems.indices) {
                            for (j in UtilityParent.getSavedlDataHolders().indices) {
                                if (UtilityParent.getSavedlDataHolders()[j].school_db_name != schoolsListItems.toList().get(i).dbName) {
                                    schoolsListItemsWithoutSelected.add(schoolsListItems.toList().get(i))
                                }
                            }
                        }
                    } else {
                        schoolsListItemsWithoutSelected.addAll(schoolsListItems)
                    }
                } catch (e: Exception) {
                    showError()
                }
            })
    }

    fun showSchoolsList() {
        if(Locale.getDefault().getDisplayLanguage().equals("English"))
        {
            school_list?.let {school_list->
                val popupMenu = GeneralPopupMenu(school_list, R.style.NormalPopupMenu)
                for (i in schoolsListItemsWithoutSelected.indices) {
                    val name = schoolsListItemsWithoutSelected.toList().get(i).nameEn
                    popupMenu.addItem(name)
                }
                popupMenu.withItemsClickListener { item ->
                    try {
                        school_list.text = schoolsListItemsWithoutSelected.toList().get(item.itemId).nameEn
                        val schoolsListItem = schoolsListItemsWithoutSelected.toList().get(item.itemId)
                        selectedSchool = schoolsListItem
                    } catch (e: Exception) {
                    }
                    true
                }
                popupMenu.show()
            }
        }else{
        school_list?.let {school_list->
            val popupMenu = GeneralPopupMenu(school_list, R.style.NormalPopupMenu)
            for (i in schoolsListItemsWithoutSelected.indices) {
                val name = schoolsListItemsWithoutSelected.toList().get(i).nameAr
                popupMenu.addItem(name)
            }
            popupMenu.withItemsClickListener { item ->
                try {
                    school_list.text = schoolsListItemsWithoutSelected.toList().get(item.itemId).nameAr
                    val schoolsListItem = schoolsListItemsWithoutSelected.toList().get(item.itemId)
                    selectedSchool = schoolsListItem
                } catch (e: Exception) {
                }
                true
            }
            popupMenu.show()
        }}

    }

    fun callLogIn() {
        /////////

        if (txtPin!!.text.toString().isEmpty() ||
            txtpass!!.text.toString().isEmpty() || selectedSchool == null
        ) {
            UtilityParent.shakeViews(btnLogIn)
            UtilityParent.shakeViews(txtPin)
            UtilityParent.shakeViews(txtpass)
            //            showError("Please fill");
            return
        }

        ///////////
        val user_name = txtPin?.text.toString().trim { it <= ' ' }
        val password = txtpass?.text.toString().trim { it <= ' ' }
        val platform = "android"
        val school_name = selectedSchool!!.dbName
        showLoading()
        val logInReq = LogInReq()
        logInReq.userName = user_name
        logInReq.password = password
        logInReq.platform = platform
        logInReq.schoolName = school_name
        logInReq.mobile_token = firebase_token
        if (logInReq.mobile_token.equals(""))
        {
            logInReq.mobile_token =FirebaseInstanceId.getInstance().token.toString()

        }
        val refreshedToken = FirebaseInstanceId.getInstance().token
        Log.d("aaaaaaaaaaaaaaaaaaaaaaaaaaaa111", FirebaseInstanceId.getInstance().token.toString())
        Log.d("aaaaaaaaaaaaaaaaaaaaaaaaaaaa",firebase_token)

        ConnectionFactory.getInstance().logIn(logInReq)
            .observe(viewLifecycleOwner, { response ->
                try {
                    val logInRes = (response as ApiSuccessResponse<LogInRes>).body
                    schoolDataHolder.logInRes = logInRes
                    schoolDataHolder.school_db_name = selectedSchool!!.dbName
                    schoolDataHolder.sessionId = logInRes.sessionId
                    schoolDataHolder.authorization = logInRes.authorization
                    UtilityParent.setStringShared(UtilityParent.AUTH, logInRes.authorization)
                    UtilityParent.setBooleanShared(UtilityParent.ADDED_STUEDENTS, true)
                    callKidsListAPI()
                } catch (e: Exception) {
                    hideLoading()
                    showError()
                }
            })
    }

    fun callKidsListAPI() {
        ConnectionFactory.getInstance().kidsListForAllKids
            .observe(viewLifecycleOwner, { response ->
                hideLoading()
                try {
                    if ((response as ApiSuccessResponse<GetKidsResp>).body !=null && (response as ApiSuccessResponse<GetKidsResp>).body.students!=null && (response as ApiSuccessResponse<GetKidsResp>).body.students.size>0){
                        val students = (response as ApiSuccessResponse<GetKidsResp>).body
                        schoolDataHolder.kidsResp = students
                        students.students.forEach(Consumer { student: Student ->
                            student.schoolsListItem = selectedSchool
                        })
                        schoolDataHolder.parentId = students.parentId
                        UtilityParent.getSavedlDataHolders().add(schoolDataHolder)
                        UtilityParent.saveCurrentHolders()
                        MainFragmentActivity.showMyKidsFragment()
                    }else{
                        showError()
                    }

                } catch (e: Exception) {
                    showError()
                }
            })
    }

    companion object {
        var bundle: Bundle? = null

        @JvmField
        var isLogIn = false
    }
}