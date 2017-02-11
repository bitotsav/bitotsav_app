package com.bitmesra.bitotsav.features.home;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bitmesra.bitotsav.R;
import com.bitmesra.bitotsav.base.BaseFragment;
import com.bitmesra.bitotsav.features.IdForFragment;
import com.bitmesra.bitotsav.features.home.adapter.HomeNotificationAdapter;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.TextSliderView;

import butterknife.BindView;
import butterknife.ButterKnife;


public class HomeFragment extends BaseFragment {


    HomePresenter presenter;
    @BindView(R.id.homeSliderLayout)
    SliderLayout sliderLayout;
    @BindView(R.id.homeNotificationRecyclerView)
    RecyclerView recyclerView;
    private HomeNotificationAdapter adapter;
    private LinearLayoutManager manager;

    public HomeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        presenter = new HomePresenter(getActivity());
        setUpSliderLayout();
        setUpNotificationRecyclerView();
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        sliderLayout.startAutoCycle(4000, 4000, true);
    }

    @Override
    public void onStop() {
        super.onStop();
        sliderLayout.stopAutoCycle();
    }

    @Override
    public IdForFragment getFragmentId() {
        return IdForFragment.HOME;
    }

    @Override
    public IdForFragment getBackToFragmentId() {
        return null;
    }

    private void setUpSliderLayout() {
        TextSliderView view = new TextSliderView(getActivity());
        view.description("Bitotsav 17")
                .image(R.drawable.home1);
        TextSliderView view2 = new TextSliderView(getActivity());
        view2.description("Party at BIT 17")
                .image(R.drawable.home2);
        TextSliderView view3 = new TextSliderView(getActivity());
        view3.description("Party3 at BIT 17")
                .image(R.drawable.home1);
        TextSliderView view4 = new TextSliderView(getActivity());
        view4.description("Party4 at BIT 17")
                .image(R.drawable.home2);
        sliderLayout.addSlider(view);
        sliderLayout.addSlider(view2);
        sliderLayout.addSlider(view3);
        sliderLayout.addSlider(view4);
        sliderLayout.setPresetTransformer(SliderLayout.Transformer.ZoomOut);
        sliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        sliderLayout.setDuration(4000);
        sliderLayout.setVisibility(View.GONE);
    }

    private void setUpNotificationRecyclerView() {
        recyclerView.setHasFixedSize(true);
        manager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(manager);
        adapter = new HomeNotificationAdapter(getActivity(), presenter.getNotifications());
        recyclerView.setAdapter(adapter);
        startAnimation();
    }

    private void startAnimation() {
        sliderLayout.setVisibility(View.VISIBLE);
        YoYo.with(Techniques.FadeInDown)
                .playOn(sliderLayout);
    }
}
