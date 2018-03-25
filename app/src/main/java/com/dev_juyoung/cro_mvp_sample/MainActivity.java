package com.dev_juyoung.cro_mvp_sample;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.dev_juyoung.cro_mvp_sample.base.BaseActivity;
import com.dev_juyoung.cro_mvp_sample.data.ImageRepository;

import javax.inject.Inject;

import butterknife.BindView;
import dagger.android.AndroidInjection;

public class MainActivity extends BaseActivity<MainContract.View, MainContract.Presenter> implements MainContract.View {
    private static final String TAG = "MainActivity";

    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout refreshLayout;
    @BindView(R.id.imageList)
    RecyclerView imageList;

    private ImageAdapter mAdapter;

    @Inject
    MainPresenter mainPresenter;

    @Override
    protected MainContract.Presenter setPresenter() {
//        return new MainPresenter();
        return mainPresenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initPresenter();
        setupRefreshLayout();
        setupRecyclerView();
    }

    private void initPresenter() {
        // Adapter를 생성하고, Presenter에 Adapter 관련 View / Model setup.
        mAdapter = new ImageAdapter(this);
        mPresenter.setAdapterView(mAdapter);
        mPresenter.setAdapterModel(mAdapter);

        // Presenter에서 사용 될 Repository (Model) setup.
        mPresenter.setImageData(ImageRepository.getInstance());
    }

    private void setupRefreshLayout() {
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Presenter로 데이터 요청 이벤트 전달.
                mPresenter.updateData(MainActivity.this, true);
            }
        });
    }

    private void setupRecyclerView() {
        imageList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        imageList.setHasFixedSize(true);
        imageList.setAdapter(mAdapter);

        // Presenter로 데이터 요청 이벤트 전달.
        mPresenter.updateData(this, false);
    }

    @Override
    public void updateRefresh() {
        Log.i(TAG, "View: Presenter로 부터 UI 갱신 요청 받음. [ RefreshLayout 처리 ]");

        if (refreshLayout.isRefreshing()) {
            refreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void showToast(String message) {
        Log.i(TAG, "View: Presenter로 부터 UI 갱신 요청 받음. [ Toast 처리 ]");

        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
