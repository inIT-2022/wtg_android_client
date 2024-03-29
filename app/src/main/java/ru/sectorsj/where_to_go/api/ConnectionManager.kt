package ru.sectorsj.where_to_go.api

import androidx.viewbinding.BuildConfig
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.reflect.TypeToken
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.sectorsj.where_to_go.dto.Location
import java.lang.reflect.Type
import kotlin.properties.Delegates

const val BASE_URL = "http://95.163.237.3:8179/wtg/api/v1/"
const val AUTH_URL = "http://95.163.237.3:8179/wtg/"


object ConnectionManager {
    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        if (BuildConfig.DEBUG) {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }
    private val typeToken: Type = TypeToken.getParameterized(Location::class.java).type
    private fun getOkHttpClient() = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    fun getRetrofitClient(
        okHttpClient: OkHttpClient = getOkHttpClient(),
        url: String,
        type: Type = typeToken
    ): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .addConverterFactory(GsonConverterInstance.createGsonConverter(type, LocationDeserializer()))
        .baseUrl(url)
        .client(okHttpClient)
        .build()
}

class LocationDeserializer : JsonDeserializer<Location> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Location {
        val jsonObject = json?.asJsonObject

        var id by Delegates.notNull<Long>()
        var title by Delegates.notNull<String>()
        var description by Delegates.notNull<String>()
        var fullDescription by Delegates.notNull<String>()
        var address by Delegates.notNull<String>()
        var workTimeStart by Delegates.notNull<String>()
        var workTimeEnd by Delegates.notNull<String>()
        var workBreakStart by Delegates.notNull<String>()
        var workBreakEnd by Delegates.notNull<String>()
        var linkImage by Delegates.notNull<String>()
        var linkSite by Delegates.notNull<String>()
        var latitude by Delegates.notNull<Double>()
        var longitude by Delegates.notNull<Double>()

        jsonObject?.let {
            id = jsonObject.get("id").asLong
            title = jsonObject.get("title").asString
            description = jsonObject.get("description").asString
            fullDescription = jsonObject.get("fullDescription").asString
            address = jsonObject.get("address").asString
            workTimeStart = jsonObject.get("workTimeStart").asString
            workTimeEnd = jsonObject.get("workTimeEnd").asString
            workBreakStart = jsonObject.get("workBreakStart").asString
            workBreakEnd = jsonObject.get("workBreakEnd").asString
            linkImage = jsonObject.get("linkImage").asString
            linkSite = jsonObject.get("linkSite").asString
            latitude = jsonObject.get("latitude").asDouble
            longitude = jsonObject.get("longitude").asDouble
        }

        return Location(
            id,
            title,
            description,
            fullDescription,
            address,
            workTimeStart,
            workTimeEnd,
            workBreakStart,
            workBreakEnd,
            linkImage,
            linkSite,
            latitude,
            longitude
        )
    }
}

object GsonConverterInstance {
    fun createGsonConverter(type: Type, typeAdapter: Any): Converter.Factory {
        val gsonBuilder = GsonBuilder().apply { registerTypeAdapter(type, typeAdapter) }
        val gson = gsonBuilder.create()
        return GsonConverterFactory.create(gson)
    }
}