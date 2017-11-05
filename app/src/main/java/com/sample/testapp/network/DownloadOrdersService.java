package com.sample.testapp.network;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;


public interface DownloadOrdersService {

    @GET
    Call<ResponseBody> downloadfile(@Url String fileUrl);
}
