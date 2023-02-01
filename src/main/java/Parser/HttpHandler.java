package Parser;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

public class HttpHandler {

    String phoneNumber;
    public HttpHandler(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }

    protected String linkBuilder(String phoneNumber) {
        String baseUrl = "https://receive-sms.cc/US-Phone-Number/";
        return baseUrl + phoneNumber.replaceAll("\\s", "").replaceAll("[-()+]", "");
    }

    protected String getHtml() throws IOException {
        Response response;
        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(linkBuilder(phoneNumber))
                    .method("GET", null)
                    .build();
            response = client.newCall(request).execute();
        } catch (Exception e){
            System.out.println("Wasn't able to reach https://receive-sms.cc/. Probably VPN or Proxy connection is needed");
            return "false";
        }
        if(!response.isSuccessful()){
            return "false";
        } else {
            assert response.body() != null;
            return response.body().string();
        }

    }
}
