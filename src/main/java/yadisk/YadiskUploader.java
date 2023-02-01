package yadisk;

import com.yandex.disk.rest.Credentials;
import com.yandex.disk.rest.ProgressListener;
import com.yandex.disk.rest.RestClient;
import com.yandex.disk.rest.exceptions.ServerException;
import com.yandex.disk.rest.json.Link;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class YadiskUploader {

    private Properties properties = new Properties();
    public void yadiskUploader(boolean isThereAFile) throws IOException, ServerException {
        properties.load(new FileReader(new File("src/main/resources/local.properties")));
        if(!isThereAFile) System.out.println(":<");
        else {
            Credentials credentials = new Credentials(properties.getProperty("yadisk.userName"), properties.getProperty("yadisk.authToken"));
            RestClient restClient = new RestClient(credentials);
            Link uploadLink = restClient.getUploadLink("link.txt", true);
            String filepath = "link.txt";
            restClient.uploadFile(uploadLink, false, new File(filepath), new ProgressListener() {
                @Override
                public void updateProgress(long loaded, long total) {
                    System.out.println("Uploaded: " + loaded + " / " + total);
                }

                @Override
                public boolean hasCancelled() {
                    return false;
                }
            });


        }
    }
}
