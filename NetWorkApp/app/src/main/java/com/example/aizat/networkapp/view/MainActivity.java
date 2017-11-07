package com.example.aizat.networkapp.view;

import android.app.LoaderManager;
import android.content.Loader;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.aizat.networkapp.R;
import com.example.aizat.networkapp.loader.InputStreamLoader;
import com.example.aizat.networkapp.loader.OkHttpLoader;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Object>, Callback<ResponseBody> {

    private static final int LOADER_NETWORK = 1;
    private Unbinder unbinder;

    @BindView(R.id.text_view_result) TextView textView;

    @BindView(R.id.button_start) Button startButton;

    @BindView(R.id.button_start_retrofit) Button startRetrofitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        unbinder = ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if( unbinder != null){
            unbinder.unbind();
        }
    }
    @OnClick(R.id.button_start)
    public void onStartClick(){
        getLoaderManager().initLoader(LOADER_NETWORK, null, this);
    }

    @OnClick(R.id.button_start_retrofit)
    public void onStartRetrofitClick(){
        /// TODO: 30.10.2017
        ((AppDelegate)getApplication()).getRetrofit().getData().enqueue(this);
    }

    @Override
    public Loader onCreateLoader(int id, Bundle args) {
        switch (id){
            case  LOADER_NETWORK:
                return  new OkHttpLoader(this,Const.URL_GET);
        }
        return null;
    }

    @Override
    public void onLoadFinished(Loader loader, Object data) {
        switch (loader.getId()){
            case LOADER_NETWORK:
                onNetworkLoadingSuccess((String) data);
                break;
        }
    }

    @Override
    public void onLoaderReset(Loader loader) {
    }

    private void onNetworkLoadingSuccess(@Nullable String result){
        startButton.setVisibility(View.GONE);
        startRetrofitButton.setVisibility(View.GONE);
        if (result!=null) {
            textView.setText(result);
        } else {
            textView.setText("Подключи интернет");
        }
    }

    @Override
    public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
        try{
            onNetworkLoadingSuccess(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFailure(Call<ResponseBody> call, Throwable t){
        onNetworkLoadingSuccess(t.getMessage());
    }
}
