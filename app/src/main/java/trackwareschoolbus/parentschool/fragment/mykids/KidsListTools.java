package trackwareschoolbus.parentschool.fragment.mykids;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.view.View;
import android.view.Window;

import java.util.List;

import trackwareschoolbus.parentschool.R;
import trackwareschoolbus.parentschool.api_v3.GetKidsResp;
import trackwareschoolbus.parentschool.utilityParent.StringUtil;

public class KidsListTools {
    //
    public static class ListFilter {
//        public static ArrayList<KidBean> filterAllKidslist(KidsListResponse kidsListResponse) {
//
//            ArrayList<KidBean> kidsAfterFilter = new ArrayList<>();
//            for (Integer nonDuplicatedIds : getNonDuplicatedKidsIds(kidsListResponse)) {
//                kidsAfterFilter.add(convertDuplicatedKidsToSingle(findKidsById(kidsListResponse, nonDuplicatedIds)));
//            }
//            return kidsAfterFilter;
//        }

//        public static KidBean filterSingleKidlist(KidsListResponse kidsListResponse) {
//            KidBean kidObjectAfterFilter = convertDuplicatedKidsToSingle(kidsListResponse.getStudents());
//            return kidObjectAfterFilter;
//        }

//        public static ArrayList<KidBean> findKidsById(KidsListResponse kidsListResponse, Integer kidId) {
//            ArrayList<KidBean> kidsWithSameId = new ArrayList<>();
//            HashSet<Integer> noDuplicatedKidsIds = new HashSet<>();
//            for (KidBean kidObj : kidsListResponse.getStudents()) {
//                if (kidObj.getId() == kidId.intValue())
//                    kidsWithSameId.add(kidObj);
//            }
//            return kidsWithSameId;
//        }

//        public static HashSet<Integer> getNonDuplicatedKidsIds(KidsListResponse kidsListResponse) {
//            HashSet<Integer> noDuplicatedKidsIds = new HashSet<>();
//            for (KidBean kid : kidsListResponse.getStudents()) {
//                noDuplicatedKidsIds.add(kid.getId());
//            }
//            return noDuplicatedKidsIds;
//        }


        public static GetKidsResp.Student getSingleStudentObject(List<GetKidsResp.Student> duplicatedKids) {
            for (int i = 0; i < duplicatedKids.size(); i++) {
                if (duplicatedKids.get(i).isActive()) {
                    //////////////* if the student is absent in the (active) round then he is absent */ /////////////
                    if (duplicatedKids.get(i).getStudentStatus().getActivityType().equalsIgnoreCase("absent-by-parent")
                            ||
                            duplicatedKids.get(i).getStudentStatus().getActivityType().equalsIgnoreCase("absent")) {
                        //student is absent
                        return duplicatedKids.get(i);
                        //////////////* if the student is (no-show) in the (active) round then he is not getting on the bus  */ /////////////
                    } else if (duplicatedKids.get(i).getStudentStatus().getActivityType().equalsIgnoreCase("no-show")) {
                        //student is no_show
                        return duplicatedKids.get(i);
                    } else { //////////////* because there is no way to be (2 active rounds ) open the map for the user  */ /////////////
                        //student is active
                        return duplicatedKids.get(i);
                    }
                }
            }

            return duplicatedKids.get(0);
        }


