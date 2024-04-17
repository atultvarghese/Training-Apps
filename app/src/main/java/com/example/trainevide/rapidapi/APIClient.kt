import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType

class APIClient private constructor() {
    private val client: OkHttpClient = OkHttpClient()

    fun search(searchQuery: String, callback: Callback) {
        val mediaType = "application/json".toMediaType()
        val body = RequestBody.create(mediaType, "{\"search\": \"$searchQuery\"}")
        val request = Request.Builder()
            .url("https://trains.p.rapidapi.com/")
            .post(body)
            .addHeader("content-type", "application/json")
            .addHeader("X-RapidAPI-Key", "88da2fad09msh72166c3ffa1cf14p162567jsna1f08fcb4387")
            .addHeader("X-RapidAPI-Host", "trains.p.rapidapi.com")
            .build()
        client.newCall(request).enqueue(callback)
    }

    companion object {
        fun create(): APIClient {
            return APIClient()
        }
    }
}
