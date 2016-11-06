package com.example.andresarango.automaticpancake;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.andresarango.automaticpancake.news.NYTimesCardHolderPojo;
import com.example.andresarango.automaticpancake.sample_package.SampleParser;
import com.example.andresarango.automaticpancake.sample_package.SampleService;
import com.example.andresarango.automaticpancake.utility.networks.CardHolderCall;
import com.example.andresarango.automaticpancake.utility.networks.NetworkServices;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mCardRecycler;
    private CardRecycleAdapter mCardAdapter;
    private List<CardHolderCall> mNetworkList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mCardRecycler = (RecyclerView) findViewById(R.id.card_recycler);
        mCardRecycler.setLayoutManager(new LinearLayoutManager(this));
        mCardAdapter = new CardRecycleAdapter();
        mCardRecycler.setAdapter(mCardAdapter);
        NetworkServices netServe = new NetworkServices();
//        for (int i = 0; i < 10; i++) {
//            mCardAdapter.addCardHolderToEnd(new SampleCardHolderPOJO());
//        }

        mCardAdapter.addCardHolderToEnd(new NYTimesCardHolderPojo());
        for (int i = 0; i <10 ; i++) {
            mNetworkList.add(
                    new CardHolderCall<>(
                            new SampleParser(),
                            netServe.getJSONService(
                                    SampleService.CHUCK_NORRIS_BASE_URL,
                                    SampleService.class).getRandomJoke(),
                            mCardAdapter));
        }


//        mCardAdapter.addCardHolderToEnd(new CatMeme());
        makeNetworkListCall();

    }


    public void makeNetworkListCall() {
        for (int i = 0; i < mNetworkList.size(); i++) {
            mNetworkList.get(i).makeCall();
        }

    }
}
