package com.feicuiedu.gitdroid.github.network;

import com.feicuiedu.gitdroid.github.login.UserRepo;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Token拦截器
 */
public class TokenInterceptor implements Interceptor {

    @Override public Response intercept(Chain chain) throws IOException {
        // 每次请求，都将加一个token值
        Request request = chain.request();
        Request.Builder builder = request.newBuilder();
        // 是否有token(注意空格)
        if (UserRepo.hasAccessToken()) {
            builder.header("Authorization", "token " + UserRepo.getAccessToken());
        }
        Response response = chain.proceed(builder.build());
        if (response.isSuccessful()) {
            return response;
        }
        if (response.code() == 401 || response.code() == 403) {
            throw new IOException("未经授权的！限制是每分钟10次！");
        } else {
            throw new IOException("响应码:" + response.code());
        }
    }
}
