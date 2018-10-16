package ru.orehovai.testdocs.model.interactor;

import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.orehovai.testdocs.contract.BaseContract;
import ru.orehovai.testdocs.model.api.Client;
import ru.orehovai.testdocs.model.Doc;
import ru.orehovai.testdocs.contract.DocsListContract;
import ru.orehovai.testdocs.model.api.IApi;

public class GetDocsInteractorImpl implements BaseContract.GetDocInteractor, DocsListContract.GetDocInteractor {

    IApi api = Client.getInstance().getApi();

    @Override
    public void getDocsArrayList(final BaseContract.GetDocInteractor.OnFinishedListener onFinishedListener) {

        /** Create handle for the RetrofitInstance interface*/
        //IApi api = Client.getInstance().getApi();

        /** Call the method with parameter in the interface to get the notice data*/
        Call<List<Doc>> call = api.getListDocs();

        /**Log the URL called*/
        Log.wtf("URL Called", call.request().url() + "");

        call.enqueue(new Callback<List<Doc>>() {
            @Override
            public void onResponse(Call<List<Doc>> call, Response<List<Doc>> response) {
                onFinishedListener.onGetAllFinished(response.body());
            }

            @Override
            public void onFailure(Call<List<Doc>> call, Throwable t) {
                onFinishedListener.onFailure(t);
            }
        });

    }

    @Override
    public void getDoc(final DocsListContract.GetDocInteractor.OnFinishedListener onFinishedListener, String docId) {

        Call<Doc> call = api.getDoc(docId);
        call.enqueue(new Callback<Doc>() {
            @Override
            public void onResponse(Call<Doc> call, Response<Doc> response) {
                onFinishedListener.onGetDocFinished(response.body());
            }

            @Override
            public void onFailure(Call<Doc> call, Throwable t) {
                onFinishedListener.onFailure(t);
            }
        });


    }

    @Override
    public void getFavDocsList(final DocsListContract.GetDocInteractor.OnFinishedListener onFinishedListener) {
        Call<List<String>> call = api.getFavDocs();
        call.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                onFinishedListener.onGetFavFinished(response.body());
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                onFinishedListener.onFailure(t);
            }
        });

    }


}