package ru.bitelecom.selenium.internet;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.BasicHttpContext;

import java.io.File;
import java.io.IOException;

import static org.apache.commons.io.FileUtils.copyInputStreamToFile;

public class Utils {

    public static void saveFile(String link, String fileName) {

        //Set file to save
        File fileToSave = new File("C:\\Users\\drkuznetsov\\IdeaProjects\\bi_telecom\\" + fileName);

        //Download file using default org.apache.http client
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(link);
        HttpResponse response = null;

        try {
            response = httpClient.execute(httpGet, new BasicHttpContext());
        } catch (
                IOException e) {
            e.printStackTrace();
        }

        //Save file on disk
        try {
            copyInputStreamToFile(response.getEntity().getContent(), fileToSave);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
