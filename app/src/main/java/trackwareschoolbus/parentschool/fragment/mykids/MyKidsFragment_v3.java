package trackwareschoolbus.parentschool.fragment.mykids;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;

import me.linshen.retrofit2.adapter.ApiResponse;
import me.linshen.retrofit2.adapter.ApiSuccessResponse;
import trackwareschoolbus.parentschool.API_V2.ConnectionFactory;
import trackwareschoolbus.parentschool.MainFragmentActivity;
import trackwareschoolbus.parentschool.R;
import trackwareschoolbus.parentschool.api_v3.GetKidsResp;
import trackwareschoolbus.parentschool.api_v3.SchoolDataHolder;
import trackwareschoolbus.parentschool.basePage.BaseFragment;
import trackwareschoolbus.parentschool.basePage.BaseRecyclerViewAdapter_V2;
import trackwareschoolbus.parentschool.interfaceParent.OnActionDoneListener;
import trackwareschoolbus.parentschool.toolsV2.DrawableToolsV2;
import trackwareschoolbus.parentschool.utilityParent.UtilDialogs;
import trackwareschoolbus.parentschool.utilityParent.UtilLocation;
import trackwareschoolbus.parentschool.utilityParent.UtilityParent;

/**
 * Created by  on 2/23/2017.
 */

public class MyKidsFragment_v3 extends BaseFragment {


    private View labNoKids;
    private View add_student;
    private RecyclerView rsKids;
    private BaseRecyclerViewAdapter_V2 kidsListAdapter;
    private View rootView;
    private SwipeRefreshLayout swipeRefreshLayout;

    public static MyKidsFragment_v3 newInstance() {
        return new MyKidsFragment_v3();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragmnent_kids_v2, container, false);
        }
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        /**/
        rsKids = rootView.findViewById(R.id.rsKids);
        labNoKids = rootView.findViewById(R.id.labNoKids);
        add_student = rootView.findViewById(R.id.add_student);
        swipeRefreshLayout = rootView.findViewById(R.id.swipeRefreshLayout);
//        ((TextView) rootView.findViewById(R.id.labTitle)).setText(getResources().getString(R.string.my_kids));
        /**/
//        initAdapter();
        /**/


        add_student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainFragmentActivity.showLoginFragment();
            }
        });

//        DrawableToolsV2.loadDrawable(rootView.findViewById(R.id.toolBarHome), R.drawable.img_notification).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                MainFragmentActivity.showNotificationFragment();
//
//            }
//        });

//        DrawableToolsV2.loadDrawable(rootView.findViewById(R.id.imgNotification), R.drawable.setting).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                MainFragmentActivity.showSettingFragment();
//            }
//        });

        rootView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
//                    Toast.makeText(mActivity, "moo", Toast.LENGTH_LONG).show();
                    return true;
                }
                return false;
            }
        });

//        getMainActivity().registerRefreshReceiver(new BroadcastReceiver() {
//            @Override
//            public void onReceive(Context context, Intent intent) {
//                callKidsListAPI();
//
//            }
//        });


        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                callKidsList();


                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 1000);

            }
        });

        ArrayList<SchoolDataHolder> savedlDataHolders = UtilityParent.getSavedlDataHolders();
        ArrayList<GetKidsResp.Student> allKidsList = new ArrayList<>();
        for (int i = 0; i < savedlDataHolders.size(); i++) {
            allKidsList.addAll(savedlDataHolders.get(i).getKidsList());
        }
        addKidsList(allKidsList);
