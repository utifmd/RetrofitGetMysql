package com.doricovix.utif.retrofitgetmysql;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by utif on 8/8/2017.
 */

public interface ReqInterface {
    @POST("trynerror/php-campus/")
    Call<ItemResponse> operation(@Body ItemRequest request);
}
