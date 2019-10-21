package com.zulfa.login;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by haerul on 15/03/18.
 */

public interface ApiInterface {

    @POST("get_user.php")
    Call<List<User>> getUser();

    @FormUrlEncoded
    @POST("add_user.php")
    Call<User> insertPet(
            @Field("key") String key,
            @Field("nama") String nama,
            @Field("alamat") String alamat,
            @Field("jenis_kelamin") int jenis_kelamin,
            @Field("tanggal_lahir") String tanggal_lahir,
            @Field("picture") String picture);

    @FormUrlEncoded
    @POST("update_pet.php")
    Call<User> updatePet(
            @Field("key") String key,
            @Field("id") int id,
            @Field("nama") String nama,
            @Field("alamat") String alamat,
            @Field("jenis_kelamin") int jenis_kelamin,
            @Field("tanggal_lahir") String tanggal_lahir,
            @Field("picture") String picture);

    @FormUrlEncoded
    @POST("delete_pet.php")
    Call<User> deletePet(
            @Field("key") String key,
            @Field("id") int id,
            @Field("picture") String picture);

    @FormUrlEncoded
    @POST("update_love.php")
    Call<User> updateLove(
            @Field("key") String key,
            @Field("id") int id,
            @Field("love") boolean love);

}
