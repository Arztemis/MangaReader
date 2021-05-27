package com.example.mangatoon.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import com.example.mangatoon.Callback;
import com.example.mangatoon.GetActionUrl;
import com.example.mangatoon.GetBannerUrl;
import com.example.mangatoon.GetFictionUrl;
import com.example.mangatoon.GetHorrorUrl;
import com.example.mangatoon.GetMaybeYouLikeUrl;
import com.example.mangatoon.R;
import com.example.mangatoon.activity.ItemOffsetDecoration;
import com.example.mangatoon.adapter.ActionAdapter;
import com.example.mangatoon.adapter.BannerApdapter;
import com.example.mangatoon.adapter.FictionAdapter;
import com.example.mangatoon.adapter.HorrorAdapter;
import com.example.mangatoon.adapter.MaybeYouLikedAdapter;
import com.example.mangatoon.model.Action;
import com.example.mangatoon.model.Banner;
import com.example.mangatoon.model.Fiction;
import com.example.mangatoon.model.Horror;
import com.example.mangatoon.model.MaybeYouLiked;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements Callback {

    private List<Banner> listBanner = new ArrayList<>();
    private BannerApdapter bannerApdapter;
    private MaybeYouLikedAdapter maybeYouLikedAdapter;
    private ActionAdapter actionAdapter;
    private HorrorAdapter horrorAdapter;
    private FictionAdapter fictionAdapter;
    private ViewPager2 viewPager2;
    private TextView tvTitle, tvNewChapter, tvTimeUpdate;
    private RecyclerView rcMaybeYouLike;
    private List<MaybeYouLiked> listMaybe = new ArrayList<>();
    private List<Action> listAction = new ArrayList<>();
    private List<Horror> listHorror = new ArrayList<>();
    private List<Fiction> listFiction = new ArrayList<>();
    private RecyclerView rcAction, rcHorror, rcFiction;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home_frament, container, false);


        tvTitle = view.findViewById(R.id.title);
        tvNewChapter = view.findViewById(R.id.newChapter);
        tvTimeUpdate = view.findViewById(R.id.timeUpdate);

        //Banner
        viewPager2 = view.findViewById(R.id.viewpager);
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tvTitle.setText(listBanner.get(position).getTitle());
                tvNewChapter.setText(listBanner.get(position).getNewChapter());
                tvTimeUpdate.setText(listBanner.get(position).getTimeUpdate());
                super.onPageSelected(position);
            }
        });
        viewPager2.setClipToPadding(false);
        viewPager2.setClipChildren(false);
        viewPager2.setOffscreenPageLimit(3);
        viewPager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(40));
        compositePageTransformer.addTransformer((page, position) -> {
            float r = 1 - Math.abs(position);
            page.setScaleY(0.85f + 0.15f * r);

        });
        viewPager2.setPageTransformer(compositePageTransformer);

        //Maybe you like
        rcMaybeYouLike = view.findViewById(R.id.rc_maybeyoulike);
        rcMaybeYouLike.setLayoutManager(new GridLayoutManager(getContext(), 2));
        ItemOffsetDecoration itemDecoration = new ItemOffsetDecoration(10);
        rcMaybeYouLike.addItemDecoration(itemDecoration);
        rcMaybeYouLike.setHasFixedSize(true);
        rcMaybeYouLike.setNestedScrollingEnabled(false);

        //Action
        rcAction = view.findViewById(R.id.rc_action);
        rcAction.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        //Horror
        rcHorror = view.findViewById(R.id.rc_horror);
        rcHorror.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        //Fiction
        rcFiction = view.findViewById(R.id.rc_fiction);
        rcFiction.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        GetBannerUrl getBannerUrl = new GetBannerUrl();
        getBannerUrl.execute();
        getBannerUrl.callback = this;

        GetMaybeYouLikeUrl getMaybeYouLikeUrl = new GetMaybeYouLikeUrl(this);
        getMaybeYouLikeUrl.execute();

        GetActionUrl getActionUrl = new GetActionUrl(this);
        getActionUrl.execute();

        GetHorrorUrl getHorrorUrl = new GetHorrorUrl(this);
        getHorrorUrl.execute();

        GetFictionUrl getFictionUrl = new GetFictionUrl(this);
        getFictionUrl.execute();
    }

    @Override
    public void getBannerUrlDone(List<Banner> list) {
        listBanner = list;
        bannerApdapter = new BannerApdapter(listBanner, getContext());
        viewPager2.setAdapter(bannerApdapter);
        bannerApdapter.notifyDataSetChanged();
    }

    @Override
    public void getMaybeYouLikeUrlDone(List<MaybeYouLiked> list) {
        listMaybe = list;
        maybeYouLikedAdapter = new MaybeYouLikedAdapter(listMaybe, getContext());
        rcMaybeYouLike.setAdapter(maybeYouLikedAdapter);
        maybeYouLikedAdapter.notifyDataSetChanged();
    }

    @Override
    public void getActionUrlDone(List<Action> list) {
        listAction = list;
        actionAdapter = new ActionAdapter(getContext(), listAction);
        rcAction.setAdapter(actionAdapter);
        actionAdapter.notifyDataSetChanged();
    }

    @Override
    public void getHorrorUrlDone(List<Horror> list) {
        listHorror = list;
        horrorAdapter = new HorrorAdapter(getContext(), listHorror);
        rcHorror.setAdapter(horrorAdapter);
        horrorAdapter.notifyDataSetChanged();
    }

    @Override
    public void getFictionUrlDone(List<Fiction> list) {
        listFiction = list;
        fictionAdapter = new FictionAdapter(getContext(), listFiction);
        rcFiction.setAdapter(fictionAdapter);
        fictionAdapter.notifyDataSetChanged();
    }

}