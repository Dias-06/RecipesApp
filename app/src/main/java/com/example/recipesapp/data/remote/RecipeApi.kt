package com.example.recipesapp.data.remote

import com.example.recipesapp.model.SearchedRecipeModel
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET

interface RecipeApi {
    @GET()
    suspend fun searchRecipes() : SearchedRecipeModel
}
object Network{
    private val customJson = Json
    private const val BASE_URL = "https://freedictionaryapi.com/"
    private const val PREDICTION_URL = "https://yesno.wtf/"
    private val retrofit = Retrofit.Builder().baseUrl(BASE_URL)
        .addConverterFactory(customJson.asConverterFactory("application/json".toMediaType())).build()
    private  val retrofitPrediction = Retrofit.Builder().baseUrl(PREDICTION_URL)
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType())).build()
}