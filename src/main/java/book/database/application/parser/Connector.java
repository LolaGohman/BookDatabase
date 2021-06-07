package book.database.application.parser;

import com.ning.http.client.AsyncHttpClient;
import com.ning.http.client.Request;
import com.ning.http.client.Response;
import org.springframework.stereotype.Component;

@Component
public class Connector {

    private final AsyncHttpClient asyncHttpClient;

    public Connector() {
        asyncHttpClient = new AsyncHttpClient();
    }

    public Response executeRequest(String url) {
        final Request request = asyncHttpClient.prepareGet(url)
            .addHeader("Accept-Encoding", "gzip, deflate, br")
            .addHeader("Accept", "*/*")
            .build();

        try {
            return asyncHttpClient.executeRequest(request).get();
        } catch (Exception e) {
            return null;
        }
    }
}
