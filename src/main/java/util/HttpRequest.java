package util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class HttpRequest {
    private static final Logger log = LoggerFactory.getLogger(HttpRequest.class);

    private Map<String, String> headers = new HashMap<>();
    private Map<String, String> parameters = new HashMap<>();
    private RequestLine requestLine;

    public HttpRequest(InputStream in) {
        try {
            headers.clear();

            BufferedReader br = new BufferedReader(new InputStreamReader(in, "UTF-8"));
            String line = br.readLine();
            if (line == null) {
                return;
            }

            requestLine = new RequestLine(line);

            line = br.readLine();
            while (line != null && !line.equals("")) {
                log.debug("header: {}", line);
                String[] tokens = line.split(":");
                headers.put(tokens[0].trim(), tokens[1].trim());
                line = br.readLine();
            }

            if (getMethod().isPost()) {
                int contentLength = Integer.parseInt(headers.get("Content-Length"));
                String body = IOUtils.readData(br, contentLength);
                parameters = HttpRequestUtils.parseQueryString(body);
            } else if (getMethod().isGet()) {
                parameters = requestLine.getParameters();
            }
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    public HttpMethod getMethod() {
        return requestLine.getMethod();
    }

    public String getPath() {
        return requestLine.getPath();
    }

    public String getHeader(String key) {
        return headers.get(key);
    }

    public String getParameter(String key) {
        return parameters.get(key);
    }

    public Map<String, String> getParameters() {
        return parameters;
    }

    public Map<String, String> getCookies() {
        String cookieValue = headers.get("Cookie");
        if (cookieValue != null) {
            return HttpRequestUtils.parseCookies(cookieValue);
        } else {
            return Collections.emptyMap();
        }
    }
}
