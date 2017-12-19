package instatricks.infogetter;


import com.google.gson.Gson;
import instatricks.entity.Media;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;


public class PageGetter {
    private final String DENIS_ACCOUNT_URL = "https://www.instagram.com/denisantipanov/?hl=ru";

    public String getPageCode() {
        StringBuffer buffer;
        try {
            URL url = new URL(DENIS_ACCOUNT_URL);
            InputStream is = url.openStream();
            int ptr;
            buffer = new StringBuffer();
            while ((ptr = is.read()) != -1) {
                buffer.append((char) ptr);
            }
        } catch (IOException e) {
            e.printStackTrace();   // TODO log here
            throw new RuntimeException(e);
        }

        return buffer.toString();
    }

}
