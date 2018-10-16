package ru.orehovai.testdocs.model.api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import ru.orehovai.testdocs.model.Doc;

public interface IApi {

    @GET("api/v1/documents/all")
    Call<List<Doc>> getListDocs();

    @GET("api/v1/documents/get/{id}")
    Call<Doc> getDoc(@Path("id") String id);

    @GET("api/v1/documents/fav")
    Call<List<String>> getFavDocs();
}
