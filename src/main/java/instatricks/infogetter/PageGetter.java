package instatricks.infogetter;


import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

@Service
public class PageGetter {

    public String getPageCode(String URL) {
        StringBuffer buffer;
        try {
            URL url = new URL(URL);
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
