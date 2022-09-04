package trackwareschoolbus.parentschool.basePage;

import android.graphics.Rect;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;

import com.wdullaer.materialdatetimepicker.GravitySnapHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import io.reactivex.annotations.NonNull;
import io.reactivex.annotations.Nullable;

public abstract class BaseRecyclerViewAdapter_V2<Type, ViewHolder extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<ViewHolder> {

    private Type removedItem = null;
    private ArrayList<Type> rootList = new ArrayList<Type>();
    private ArrayList<Type> originalList = new ArrayList<Type>();
    private RecyclerView mRecyclerView;
    private boolean withFadeAnimation = false;
    private boolean withScale = false;
    private RecyclerViewType recyclerViewType = RecyclerViewType.Linear;

    public enum RecyclerViewType {
        Linear,
        Grid
    }


    public BaseRecyclerViewAdapter_V2(RecyclerView mRecyclerView, RecyclerViewType recyclerViewType) {
        this(mRecyclerView,recyclerViewType,2);
    }

    public BaseRecyclerViewAdapter_V2(RecyclerView mRecyclerView, RecyclerViewType recyclerViewType,int spanCount) {
        this.mRecyclerView = mRecyclerView;
        if (recyclerViewType == RecyclerViewType.Linear)
            this.mRecyclerView.setLayoutManager(new LinearLayoutManager(mRecyclerView.getContext(), LinearLayoutManager.VERTICAL, false));
        else {
            this.mRecyclerView.setLayoutManager(new GridLayoutManager(mRecyclerView.getContext(), spanCount));
            RecyclerView.ItemDecoration itemDecoration = new RecyclerView.ItemDecoration(){
                @Override
                public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                                           RecyclerView.State state) {
                    super.getItemOffsets(outRect, view, parent, state);
                    outRect.set(0, 0, 0, 0);
                }
            };
            mRecyclerView.addItemDecoration(itemDecoration);
        }

        this.mRecyclerView.setAdapter(this);
    }


    public void reverseAllItems() {
        Collections.reverse(rootList);
        Collections.reverse(originalList);
        notifyDataSetChanged();
    }

    @Override
    public final ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        return cViewHolder(viewGroup, i, layoutInflater);

    }

    @Override
    public final void onBindViewHolder(ViewHolder viewHolder, int i) {
        animate(viewHolder.itemView);
        bViewHolder(viewHolder, i, rootList.get(i));
    }


    @Override
    public int getItemCount() {
        return rootList.size();
    }

    public Type getItem(int position) {
        return rootList.get(position);
    }

    public int getItemPosition(Type item) {
        return rootList.indexOf(item);
    }

//    public void add(Type... items) {
//        for (Type item : items) {
//            add(item);
//        }
//    }

    public void add(Type item) {
        if (item == null) {
            return;
        }
        rootList.add(item);
        originalList.add(item);

        notifyItemInserted(rootList.size() - 1);
    }


    public void addFirst(Type item) {
        if (item == null) {
            return;
        }
        rootList.add(0, item);
        originalList.add(0, item);
        notifyItemInserted(0);
    }

    public void addAll(@NonNull List<Type> list) {
        if (list == null || list.size() == 0) {
            return;
        }
        for (Type type : list) {
            add(type);
        }
    }

    public void addAllNoAnimate(@Nullable  List<Type> list) {
        if (list == null || list.size() == 0) {
            return;
        }
        rootList.addAll(list);
        originalList.addAll(list);

        notifyDataSetChanged();
    }

    public void newList(List<Type> list) {
        clear();
        addAll(list);
    }

    public void clear() {
        while (!rootList.isEmpty()) {
            remove(0);
        }
        originalList.clear();
    }

    public void clearNoAnimate() {
        rootList.clear();
        originalList.clear();
        notifyDataSetChanged();
    }

    public Type remove(int position) {
        removedItem = rootList.remove(position);
        originalList.remove(position);
        notifyItemRemoved(position);
        return removedItem;
    }

    public void undoRemove() {
        if (removedItem != null) {
            add(removedItem);
            removedItem = null;
        }
    }

    public void sort(Comparator<Type> comparator) {
        Collections.sort(rootList, comparator);
        Collections.sort(originalList, comparator);
        notifyDataSetChanged();
    }

    public List<Type> getValues() {
        return originalList;
    }


    public boolean contain(Object object) {
        return rootList.contains(object);
    }

    public void filter(AdapterFilter<Type> adapterFilter) {
        rootList.clear();
        if (adapterFilter == null) {
            rootList.addAll(originalList);
        } else {
            for (Type type : originalList) {
                if (adapterFilter.filter(type)) {
                    rootList.add(type);
                }
            }
        }

        notifyDataSetChanged();
    }

    public abstract ViewHolder cViewHolder(@NonNull ViewGroup viewGroup, int i, @NonNull LayoutInflater layoutInflater);

    public abstract void bViewHolder(@NonNull ViewHolder viewHolder, int i, @NonNull Type item);

    public interface AdapterFilter<T> {
        boolean filter(T type);
    }


    public BaseRecyclerViewAdapter_V2 withListDivider() {
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mRecyclerView.getContext(), DividerItemDecoration.VERTICAL);
        mRecyclerView.addItemDecoration(dividerItemDecoration);
        return this;
    }

    public RecyclerView getRecyclerView() {
        return mRecyclerView;
    }

    public BaseRecyclerViewAdapter_V2 withFadeAnimation() {
        withFadeAnimation = true;
        return this;
    }

    public BaseRecyclerViewAdapter_V2 withScaleAnimation() {
        withScale = true;
        return this;
    }

    public BaseRecyclerViewAdapter_V2 withSnap() {
        new GravitySnapHelper(Gravity.CENTER).attachToRecyclerView(this.mRecyclerView);
        return this;
    }

    private void setFadeAnimation(View view) {
        final int FADE_DURATION = 1000;
        AlphaAnimation anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(FADE_DURATION);
        view.startAnimation(anim);
    }

    private void setScaleAnimation(View view) {
        final int Scale_DURATION = 300;
        ScaleAnimation anim = new ScaleAnimation(0.5f, 1.0f, 0.5f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        anim.setDuration(Scale_DURATION);
        view.startAnimation(anim);


    }

    private View animate(View view) {

        if (withFadeAnimation)
            setFadeAnimation(view);

        if (withScale)
            setScaleAnimation(view);

        return view;
    }


}