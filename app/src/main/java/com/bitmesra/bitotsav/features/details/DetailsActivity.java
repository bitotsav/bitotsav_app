package com.bitmesra.bitotsav.features.details;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.bitmesra.bitotsav.R;
import com.bitmesra.bitotsav.database.models.flagship.FlagshipDetailsDto;
import com.bitmesra.bitotsav.features.EventDtoType;
import com.google.firebase.messaging.FirebaseMessaging;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailsActivity extends AppCompatActivity implements DetailsViewInterface {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    String eventName;
    int eventDtoType;
    DetailsPresenter presenter;
    @BindView(R.id.detail_time_venue)
    TextView timeVenue;
    @BindView(R.id.detail_money)
    TextView money;
    @BindView(R.id.star_subscribe)
    FloatingActionButton subscribeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flagship_detail);
        ButterKnife.bind(this);
        eventName = getIntent().getStringExtra("eventName");
        eventDtoType = getIntent().getIntExtra("eventDtoType", EventDtoType.TYPE_FLAGSHIP);
        toolbar.setTitle(eventName);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        presenter = new DetailsPresenter(this, this);
        presenter.fetchDetailsDto(eventName, eventDtoType);
        if (presenter.isTopicSubscribed(eventName)) {
            subscribeButton.setImageDrawable(getDrawable(android.R.drawable.star_big_on));
        } else {
            subscribeButton.setImageDrawable(getDrawable(android.R.drawable.star_big_off));
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void updateDetailView(FlagshipDetailsDto detailsDto) {
        timeVenue.setText(detailsDto.getTime() + " at " + detailsDto.getVenue());
        money.setText("Prize money: " + detailsDto.getMoney());
    }

    @OnClick(R.id.star_subscribe)
    void onSubscribe() {
        if (presenter.isTopicSubscribed(eventName)) {
            FirebaseMessaging.getInstance().unsubscribeFromTopic(eventName);
            //todo send the unsubscribed topic with registered id to server
            subscribeButton.setImageDrawable(getDrawable(android.R.drawable.star_big_off));
            presenter.unsubscribeFromTopic(eventName);
        } else {
            subscribeButton.setImageDrawable(getDrawable(android.R.drawable.star_big_on));
            FirebaseMessaging.getInstance().subscribeToTopic(eventName);
            presenter.subscribeToTopic(eventName);
            //todo send the subscribed topic with registered id to server
        }
    }
}
