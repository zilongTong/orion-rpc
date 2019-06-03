package org.orion.protocol;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.util.MultiValueMap;
import org.springframework.util.ReflectionUtils;

import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * @ClassName CommandContext
 * @Author Leo
 * @Description //TODO
 * @Date: 2019/5/29 19:37
 **/
@Data
@AllArgsConstructor
public class CommandContext {

    private final String serviceId;
    private final String method;
    private final String uri;
    private final Boolean retryable;
    private final MultiValueMap<String, String> headers;
    private final MultiValueMap<String, String> params;
    private final InputStream requestEntity;
    private Long contentLength;

    public URI uri() {
        try {
            return new URI(this.uri);
        } catch (URISyntaxException var2) {
            ReflectionUtils.rethrowRuntimeException(var2);
            return null;
        }
    }
    public String toString() {
        StringBuffer sb = new StringBuffer("RibbonCommandContext{");
        sb.append("serviceId='").append(this.serviceId).append('\'');
        sb.append(", method='").append(this.method).append('\'');
        sb.append(", uri='").append(this.uri).append('\'');
        sb.append(", retryable=").append(this.retryable);
        sb.append(", headers=").append(this.headers);
        sb.append(", params=").append(this.params);
        sb.append(", requestEntity=").append(this.requestEntity);
        sb.append(", contentLength=").append(this.contentLength);
        sb.append('}');
        return sb.toString();
    }
}
