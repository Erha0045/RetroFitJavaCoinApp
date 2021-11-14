package com.erhanbasaran.retrofitjavacoinapp.service;

import com.erhanbasaran.retrofitjavacoinapp.model.CryptoModel;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;

public interface CryptoAPI {

    //https://api.nomics.com/v1/prices?key=8c6a2a2573d13ddb164f6b9dd27e84445cdbc622

    @GET("prices?key=8c6a2a2573d13ddb164f6b9dd27e84445cdbc622")
    Observable<List<CryptoModel>> getData();

    //Call<List<CryptoModel>> getData();

}
