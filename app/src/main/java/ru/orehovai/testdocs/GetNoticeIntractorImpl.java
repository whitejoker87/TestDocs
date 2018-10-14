package ru.orehovai.testdocs;

import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetNoticeIntractorImpl implements MainContract.GetNoticeIntractor {

    @Override
    public void getNoticeArrayList(final OnFinishedListener onFinishedListener) {


        /** Create handle for the RetrofitInstance interface*/
        IApi api = Client.getInstance().getApi();

        /** Call the method with parameter in the interface to get the notice data*/
        Call<List<Doc>> call = api.getListDocs();

        /**Log the URL called*/
        Log.wtf("URL Called", call.request().url() + "");

        call.enqueue(new Callback<List<Doc>>() {
            @Override
            public void onResponse(Call<List<Doc>> call, Response<List<Doc>> response) {
                onFinishedListener.onFinished(response.body());

            }

            @Override
            public void onFailure(Call<List<Doc>> call, Throwable t) {
                onFinishedListener.onFailure(t);
            }
        });

    }

}