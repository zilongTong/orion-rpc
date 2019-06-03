package org.orion.utils;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.util.CollectionUtils;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * okhttp客户端
 */
@Slf4j
public class OkHttpUtil {
    private static OkHttpClient OK_HTTP_CLIENT = new OkHttpClient.Builder().readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS).writeTimeout(120, TimeUnit.SECONDS)
            .connectionPool(new ConnectionPool(10, 5, TimeUnit.MINUTES)).build();

    /**
     * @param url          请求的url
     * @param queries      请求的参数 没有可以传null
     * @param headerParams 请求header中的参数 没有可以传null
     */
    public static Response get(String url, Map<String, String> queries, Map<String, String> headerParams) {
        StringBuffer stringBuffer = new StringBuffer(url);
        if (!CollectionUtils.isEmpty(queries)) {
            stringBuffer.append("?");
            queries.forEach((k, v) -> stringBuffer.append(k).append("=").append(v).append("&"));
        }
        Request.Builder builder = new Request.Builder().url(stringBuffer.toString());
        if (!CollectionUtils.isEmpty(headerParams)) {
            Headers headers = Headers.of(headerParams).newBuilder().build();
            builder.headers(headers);
        }
        return client(builder.build());
    }

    /**
     * @param url     请求的url
     * @param queries 请求的参数 没有可以传null
     */
    public static Response get(String url, Map<String, String> queries) {
        StringBuffer stringBuffer = new StringBuffer(url);
        if (!CollectionUtils.isEmpty(queries)) {
            stringBuffer.append("?");
            queries.forEach((k, v) -> stringBuffer.append(k).append("=").append(v).append("&"));
        }
        Request request = new Request.Builder().url(stringBuffer.toString()).build();
        return client(request);
    }

    /**
     * post
     *
     * @param url          请求的url
     * @param params       post form 提交的参数
     * @param headerParams 请求header中的参数 没有可以传null
     * @return
     */
    public static Response post(String url, Map<String, String> params, Map<String, String> headerParams) {
        FormBody.Builder formBuilder = new FormBody.Builder();
        // 添加参数
        if (!CollectionUtils.isEmpty(params)) {
            params.forEach(formBuilder::add);
        }
        Request.Builder builder = new Request.Builder().url(url).post(formBuilder.build());
        if (!CollectionUtils.isEmpty(headerParams)) {
            Headers headers = Headers.of(headerParams).newBuilder().build();
            builder.headers(headers);
        }
        return client(builder.build());
    }

    /**
     * post
     *
     * @param url    请求的url
     * @param params post form 提交的参数
     * @return
     */
    public static Response post(String url, Map<String, String> params) {
        FormBody.Builder builder = new FormBody.Builder();
        // 添加参数
        if (!CollectionUtils.isEmpty(params)) {
            params.forEach(builder::add);
        }
        Request request = new Request.Builder().url(url).post(builder.build()).build();
        return client(request);
    }

    /**
     * @param url          请求的url
     * @param jsonParams   post 提交的参数
     * @param headerParams 请求header中的参数 没有可以传null
     */
    public static Response postJsonParams(String url, String jsonParams, Map<String, String> headerParams) {
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonParams);
        Request.Builder builder = new Request.Builder().url(url).post(requestBody);
        if (!CollectionUtils.isEmpty(headerParams)) {
            Headers headers = Headers.of(headerParams).newBuilder().build();
            builder.headers(headers);
        }
        return client(builder.build());
    }

    /**
     * @param url        请求的url
     * @param jsonParams post 提交的参数
     */
    public static Response postJsonParams(String url, String jsonParams) {
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonParams);
        Request request = new Request.Builder().url(url).post(requestBody).build();
        return client(request);
    }

    public static Response postJsonParams(String url, Object jsonParams) {
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), JSON.toJSONString(jsonParams));
        Request request = new Request.Builder().url(url).post(requestBody).build();
        return client(request);
    }

    public static Response client(Request request) {
        Response response = null;
        try {
            response = OK_HTTP_CLIENT.newCall(request).execute();
            if (response.isSuccessful() && response.body() != null) {
                return response;
            }
        } catch (Exception e) {
            log.error("okhttp3 error, url:{}", request.url().toString(), e);
        } finally {
            if (response != null) {
                response.close();
            }
        }
        return null;
    }
}
