package instatricks.popularhashtags;

import instatricks.infogetter.PageGetter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MostPopularHashTagsGetter {

    private final String RESOURCE_URL = "https://websta.me/search/";

    public List<String> getMostPopularTagsByWord(String word){
        Document document = null;
        try {
            document = Jsoup.connect(RESOURCE_URL + word).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (document!= null) {
            List<Element> elements = document.getElementsByAttributeValue("class", "tag-style");
            return elements.stream().map(Element::ownText).collect(Collectors.toList());
        }
        return null;
    }


    public List<String> getAllPopularTags(String filePath){
        String fileText = getFileText(filePath);

        String[] words = fileText.split("\r\n");

        List<String> result = new ArrayList<>();

        for (String word : words
             ) {
            result.addAll(getMostPopularTagsByWord(word));
        }
        return result;
    }

    private String getFileText(String filePath) {
        File file = new File(filePath);
        StringBuilder wordList = new StringBuilder();
        int i;
        try(Reader fileInputStream = new InputStreamReader(new FileInputStream(file), "UTF-8")){
            while ((i = fileInputStream.read()) != -1){
                wordList.append((char)i);
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        return wordList.toString();
    }

}
