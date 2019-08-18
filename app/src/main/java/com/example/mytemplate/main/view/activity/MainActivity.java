package com.example.mytemplate.main.view.activity;

import android.os.Bundle;
import android.widget.ListView;

import androidx.annotation.Nullable;

import com.example.mytemplate.R;
import com.example.mytemplate.actionView.ResponseApiDefault;
import com.example.mytemplate.api.GlobalApi;
import com.example.mytemplate.main.model.api.GithubRepoResponseModel;
import com.example.mytemplate.main.model.local.DefaultItemList;
import com.example.mytemplate.main.view.adapter.ExampleAdapterListView;
import com.example.mytemplate.base.BaseActivity;
import com.example.mytemplate.main.view.dialog.CustomProgressDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Response;

public class MainActivity extends BaseActivity {

    @BindView(R.id.list)
    private ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        // example call api
        callApi();
    }

    private void callApi(){
        CustomProgressDialog.showDialog(MainActivity.this);
        new GlobalApi(MainActivity.this).getUserRepo(new ResponseApiDefault<List<GithubRepoResponseModel>>() {

            @Override
            public void successApi(Response<List<GithubRepoResponseModel>> response) {
                CustomProgressDialog.closeDialog();

                assert response.body() != null;

                // parse response api
                List<DefaultItemList> mItems = new ArrayList<>();
                for (GithubRepoResponseModel item: response.body())
                    mItems.add(new DefaultItemList(item.getId(), item.getName()));

                configureListView(mItems);
            }

            @Override
            public void failedApi(@Nullable Response<List<GithubRepoResponseModel>> response, String message) {
                CustomProgressDialog.closeDialog();
            }
        }, "mojombo");
    }

    private void configureListView(List<DefaultItemList> mItems){
        list.setAdapter(new ExampleAdapterListView(MainActivity.this, mItems));
    }
}