//        if (BaseActivity.selectedSchool == null) {
//            showNoKids(true);
//            showList(false);
//        } else {
//            showNoKids(false);
//            showList(true);
////            callKidsListAPI();
//        }

    }


    private BaseRecyclerViewAdapter_V2 getKidsListAdapter() {
        if (kidsListAdapter == null) {
            kidsListAdapter = new BaseRecyclerViewAdapter_V2<GetKidsResp.Student, MyKidsHolder_v2>(rsKids, BaseRecyclerViewAdapter_V2.RecyclerViewType.Grid) {
                @Override
                public MyKidsHolder_v2 cViewHolder(ViewGroup viewGroup, int i, LayoutInflater layoutInflater) {
                    return new MyKidsHolder_v2(layoutInflater.inflate(R.layout.list_kids_v2, viewGroup, false));
                }

                @Override
                public void bViewHolder(MyKidsHolder_v2 viewHolder, int i, GetKidsResp.Student kidObjectAfterFilter) {


                    viewHolder.loadStudentImage(kidObjectAfterFilter.fullAvatar());

                    // test  // test  // test  // test  // test  // test
//                switch (i){
//                    case 1:
//                    case 2:
//                    case 3:
//                        kidObjectAfterFilter.isActive=true;
//                        break;
//                }
                    // test  // test  // test  // test  // test  // test  // test

//                    viewHolder.loadStudentImage(kidObjectAfterFilter.getAvatar());

                    boolean isOnline;
                    isOnline = kidObjectAfterFilter.isActive();
                    isOnline = isOnline && !kidObjectAfterFilter.getStudentStatus().getActivityType().equalsIgnoreCase("absent-by-parent");
                    isOnline = isOnline && !kidObjectAfterFilter.getStudentStatus().getActivityType().equalsIgnoreCase("absent");
                    isOnline = isOnline && !kidObjectAfterFilter.getStudentStatus().getActivityType().equalsIgnoreCase("no-show");
                    viewHolder.isActive(isOnline);
                    /**/
                    viewHolder.student_name_txt.setText(kidObjectAfterFilter.getFirstname());
//                viewHolder.school_name_txt.setText(StringToolsV2.strBuilder(/*getString(R.string.school), " : ",*/ kidObjectAfterFilter.getSchoolName()));
//                viewHolder.student_grade_txt.setText(StringToolsV2.strBuilder(/*getString(R.string.grade), " : ", */kidObjectAfterFilter.getStudentGrade()));
                    viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            openKidDetailsFragment(kidObjectAfterFilter);
                        }
                    });

                    if (!kidObjectAfterFilter.getShowMap()) {
                        viewHolder.bootom_container.setVisibility(View.INVISIBLE);
                    } else {
                        viewHolder.bootom_container.setVisibility(View.VISIBLE);
                    }
                    viewHolder.bootom_container.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (kidObjectAfterFilter.isActive()) {
                                goToMap(kidObjectAfterFilter);
                            } else if (kidObjectAfterFilter.getStudentStatus().getActivityType().equalsIgnoreCase("absent-by-parent")) {
                                UtilityParent.showMessage(getMainActivity(), getMainActivity().getString(R.string.absent));
                            } else if (kidObjectAfterFilter.getStudentStatus().getActivityType().equalsIgnoreCase("absent")) {
                                UtilityParent.showMessage(getMainActivity(), getMainActivity().getString(R.string.absent));
                            } else if (kidObjectAfterFilter.getStudentStatus().getActivityType().equalsIgnoreCase("no-show")) {
                                UtilityParent.showMessage(getMainActivity(), getMainActivity().getString(R.string.no_show));
                            } else {
                                UtilityParent.showMessage(getMainActivity(), getMainActivity().getString(R.string.round_not_activr));
                            }


//                        Toast.makeText(viewHolder.tracker_container.getContext(), "weeeee", Toast.LENGTH_SHORT).show();
                        }
                    });


                }
            }.withFadeAnimation();
        }
        return kidsListAdapter;

    }


    private void goToMap(GetKidsResp.Student kidObjectAfterFilter) {
        if (!UtilLocation.islocationAvailable(getMainActivity())) {
            UtilDialogs.showNoGPSDialog(getMainActivity());
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putParcelable("KIDBEAN", kidObjectAfterFilter);
        MainFragmentActivity.showMapFragment(bundle);
    }


    private void addKidsList(ArrayList<GetKidsResp.Student> kidObjectAfterFilters) {
//        kidObjectAfterFilters.remove(1);
//        kidObjectAfterFilters.remove(2);
//        kidObjectAfterFilters.get(0).isActive=true;

        if (kidObjectAfterFilters.size() == 0) {
            showNoKids(true);
            showList(false);
        } else {
            showNoKids(false);
            showList(true);
        }
        getKidsListAdapter().clear();
//        kidsListAdapter.clear();
        getKidsListAdapter().addAllNoAnimate(kidObjectAfterFilters);

    }


    private void showNoKids(boolean showNoKidsText) {
        labNoKids.setVisibility(showNoKidsText ? View.VISIBLE : View.GONE);
    }

    private void showList(boolean showNoKidsText) {
        rsKids.setVisibility(showNoKidsText ? View.VISIBLE : View.GONE);
    }

    private void openKidDetailsFragment(GetKidsResp.Student kidObjectAfterFilter) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("KIDBEAN", kidObjectAfterFilter);
        bundle.putParcelable("KIDBEAN", kidObjectAfterFilter);
        MainFragmentActivity.showKidDetails(bundle);
        //////////

    }

    @Override
    public void onResume() {
        super.onResume();
        try {
            getView().setFocusableInTouchMode(true);
            getView().requestFocus();
            getView().setOnKeyListener(new View.OnKeyListener() {
                @Override
                public boolean onKey(View v, int keyCode, KeyEvent event) {
                    if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                        System.exit(0);
                        return true;
                    }
                    return false;
                }
            });
        } catch (Exception e) {

        }

    }


    @Override
    protected boolean showToolBarNotification() {
        return true;
    }

    @Override
    protected boolean showToolBack() {
        return false;
    }

    @Override
    protected boolean showToolBarSettings() {
        return true;
    }

    @Override
    protected String initToolBarTitle() {
        return getString(R.string.my_kids);
    }

    public void callKidsList() {

        for (int i = 0; i < UtilityParent.getSavedlDataHolders().size(); i++) {
            ConnectionFactory.getInstance().getKidsListForAllKids(UtilityParent.getSavedlDataHolders().get(i).getAuthorization())
                    .observe(getViewLifecycleOwner(), new Observer<ApiResponse<GetKidsResp>>() {
                        @Override
                        public void onChanged(ApiResponse<GetKidsResp> response) {
                            hideLoading();
                            try {
                                GetKidsResp students = ((ApiSuccessResponse<GetKidsResp>) response).getBody();
                                replaceKidList(students);

                                ArrayList<SchoolDataHolder> savedlDataHolders = UtilityParent.getSavedlDataHolders();
                                ArrayList<GetKidsResp.Student> allKidsList = new ArrayList<>();
                                for (int i = 0; i < savedlDataHolders.size(); i++) {
                                    allKidsList.addAll(savedlDataHolders.get(i).getKidsList());
                                }
                                addKidsList(allKidsList);

                            } catch (Exception e) {
                                showError();

                            }
                        }
                    });

        }


    }

    public void replaceKidList(GetKidsResp getKidsResp) {
        for (int i = 0; i < UtilityParent.getSavedlDataHolders().size(); i++) {
            int parentId = getKidsResp.getParentId();
            int parentId1 = UtilityParent.getSavedlDataHolders().get(i).getParentId();

            if (parentId == parentId1) {
                UtilityParent.getSavedlDataHolders().get(i).setKidsResp(null);
                UtilityParent.getSavedlDataHolders().get(i).setKidsResp(getKidsResp);
            }
        }

        UtilityParent.saveCurrentHolders();

    }

    @Override
    public void onStart() {
        super.onStart();
        getMainActivity().registerRefreshReceiver(intent -> callKidsList());
    }

    @Override
    public void onStop() {
        getMainActivity().unRegisterMessageReceiver();
        super.onStop();
    }
}
