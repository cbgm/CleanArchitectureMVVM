package com.christian.mvvmclean.core.utils

import com.christian.mvvmclean.data.model.PostDto
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.*
import okio.Buffer
import java.io.IOException

class FakeInterceptor : Interceptor {

    private companion object {
        var postDtos = ArrayList<PostDto>()
    }

    init {

        postDtos.add(
            PostDto(
                userId = 1,
                id = 123,
                body = "fsdfsdsdf",
                title = "test"
            )
        )
    }


    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response? {
        val response: Response?
        var code = 200

        val responseString: String = when (chain.request().method()) {
            "GET" -> {

                    getMockedSearchJson()

            }
            else -> {
                code = 500
                ""
            }
        }

        response = Response.Builder()
            .code(code)
            .message(responseString)
            .request(chain.request())
            .protocol(Protocol.HTTP_1_0)
            .body(
                ResponseBody.create(
                    MediaType.parse("application/json"),
                    responseString.toByteArray()
                )
            )
            .addHeader("content-type", "application/json")
            .build()


        return response
    }

    private fun getMockedSearchJson(): String {
        return GsonBuilder().create()
            .toJson(postDtos)
    }
}