package util;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RequestLineTest {

    @Test
    public void create_method() {
        RequestLine line = new RequestLine("GET /index.html HTTP/1.1");
        assertEquals(HttpMethod.GET, line.getMethod());
        assertEquals("/index.html", line.getPath());

        line = new RequestLine("POST /index.html HTTP/1.1");
        assertEquals(HttpMethod.POST, line.getMethod());
        assertEquals("/index.html", line.getPath());
    }

    @Test
    public void create_method_path_params() {
        RequestLine line = new RequestLine("GET /user/login?userId=user&password=pass HTTP/1.1");
        assertEquals(HttpMethod.GET, line.getMethod());
        assertEquals("/user/login", line.getPath());
        assertEquals(2, line.getParameters().size());
    }
}
