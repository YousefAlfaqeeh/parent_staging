package trackwareschoolbus.parentschool.api_v3

import android.app.Activity
import android.graphics.drawable.Drawable
import android.os.Parcelable
import android.view.View
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.google.gson.annotations.SerializedName
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.parcel.Parcelize
import trackwareschoolbus.parentschool.R
import trackwareschoolbus.parentschool.interfaceParent.OnActionDoneListener


@Parcelize
data class SchoolsListItem(
    @SerializedName("name_en")
    var nameEn: String = "",
    @SerializedName("name_ar")
    var nameAr: String = "",
    @SerializedName("db_name")
    var dbName: String = "",
    var url: String = "",
    var image: String = ""
) : Parcelable


@Parcelize
data class LogInReq(
    @SerializedName("user_name")
    var userName: String = "",
    @SerializedName("password")
    var password: String = "",
    @SerializedName("platform")
    var platform: String = "",
    @SerializedName("school_name")
    var schoolName: String = "",
    @SerializedName("mobile_token")
    var mobile_token: String = ""
) : Parcelable


@Parcelize
data class LogInRes(
    var status: String = "",
    @SerializedName("notifications_text")
    var notificationsText: List<NotificationsText> = listOf(),
    var uid: Int = 0,
    @SerializedName("session_id")
    var sessionId: String = "",
    @SerializedName("web_base_url")
    var webBaseUrl: String = "",
    @SerializedName("Authorization")
    var authorization: String = ""
) : Parcelable {
    @Parcelize
    data class NotificationsText(
        var type: String = "",
        var actions: List<Action> = listOf()
    ) : Parcelable {
        @Parcelize
        data class Action(
            @SerializedName("no-show")
            var noShow: String = "",
            @SerializedName("check_in")
            var checkIn: String = "",
            @SerializedName("check_out")
            var checkOut: String = "",
            var absent: String = "",
            @SerializedName("near_by")
            var nearBy: String = ""
        ) : Parcelable
    }
}


