package com.example.recipesapp.data.remote

import com.example.recipesapp.model.CategoryModel
import com.example.recipesapp.model.SearchedRecipeModel
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface RecipeApi {
    @GET("api/json/v1/1/search.php")
    suspend fun searchRecipes(@Query(value = "s") name : String) : SearchedRecipeModel
}
interface CategoryApi {
    @GET("api/json/v1/1/categories.php")
    suspend fun getCategories() : CategoryModel
}
interface SearchByCategoryApi{
    @GET("api/json/v1/1/filter.php")
    suspend fun searchByCategory(@Query(value = "c") categoryName : String) : SearchedRecipeModel
}
interface RandomMealApi{
    @GET("api/json/v1/1/random.php")
    suspend fun getRandomMeal() : SearchedRecipeModel
}
object Network{
    private val customJson = Json { ignoreUnknownKeys = true }
    private const val BASE_URL = "https://www.themealdb.com/"
    private val retrofit = Retrofit.Builder().baseUrl(BASE_URL)
        .addConverterFactory(customJson.asConverterFactory("application/json".toMediaType())).build()

    val recipeApi : RecipeApi = retrofit.create(RecipeApi::class.java)
    val categoryApi : CategoryApi = retrofit.create(CategoryApi::class.java)
    val randomMealApi : RandomMealApi = retrofit.create(RandomMealApi::class.java)
    val searchByCategoryApi : SearchByCategoryApi = retrofit.create(SearchByCategoryApi::class.java)
}