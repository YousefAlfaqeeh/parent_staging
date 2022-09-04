//package trackwareschoolbus.parentschool.bean;
//
//import android.app.Activity;
//import android.graphics.drawable.Drawable;
//import android.os.Parcel;
//import android.os.Parcelable;
//import androidx.annotation.Nullable;
//import android.view.View;
//import android.widget.TextView;
//
//import com.bumptech.glide.Glide;
//import com.bumptech.glide.load.DataSource;
//import com.bumptech.glide.load.engine.GlideException;
//import com.bumptech.glide.request.RequestListener;
//import com.bumptech.glide.request.RequestOptions;
//import com.bumptech.glide.request.target.Target;
//import com.google.gson.annotations.Expose;
//import com.google.gson.annotations.SerializedName;
//
//import java.io.Console;
//import java.util.List;
//
//import de.hdodenhof.circleimageview.CircleImageView;
//import trackwareschoolbus.parentschool.R;
//import trackwareschoolbus.parentschool.fragment.mykids.request_and_response.MobileNumber;
//import trackwareschoolbus.parentschool.interfaceParent.OnActionDoneListener;
//import trackwareschoolbus.parentschool.screens_v2.home_kids_list_v2.request_and_response.StudentStatus_v2;
//
///**
// * Created by  on 2/23/2017.
// */
//
//public class KidBean implements Parcelable {
//
//    @SerializedName("name")
//    @Expose
//    public String name;
//    @SerializedName("id")
//    @Expose
//    public Integer id;
//    @SerializedName("avatar")
//    @Expose
//    public String avatar;
//    @SerializedName("school_id")
//    @Expose
//    public Integer schoolId;
//    @SerializedName("student_grade")
//    @Expose
//    public String studentGrade;
//    @SerializedName("drop_off_by_parent")
//    @Expose
//    public Boolean dropOffByParent;
//    @SerializedName("pickup_by_parent")
//    @Expose
//    public Boolean pickupByParent;
//    @SerializedName("father_id")
//    @Expose
//    public Integer fatherId;
//    @SerializedName("mother_id")
//    @Expose
//    public Integer motherId;
//    @SerializedName("other_1")
//    @Expose
//    public Integer other1;
//    @SerializedName("other_2")
//    @Expose
//    public Integer other2;
//    @SerializedName("school_name")
//    @Expose
//    public String schoolName;
//    @SerializedName("school_mobile_number")
//    @Expose
//    public String schoolMobileNumber;
//    @SerializedName("school_lat")
//    @Expose
//    public String schoolLat;
//    @SerializedName("school_lng")
//    @Expose
//    public String schoolLng;
//    @SerializedName("driver_mobile_number")
//    @Expose
//    public String driverMobileNumber;
//    @SerializedName("driver_mobile_token")
//    @Expose
//    public String driverMobileToken;
//    @SerializedName("driver_name")
//    @Expose
//    public String driverName;
//    @SerializedName("assistant_name")
//    @Expose
//    public String assistantName;
//    @SerializedName("assistant_mobile_number")
//    @Expose
//    public String assistantMobileNumber;
//    @SerializedName("bus_id")
//    @Expose
//    public Integer busId;
//    @SerializedName("round_type")
//    @Expose
//    public String roundType;
//    @SerializedName("is_active")
//    @Expose
//    public Boolean isActive;
//    @SerializedName("round_name")
//    @Expose
//    public String roundName;
//    @SerializedName("round_id")
//    @Expose
//    public Integer roundId;
//    @SerializedName("assistant_id")
//    @Expose
//    public Integer assistantId;
//    @SerializedName("route_order")
//    @Expose
//    public Integer routeOrder;
//    @SerializedName("chat_teachers")
//    @Expose
//    public Boolean chatTeachers;
//    @SerializedName("target_lng")
//    @Expose
//    public String targetLng;
//    @SerializedName("target_lat")
//    @Expose
//    public String targetLat;
//    @SerializedName("license_state")
//    @Expose
//    public String licenseState;
//    @SerializedName("trial_days_left")
//    @Expose
//    public Integer trialDaysLeft;
//    @SerializedName("license_days_left")
//    @Expose
//    public Integer licenseDaysLeft;
//    @SerializedName("semester_start_date")
//    @Expose
//    public String semesterStartDate;
//    @SerializedName("semester_end_date")
//    @Expose
//    public String semesterEndDate;
//    @SerializedName("show_add_bus_card")
//    @Expose
//    public Boolean showAddBusCard;
//    @SerializedName("show_map")
//    @Expose
//    public String showMap;
//    @SerializedName("change_location")
//    @Expose
//    public String changeLocation;
//    @SerializedName("student_status")
//    @Expose
//    public StudentStatus_v2 studentStatus;
//    @SerializedName("mobile_numbers")
//    @Expose
//    public List<MobileNumber> mobileNumbers = null;
//    @SerializedName("allow_upload_students_images")
//    @Expose
//    public Boolean allowUploadStudentsImages;
//    @SerializedName("pickup_request_distance")
//    @Expose
//    public Integer pickup_request_distance;
//
//    public Boolean getAllowUploadStudentsImages() {
//        return allowUploadStudentsImages == null ? false : allowUploadStudentsImages;
//    }
//
//    public KidBean setAllowUploadStudentsImages(Boolean allowUploadStudentsImages) {
//        this.allowUploadStudentsImages = allowUploadStudentsImages;
//        return this;
//    }
//
//    public KidBean setName(String name) {
//        this.name = name;
//        return this;
//    }
//
//    public KidBean setId(Integer id) {
//        this.id = id;
//        return this;
//    }
//
//    public KidBean setAvatar(String avatar) {
//        this.avatar = avatar;
//        return this;
//    }
//
//    public KidBean setSchoolId(Integer schoolId) {
//        this.schoolId = schoolId;
//        return this;
//    }
//
//    public KidBean setStudentGrade(String studentGrade) {
//        this.studentGrade = studentGrade;
//        return this;
//    }
//
//    public KidBean setDropOffByParent(Boolean dropOffByParent) {
//        this.dropOffByParent = dropOffByParent;
//        return this;
//    }
//
//    public KidBean setPickupByParent(Boolean pickupByParent) {
//        this.pickupByParent = pickupByParent;
//        return this;
//    }
//
//    public KidBean setFatherId(Integer fatherId) {
//        this.fatherId = fatherId;
//        return this;
//    }
//
//    public KidBean setMotherId(Integer motherId) {
//        this.motherId = motherId;
//        return this;
//    }
//
//    public KidBean setOther1(Integer other1) {
//        this.other1 = other1;
//        return this;
//    }
//
//    public KidBean setOther2(Integer other2) {
//        this.other2 = other2;
//        return this;
//    }
//
//    public KidBean setSchoolName(String schoolName) {
//        this.schoolName = schoolName;
//        return this;
//    }
//
//    public KidBean setSchoolMobileNumber(String schoolMobileNumber) {
//        this.schoolMobileNumber = schoolMobileNumber;
//        return this;
//    }
//
//    public KidBean setSchoolLat(String schoolLat) {
//        this.schoolLat = schoolLat;
//        return this;
//    }
//
//    public KidBean setSchoolLng(String schoolLng) {
//        this.schoolLng = schoolLng;
//        return this;
//    }
//
//    public KidBean setDriverMobileNumber(String driverMobileNumber) {
//        this.driverMobileNumber = driverMobileNumber;
//        return this;
//    }
//
//    public KidBean setDriverMobileToken(String driverMobileToken) {
//        this.driverMobileToken = driverMobileToken;
//        return this;
//    }
//
//    public KidBean setDriverName(String driverName) {
//        this.driverName = driverName;
//        return this;
//    }
//
//    public KidBean setAssistantName(String assistantName) {
//        this.assistantName = assistantName;
//        return this;
//    }
//
//    public KidBean setAssistantMobileNumber(String assistantMobileNumber) {
//        this.assistantMobileNumber = assistantMobileNumber;
//        return this;
//    }
//
//    public KidBean setBusId(Integer busId) {
//
//        this.busId = busId;
//        return this;
//    }
//
//    public KidBean setRoundType(String roundType) {
//        this.roundType = roundType;
//        return this;
//    }
//
//    public KidBean setActive(Boolean active) {
//        isActive = active;
//        return this;
//    }
//
//    public KidBean setRoundName(String roundName) {
//        this.roundName = roundName;
//        return this;
//    }
//
//    public KidBean setRoundId(Integer roundId) {
//        this.roundId = roundId;
//        return this;
//    }
//
//    public KidBean setAssistantId(Integer assistantId) {
//        this.assistantId = assistantId;
//        return this;
//    }
//
//    public KidBean setRouteOrder(Integer routeOrder) {
//        this.routeOrder = routeOrder;
//        return this;
//    }
//
//    public KidBean setChatTeachers(Boolean chatTeachers) {
//        this.chatTeachers = chatTeachers;
//        return this;
//    }
//
//    public KidBean setTargetLng(String targetLng) {
//        this.targetLng = targetLng;
//        return this;
//    }
//
//    public KidBean setTargetLat(String targetLat) {
//        this.targetLat = targetLat;
//        return this;
//    }
//
//    public KidBean setLicenseState(String licenseState) {
//        this.licenseState = licenseState;
//        return this;
//    }
//
//    public KidBean setTrialDaysLeft(Integer trialDaysLeft) {
//        this.trialDaysLeft = trialDaysLeft;
//        return this;
//    }
//
//    public KidBean setLicenseDaysLeft(Integer licenseDaysLeft) {
//        this.licenseDaysLeft = licenseDaysLeft;
//        return this;
//    }
//
//    public KidBean setSemesterStartDate(String semesterStartDate) {
//        this.semesterStartDate = semesterStartDate;
//        return this;
//    }
//
//    public KidBean setSemesterEndDate(String semesterEndDate) {
//        this.semesterEndDate = semesterEndDate;
//        return this;
//    }
//
//    public KidBean setShowAddBusCard(Boolean showAddBusCard) {
//        this.showAddBusCard = showAddBusCard;
//        return this;
//    }
//
//    public KidBean setShowMap(String showMap) {
//        this.showMap = showMap;
//        return this;
//    }
//
//    public KidBean setChangeLocation(String changeLocation) {
//        this.changeLocation = changeLocation;
//        return this;
//    }
//
//    public KidBean setStudentStatus(StudentStatus_v2 studentStatus) {
//        this.studentStatus = studentStatus;
//        return this;
//    }
//
//    public KidBean setMobileNumbers(List<MobileNumber> mobileNumbers) {
//        this.mobileNumbers = mobileNumbers;
//        return this;
//    }
//
//    public Integer getPickup_request_distance() {
//        return pickup_request_distance;
//    }
//
//    public KidBean setPickup_request_distance(Integer pickup_request_distance) {
//        this.pickup_request_distance = pickup_request_distance;
//        return this;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public Integer getId() {
//        return id;
//    }
//
//    public String getAvatar() {
//        return avatar;
//    }
//
//    public Integer getSchoolId() {
//        return schoolId;
//    }
//
//    public String getStudentGrade() {
//        return studentGrade;
//    }
//
//    public Boolean getDropOffByParent() {
//        return dropOffByParent;
//    }
//
//    public Boolean getPickupByParent() {
//        return pickupByParent;
//    }
//
//    public Integer getFatherId() {
//        return fatherId;
//    }
//
//    public Integer getMotherId() {
//        return motherId;
//    }
//
//    public Integer getOther1() {
//        return other1;
//    }
//
//    public Integer getOther2() {
//        return other2;
//    }
//
//    public String getSchoolName() {
//        return schoolName;
//    }
//
//    public String getSchoolMobileNumber() {
//        return schoolMobileNumber;
//    }
//
//    public String getSchoolLat() {
//        return schoolLat;
//    }
//
//    public String getSchoolLng() {
//        return schoolLng;
//    }
//
//    public String getDriverMobileNumber() {
//        return driverMobileNumber;
//    }
//
//    public String getDriverMobileToken() {
//        return driverMobileToken;
//    }
//
//    public String getDriverName() {
//        return driverName;
//    }
//
//    public String getAssistantName() {
//        return assistantName;
//    }
//
//    public String getAssistantMobileNumber() {
//        return assistantMobileNumber;
//    }
//
//    public Integer getBusId() {
//        return busId;
//    }
//
//    public String getRoundType() {
//        return roundType;
//    }
//
//    public Boolean getActive() {
//        return isActive;
//    }
//
//    public String getRoundName() {
//        return roundName;
//    }
//
//    public Integer getRoundId() {
//        return roundId;
//    }
//
//    public Integer getAssistantId() {
//        return assistantId;
//    }
//
//    public Integer getRouteOrder() {
//        return routeOrder;
//    }
//
//    public Boolean getChatTeachers() {
//        return chatTeachers;
//    }
//
//    public String getTargetLng() {
//        return targetLng;
//    }
//
//    public String getTargetLat() {
//        return targetLat;
//    }
//
//    public String getLicenseState() {
//        return licenseState;
//    }
//
//    public Integer getTrialDaysLeft() {
//        return trialDaysLeft;
//    }
//
//    public Integer getLicenseDaysLeft() {
//        return licenseDaysLeft;
//    }
//
//    public String getSemesterStartDate() {
//        return semesterStartDate;
//    }
//
//    public String getSemesterEndDate() {
//        return semesterEndDate;
//    }
//
//    public Boolean getShowAddBusCard() {
//        return showAddBusCard;
//    }
//
//    public String getShowMap() {
//        return showMap;
//    }
//
//    public String getChangeLocation() {
//        return changeLocation;
//    }
//
//    public StudentStatus_v2 getStudentStatus() {
//        return studentStatus;
//    }
//
//    public List<MobileNumber> getMobileNumbers() {
//        return mobileNumbers;
//    }
//
//
//    public String getFirstname() {
//        String firstName = "";
//        try {
//            firstName = getName().split(" ")[0].trim();
//            if (firstName.equalsIgnoreCase("")) {// in case firstName is empty
//                firstName = getName();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//
//        }
//        return firstName;
//    }
//
//
//    public void loadImageInToImageView(Activity activity, View kidPointMarker, OnActionDoneListener onMarkerDone) {
//        int errorImage = R.drawable.img_student;
//        final CircleImageView imgStudent = (CircleImageView) kidPointMarker.findViewById(R.id.imgMarkerStudent);
//        Glide.with(activity).load(getAvatar()).apply(new RequestOptions().error(errorImage).fitCenter())
//                .listener(new RequestListener<Drawable>() {
//                    @Override
//                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
//                        final TextView labStudentNaame = kidPointMarker.findViewById(R.id.labRoutNumberStudent);
//                        labStudentNaame.setText(getName());
//                        if (onMarkerDone != null)
//                            onMarkerDone.OnActionDone(kidPointMarker);
//                        return false;
//                    }
//
//                    @Override
//                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
//                        final TextView labStudentNaame = kidPointMarker.findViewById(R.id.labRoutNumberStudent);
//                        labStudentNaame.setText(getName());
//                        imgStudent.setImageDrawable(resource);
//                        if (onMarkerDone != null)
//                            onMarkerDone.OnActionDone(kidPointMarker);
//                        return false;
//                    }
//                }).into(imgStudent);
//
//    }
//
//    @Override
//    public int describeContents() {
//        return 0;
//    }
//
//    @Override
//    public void writeToParcel(Parcel dest, int flags) {
//        dest.writeString(this.name);
//        dest.writeValue(this.id);
//        dest.writeString(this.avatar);
//        dest.writeValue(this.schoolId);
//        dest.writeString(this.studentGrade);
//        dest.writeValue(this.dropOffByParent);
//        dest.writeValue(this.pickupByParent);
//        dest.writeValue(this.fatherId);
//        dest.writeValue(this.motherId);
//        dest.writeValue(this.other1);
//        dest.writeValue(this.other2);
//        dest.writeString(this.schoolName);
//        dest.writeString(this.schoolMobileNumber);
//        dest.writeString(this.schoolLat);
//        dest.writeString(this.schoolLng);
//        dest.writeString(this.driverMobileNumber);
//        dest.writeString(this.driverMobileToken);
//        dest.writeString(this.driverName);
//        dest.writeString(this.assistantName);
//        dest.writeString(this.assistantMobileNumber);
//        dest.writeValue(this.busId);
//        dest.writeString(this.roundType);
//        dest.writeValue(this.isActive);
//        dest.writeString(this.roundName);
//        dest.writeValue(this.roundId);
//        dest.writeValue(this.assistantId);
//        dest.writeValue(this.routeOrder);
//        dest.writeValue(this.chatTeachers);
//        dest.writeString(this.targetLng);
//        dest.writeString(this.targetLat);
//        dest.writeString(this.licenseState);
//        dest.writeValue(this.trialDaysLeft);
//        dest.writeValue(this.licenseDaysLeft);
//        dest.writeString(this.semesterStartDate);
//        dest.writeString(this.semesterEndDate);
//        dest.writeValue(this.showAddBusCard);
//        dest.writeString(this.showMap);
//        dest.writeString(this.changeLocation);
//        dest.writeParcelable(this.studentStatus, flags);
//        dest.writeTypedList(this.mobileNumbers);
//        dest.writeValue(this.allowUploadStudentsImages);
//        dest.writeValue(this.pickup_request_distance);
//
//    }
//
//    public KidBean() {
//    }
//
//    protected KidBean(Parcel in) {
//        this.name = in.readString();
//        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
//        this.avatar = in.readString();
//        this.schoolId = (Integer) in.readValue(Integer.class.getClassLoader());
//        this.studentGrade = in.readString();
//        this.dropOffByParent = (Boolean) in.readValue(Boolean.class.getClassLoader());
//        this.pickupByParent = (Boolean) in.readValue(Boolean.class.getClassLoader());
//        this.fatherId = (Integer) in.readValue(Integer.class.getClassLoader());
//        this.motherId = (Integer) in.readValue(Integer.class.getClassLoader());
//        this.other1 = (Integer) in.readValue(Integer.class.getClassLoader());
//        this.other2 = (Integer) in.readValue(Integer.class.getClassLoader());
//        this.schoolName = in.readString();
//        this.schoolMobileNumber = in.readString();
//        this.schoolLat = in.readString();
//        this.schoolLng = in.readString();
//        this.driverMobileNumber = in.readString();
//        this.driverMobileToken = in.readString();
//        this.driverName = in.readString();
//        this.assistantName = in.readString();
//        this.assistantMobileNumber = in.readString();
//        this.busId = (Integer) in.readValue(Integer.class.getClassLoader());
//        this.roundType = in.readString();
//        this.isActive = (Boolean) in.readValue(Boolean.class.getClassLoader());
//        this.roundName = in.readString();
//        this.roundId = (Integer) in.readValue(Integer.class.getClassLoader());
//        this.assistantId = (Integer) in.readValue(Integer.class.getClassLoader());
//        this.routeOrder = (Integer) in.readValue(Integer.class.getClassLoader());
//        this.chatTeachers = (Boolean) in.readValue(Boolean.class.getClassLoader());
//        this.targetLng = in.readString();
//        this.targetLat = in.readString();
//        this.licenseState = in.readString();
//        this.trialDaysLeft = (Integer) in.readValue(Integer.class.getClassLoader());
//        this.licenseDaysLeft = (Integer) in.readValue(Integer.class.getClassLoader());
//        this.semesterStartDate = in.readString();
//        this.semesterEndDate = in.readString();
//        this.showAddBusCard = (Boolean) in.readValue(Boolean.class.getClassLoader());
//        this.showMap = in.readString();
//        this.changeLocation = in.readString();
//        this.studentStatus = in.readParcelable(StudentStatus_v2.class.getClassLoader());
//        this.mobileNumbers = in.createTypedArrayList(MobileNumber.CREATOR);
//        this.allowUploadStudentsImages = (Boolean) in.readValue(Boolean.class.getClassLoader());
//        this.pickup_request_distance=(Integer) in.readValue(Integer.class.getClassLoader());
//
//    }
//
//    public static final Creator<KidBean> CREATOR = new Creator<KidBean>() {
//        @Override
//        public KidBean createFromParcel(Parcel source) {
//            return new KidBean(source);
//        }
//
//        @Override
//        public KidBean[] newArray(int size) {
//            return new KidBean[size];
//        }
//    };
//}