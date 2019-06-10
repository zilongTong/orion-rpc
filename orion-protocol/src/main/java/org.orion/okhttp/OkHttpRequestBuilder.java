package org.orion.okhttp;


import okhttp3.Headers.Builder;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.internal.http.HttpMethod;
import okio.BufferedSink;
import okio.Okio;
import okio.Source;
import org.springframework.http.HttpHeaders;
import org.springframework.util.MultiValueMap;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.Iterator;
import java.util.List;

/**
 * @ClassName OkHttpRequestBuilder
 * @Author Leo
 * @Description //TODO
 * @Date: 2019/5/29 15:41
 **/
public class OkHttpRequestBuilder {
    protected URI uri;
    protected final CommandContext context;
    private HttpHeaders httpHeaders;

    public OkHttpRequestBuilder(CommandContext context) {

        this.context = context;
        MultiValueMap<String, String> headers = context.getHeaders();
        this.httpHeaders = new HttpHeaders();
        Iterator var3 = headers.keySet().iterator();

        while (var3.hasNext()) {
            String key = (String) var3.next();
            this.httpHeaders.put(key, (List) headers.get(key));
        }
        this.uri = context.uri();
    }

    public Request toRequest() {
        Builder headers = new Builder();
        Iterator var2 = this.context.getHeaders().keySet().iterator();

        while (var2.hasNext()) {
            String name = (String) var2.next();
            List<String> values = (List) this.context.getHeaders().get(name);
            Iterator var5 = values.iterator();

            while (var5.hasNext()) {
                String value = (String) var5.next();
                headers.add(name, value);
            }
        }

        okhttp3.HttpUrl.Builder url = HttpUrl.get(this.uri).newBuilder();
        Iterator var9 = this.context.getParams().keySet().iterator();

        while (var9.hasNext()) {
            String name = (String) var9.next();
            List<String> values = (List) this.context.getParams().get(name);
            Iterator var15 = values.iterator();

            while (var15.hasNext()) {
                String value = (String) var15.next();
                url.addQueryParameter(name, value);
            }
        }

        RequestBody requestBody = null;

        if (this.context.getRequestEntity() != null && HttpMethod.permitsRequestBody(this.context.getMethod())) {
            MediaType mediaType = null;
            if (headers.get("Content-Type") != null) {
                mediaType = MediaType.parse(headers.get("Content-Type"));
            }

            requestBody = new OkHttpRequestBuilder.InputStreamRequestBody(this.context.getRequestEntity(), mediaType, this.context.getContentLength());
        }

        okhttp3.Request.Builder builder = (new okhttp3.Request.Builder()).url(url.build()).headers(headers.build()).method(this.context.getMethod(), requestBody);
        return builder.build();
    }

    static class InputStreamRequestBody extends RequestBody {
        private InputStream inputStream;
        private MediaType mediaType;
        private Long contentLength;

        InputStreamRequestBody(InputStream inputStream, MediaType mediaType, Long contentLength) {
            this.inputStream = inputStream;
            this.mediaType = mediaType;
            this.contentLength = contentLength;
        }

        public MediaType contentType() {
            return this.mediaType;
        }

        public long contentLength() {
            if (this.contentLength != null) {
                return this.contentLength;
            } else {
                try {
                    return (long) this.inputStream.available();
                } catch (IOException var2) {
                    return 0L;
                }
            }
        }

        public void writeTo(BufferedSink sink) throws IOException {
            Source source = null;

            try {
                source = Okio.source(this.inputStream);
                sink.writeAll(source);
            } finally {
                if (source != null) {
                    source.close();
                }

            }

        }
    }

}
