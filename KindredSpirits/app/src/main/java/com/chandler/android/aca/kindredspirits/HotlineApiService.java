package com.chandler.android.aca.kindredspirits;

import retrofit2.Call;
import retrofit2.http.GET;

public interface HotlineApiService {

    @GET("https://kindred-spirits.firebaseio.com/hotlines/")
    Call<Hotline.HotlineResult> getHotlines();
}