@Parcelize
data class GetKidsResp(
    var message: String = "",
    var students: ArrayList<Student> = ArrayList(),
    @SerializedName("parent_id")
    var parentId: Int = 0
) : Parcelable {
    @Parcelize
    data class Student(
        private var name: String = "",
        private var name_ar: String = "",
        var id: Int = 0,
        @SerializedName("user_id")
        var userId: Int = 0,
        var avatar: String = "",
        @SerializedName("school_id")
        var schoolId: Int? = 0,
        @SerializedName("student_grade")
        var studentGrade: String = "",
        @SerializedName("drop_off_by_parent")
        var dropOffByParent: Boolean = false,
        @SerializedName("pickup_by_parent")
        var pickupByParent: Boolean = false,
        @SerializedName("father_id")
        var fatherId: Int = 0,
        @SerializedName("mother_id")
        var motherId: Int = 0,
        @SerializedName("other_1")
        var other1: Int = 0,
        @SerializedName("other_2")
        var other2: Int = 0,
        @SerializedName("school_name")
        var schoolName: String = "",
        @SerializedName("school_mobile_number")
        var schoolMobileNumber: String = "",
        @SerializedName("school_lat")
        var schoolLat: String = "0.0",
        @SerializedName("school_lng")
        var schoolLng: String = "0.0",
        @SerializedName("driver_mobile_number")
        var driverMobileNumber: String = "",
        @SerializedName("driver_mobile_token")
        var driverMobileToken: String = "",
        @SerializedName("driver_name")
        var driverName: String = "",
        @SerializedName("assistant_name")
        var assistantName: String = "",
        @SerializedName("assistant_mobile_number")
        var assistantMobileNumber: String = "",
        @SerializedName("bus_id")
        var busId: Int = 0,
        @SerializedName("round_type")
        var roundType: String = "",
        @SerializedName("is_active")
        var isActive: Boolean = false,
        @SerializedName("round_name")
        var roundName: String = "",
        @SerializedName("round_id")
        var roundId: Int = 0,
        @SerializedName("assistant_id")
        var assistantId: Int = 0,
        @SerializedName("route_order")
        var routeOrder: Int = 0,
        @SerializedName("chat_teachers")
        var chatTeachers: Boolean = false,
        @SerializedName("target_lng")
        var targetLng: String = "0.0",
        @SerializedName("target_lat")
        var targetLat: String = "0.0",
        @SerializedName("license_state")
        var licenseState: String = "",
        @SerializedName("trial_days_left")
        var trialDaysLeft: Int = 0,
        @SerializedName("license_days_left")
        var licenseDaysLeft: Int = 0,
        @SerializedName("semester_start_date")
        var semesterStartDate: String = "",
        @SerializedName("semester_end_date")
        var semesterEndDate: String = "",
        @SerializedName("show_add_bus_card")
        var showAddBusCard: Boolean = false,
        @SerializedName("allow_upload_students_images")
        var allowUploadStudentsImages: Boolean = false,
        @SerializedName("show_map")
        var showMap: Boolean = false,
        @SerializedName("change_location")
        var changeLocation: Boolean = false,
//        @SerializedName("pickup_request_distance")
        var pickupRequestDistance: Int = 0,
        @SerializedName("student_status")
        var studentStatus: StudentStatus = StudentStatus(),
        @SerializedName("mobile_numbers")
        var mobileNumbers: List<MobileNumber> = listOf(),
        @SerializedName("features")
        var features: List<FeaturesListItem> = listOf(),
        var sessionId: String = "",
        var schoolsListItem: SchoolsListItem? = null,
        @SerializedName("show_absence")
        var show_absence: Boolean = false,
        @SerializedName("show_pickup_request")
        var show_pickup_request: Boolean = false,
    ) : Parcelable {

        fun setName(name_: String){
            name=name_
        }
        fun getName_(): String {
            var retName = ""
            if (SharedPreferencesHelperV3.getCurrentAppLanguage().equals("en"))
                retName= name
            else
                retName= name_ar

            if (name_ar.isNullOrEmpty()){
                retName = name
            }

           return retName
        }





        fun fullAvatar() =
            GlideUrl(avatar) {
                mapOf(Pair("X-Openerp-Session-Id", sessionId))
            }


//        GlideUrl(
//            "https://trackware-schools.s3.eu-central-1.amazonaws.com/web/image/student.student/" + id + "/image_medium/250x120",
//            LazyHeaders.Builder()
//                .addHeader("X-Openerp-Session-Id", BaseActivity.logInResData.sessionId)
//                .build()
//        )


        fun getschoolLatD() = schoolLat.toDoubleOrNull() ?: 0.0
        fun getschoolLngD() = schoolLat.toDoubleOrNull() ?: 0.0
        fun gettargetLatD() = schoolLat.toDoubleOrNull() ?: 0.0
        fun gettargetLngD() = schoolLat.toDoubleOrNull() ?: 0.0


        fun getFirstname(): String {
            var firstName = "";
            try {
                firstName = getName_().split(" ")[0].trim();
                if (firstName.isEmpty()) {// in case firstName is empty
                    firstName = getName_();
                }
            } catch (e: Exception) {
                e.printStackTrace();

            }
            return firstName;
        }


        fun loadImageInToImageView(
            activity: Activity?,
            kidPointMarker: View,
            onMarkerDone: OnActionDoneListener<View>?
        ) {
            val errorImage: Int = R.drawable.img_student
            val imgStudent =
                kidPointMarker.findViewById<View>(R.id.imgMarkerStudent) as CircleImageView
            Glide.with(activity!!).load(avatar)
                .apply(RequestOptions().error(errorImage).fitCenter())
                .listener(object : RequestListener<Drawable?> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any,
                        target: Target<Drawable?>,
                        isFirstResource: Boolean
                    ): Boolean {
                        val labStudentNaame =
                            kidPointMarker.findViewById<TextView>(R.id.labRoutNumberStudent)
                        labStudentNaame.setText(getName_())
                        onMarkerDone?.OnActionDone(kidPointMarker)
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any,
                        target: Target<Drawable?>,
                        dataSource: DataSource,
                        isFirstResource: Boolean
                    ): Boolean {
                        val labStudentNaame =
                            kidPointMarker.findViewById<TextView>(R.id.labRoutNumberStudent)
                        labStudentNaame.text = getName_()
                        imgStudent.setImageDrawable(resource)
                        onMarkerDone?.OnActionDone(kidPointMarker)
                        return false
                    }
                }).into(imgStudent)
        }

        @Parcelize
        data class FeaturesListItem(
            var name: String = "",
            @SerializedName("name_ar")
            var nameAr: String = "",
            var icon: String = "",
           private var url: String = "",
           private var arabic_url: String = ""
        ) : Parcelable {
            fun getUrlWithLang() = run {
                if (SharedPreferencesHelperV3.getCurrentAppLanguage().equals("ar")) {
                    arabic_url
                } else {
                    url
                }
            }

//            fun getUrlWithLangAndDB(dbName: String?) = run {
//                getUrlWithLang().plus("?db=$dbName")
//            }
        }

        @Parcelize
        data class StudentStatus(
            @SerializedName("activity_type")
            var activityType: String = "",
            @SerializedName("round_id")
            var roundId: Int = 0,
            var datetime: String = ""
        ) : Parcelable

        @Parcelize
        data class MobileNumber(
            var mobile: String = "",
            var name: String = "",
            var type: String = ""
        ) : Parcelable
    }


}

@Parcelize
data class SchoolDataHolder(
    var kidsResp: GetKidsResp? = null,
    var logInRes: LogInRes? = null,
    var school_db_name: String = "",
    var sessionId: String = "",
    var uName: String = "",
    var pWord: String = "",
    var authorization: String = "",
    var parentId: Int = 0

) : Parcelable {
    fun getKidsList(): ArrayList<GetKidsResp.Student>? {
        kidsResp?.students?.forEach {
            it.sessionId = sessionId
        }
        return@getKidsList kidsResp?.students
    }

}