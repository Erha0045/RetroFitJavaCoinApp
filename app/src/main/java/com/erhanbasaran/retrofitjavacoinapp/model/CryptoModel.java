package com.erhanbasaran.retrofitjavacoinapp.model;

import com.google.gson.annotations.SerializedName;

public class CryptoModel {


    @SerializedName("currency")//skal være ens med json navnet da det er her der bliver sammenlignet med data navnet
    public String currency;

    @SerializedName("price")//skal være ens med json navnet
    public String price;

}
