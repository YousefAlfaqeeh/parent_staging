//package trackwareschoolbus.parentschool.screens_v2.notifications_v2;
//
//import java.util.ArrayList;
//
//import io.reactivex.SingleObserver;
//import io.reactivex.android.schedulers.AndroidSchedulers;
//import io.reactivex.functions.Function;
//import trackwareschoolbus.parentschool.API_V2.ConnectionFactory;
//import trackwareschoolbus.parentschool.screens_v2.BaseViewModelV2;
//import trackwareschoolbus.parentschool.screens_v2.notifications_v2.request_and_response.GetNotificationsRequest;
//import trackwareschoolbus.parentschool.screens_v2.notifications_v2.request_and_response.GetNotificationsResponse;
//
//
//public class NotificationsViewModel extends BaseViewModelV2 {
//
//
//    public NotificationsViewModel callAllKids(GetNotificationsRequest getNotificationsRequest, SingleObserver<ArrayList<GetNotificationsResponse>> singleObserver) {
//        ConnectionFactory.getInstance().getNotifications(getNotificationsRequest)
//                .observeOn(AndroidSchedulers.mainThread())
//                .map(new Function<ArrayList<GetNotificationsResponse>, ArrayList<GetNotificationsResponse>>() {
//                    @Override
//                    public ArrayList<GetNotificationsResponse> apply(ArrayList<GetNotificationsResponse> getNotificationsResponse) throws Exception {
//
//                        return getNotificationsResponse;
//                    }
//                })
//                .subscribe(singleObserver);
//        return this;
//    }
//
//
//}