        public static GetKidsResp.Student convertDuplicatedKidsToSingle(List<GetKidsResp.Student> duplicatedKids) {
            /**/
            GetKidsResp.Student student = getSingleStudentObject(duplicatedKids);
            /**/


            if (student == null) {
                student = duplicatedKids.get(0);
            }
//            KidBean kidObjectAfterFilter = new KidBean();
//            /**/
//            kidObjectAfterFilter.kidFullname = student.getName();
//            kidObjectAfterFilter.kidId = student.getId();
//            kidObjectAfterFilter.kidImageUrl = student.getAvatar();
//            kidObjectAfterFilter.kidGrade = student.getStudentGrade();
//            kidObjectAfterFilter.dropOffByParent = student.getDropOffByParent();
//            kidObjectAfterFilter.pickupByParent = student.getPickupByParent();
//            kidObjectAfterFilter.kidFatherId = student.getFatherId();
//            kidObjectAfterFilter.kidMotherId = student.getMotherId();
//            kidObjectAfterFilter.kidLng = student.getTargetLng();
//            kidObjectAfterFilter.kidLat = student.getTargetLat();
//            kidObjectAfterFilter.kidRouteOrder = student.getRouteOrder();
//            kidObjectAfterFilter.isKidActiveNowInRound = student.getActive();
//            /**/
//            kidObjectAfterFilter.schoolId = student.getSchoolId();
//            kidObjectAfterFilter.schoolName = student.getSchoolName();
//            kidObjectAfterFilter.schoolMobileNumber = student.getSchoolMobileNumber();
//            kidObjectAfterFilter.schoolLat = student.getSchoolLat();
//            kidObjectAfterFilter.schoolLng = student.getSchoolLng();
//            /**/
//            kidObjectAfterFilter.driverMobileNumber = student.getDriverMobileNumber();
//            kidObjectAfterFilter.driverMobileToken = student.getDriverMobileToken();
//            kidObjectAfterFilter.driverName = student.getDriverName();
//            /**/
//            kidObjectAfterFilter.assistantName = student.getAssistantName();
//            kidObjectAfterFilter.assistantMobileNumber = student.getAssistantMobileNumber();
//            /**/
//            kidObjectAfterFilter.canParentAddCard = student.getShowAddBusCard();
//            kidObjectAfterFilter.canParentShowMap = student.getShowMap();
//            kidObjectAfterFilter.canParentChangeLocation = student.getChangeLocation();
//            kidObjectAfterFilter.busId = student.getBusId();
//            kidObjectAfterFilter.mobileNumbers = student.getMobileNumbers();


            return student;
        }
    }


//    public static KidBean getStudentObjectWithActiveRound(List<KidBean> duplicatedKids) {
//        for (KidBean student : duplicatedKids) {
//            if (student.getActive())
//                return student;
//        }
//        return null;
//    }

//    public void checkCameraAndPin(KidBean kidBean) {
//        mActivity.cameraPermission(mActivity,new OnActionDoneListener<Boolean>() {
//            @Override
//            public void OnActionDone(Boolean permissionsAccepted) {
//                if (permissionsAccepted) {
//                    new ConfermPinDialog(mActivity, EnumFragment.QR_CODE, kidBean.getId()).show();
//                }
//
//            }
//        });
//    }


    public static void CallDialog(Activity activity, final GetKidsResp.Student kidObjectAfterFilter) {
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_call_new_);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        View lliCallSchool = dialog.findViewById(R.id.lliCallSchool);
        View lliCallDriver = dialog.findViewById(R.id.lliCallDriver);
        View lliCallAssistant = dialog.findViewById(R.id.lliCallAssistant);
        View imgClose = dialog.findViewById(R.id.imgClose);

        imgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        lliCallAssistant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                callThisNumber(activity, kidObjectAfterFilter.getAssistantMobileNumber().trim());
            }
        });

        lliCallSchool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                callThisNumber(activity, kidObjectAfterFilter.getSchoolMobileNumber().trim());
            }
        });
        lliCallDriver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                callThisNumber(activity, kidObjectAfterFilter.getDriverMobileNumber().trim());
            }
        });

        if (StringUtil.isEmptyString(kidObjectAfterFilter.getAssistantMobileNumber()))
            lliCallAssistant.setVisibility(View.GONE);
        if (StringUtil.isEmptyString(kidObjectAfterFilter.getSchoolMobileNumber()))
            lliCallSchool.setVisibility(View.GONE);
        if (StringUtil.isEmptyString(kidObjectAfterFilter.getDriverMobileNumber()))
            lliCallDriver.setVisibility(View.GONE);
        /**/


        dialog.show();
    }


    public static void callThisNumber(Activity activity, String number) {
        try {
            String uri = "tel:" + number;
            Intent callIntent = new Intent(Intent.ACTION_DIAL, Uri.parse(uri));
            activity.startActivity(callIntent);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
