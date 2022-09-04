package trackwareschoolbus.parentschool.fragment.mykids.request_and_response;

public class HomeKidsListViewModel {


//    public static void callAllKids(SingleObserver<ArrayList<KidObjectAfterFilter>> singleObserver) {
//        ConnectionFactory.getInstance().getKidsListForAllKids()
//                .observeOn(AndroidSchedulers.mainThread())
////                .observeOn(Schedulers.io())
//                .map(new Function<KidsListResponse, ArrayList<KidObjectAfterFilter>>() {
//                    @Override
//                    public ArrayList<KidObjectAfterFilter> apply(KidsListResponse kidsListResponse) throws Exception {
//                        ArrayList<KidObjectAfterFilter> kidObjectAfterFilters = KidsListTools.ListFilter.filterAllKidslist(kidsListResponse);
//                        return kidObjectAfterFilters;
//                    }
//                })
//                .subscribe(singleObserver);
//        return;
//    }


//    public static void callSingleKid(KidsListRequest kidsListRequest, SingleObserver<KidBean> singleObserver) {
//        ConnectionFactory.getInstance().getKidsListForSingleKid(kidsListRequest)
//                .observeOn(AndroidSchedulers.mainThread())
//                .map(new Function<KidsListResponse, KidBean>() {
//                    @Override
//                    public KidBean apply(KidsListResponse kidsListResponse) throws Exception {
//                        return KidsListTools.ListFilter.filterSingleKidlist(kidsListResponse);
//                    }
//                })
//                .subscribe(singleObserver);
//        return;
//    }


}
