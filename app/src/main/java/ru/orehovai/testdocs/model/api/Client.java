package ru.orehovai.testdocs.model.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Client {

    private static Client ourInstance = new Client();
    private static IApi api;

    private static final String BASE_URL = "http://95.213.236.54:3000/";

    public static Client getInstance() {
        return ourInstance;
    }

    private Client() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        api = retrofit.create(IApi.class);
    }

    public IApi getApi() {
        return api;
    }
}
