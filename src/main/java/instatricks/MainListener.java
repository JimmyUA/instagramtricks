package instatricks;

import instatricks.entity.Node;
import instatricks.entity.Source;
import instatricks.infogetter.JsonGetter;
import instatricks.infogetter.NodesGetter;
import instatricks.infogetter.PageGetter;
import instatricks.popularhashtags.MostPopularHashTagsGetter;
import instatricks.popularhashtags.getters.ExcelMapper;
import instatricks.saveimage.ImageSaver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;


@Controller
@EnableAutoConfiguration
@EnableScheduling
@ComponentScan({"instatricks"})
public class MainListener {

    private final String DENIS_ACCOUNT_URL = "https://www.instagram.com/denisantipanov/?hl=ru";


    @RequestMapping("/home")
    @ResponseBody
    public String home() {
        return "home";
    }

    public static void main(String[] args) {
        SpringApplication.run(MainListener.class);
        new MainListener().getNewPosts();
    }

    @RequestMapping("/tags")
    @ResponseBody
    public String makeFile() {

        return "home";
    }


    @Scheduled(initialDelay = 1000 * 60, fixedDelay = 1000 * 60 * 60)
    public void getNewPosts() {
        System.out.println("started");
        PageGetter pageGetter = new PageGetter();
        String pageCode = pageGetter.getPageCode(DENIS_ACCOUNT_URL);

        final JsonGetter jsonGetter = new JsonGetter();
        String json = jsonGetter.findJsonPart(pageCode);
        String mediaPart = jsonGetter.cutToMedia(json);

        List<Node> nodes = new NodesGetter().getNodes(mediaPart);

        for (Node node : nodes
                ) {
            saveContent(node);
        }
    }

    private void saveContent(Node node) {
        String postDirectoryPath = getPostPath(node) + "/post" + node.getId() + LocalDate.now().toString();

        String caption = node.getCaption();
        if (caption.contains("0938613024 Денис")) {
            caption = replaceContacts(caption);
            saveComment(caption, postDirectoryPath);
            final File postPathFile = new File(postDirectoryPath);
            if (!postPathFile.exists()) {
                postPathFile.mkdirs();
            }

            String mainImageURL = node.getDisplay_src();
            new ImageSaver().saveImage(mainImageURL, postDirectoryPath + "/main.jpg");

            saveAllImages(node, postDirectoryPath);
        }


    }

    private String replaceContacts(String caption) {
        return caption.replace("0938613024", "0993551572").
                replace("Денис", "Сергей");
    }

    private void saveComment(String caption, String postDirectoryPath) {
        try {
            FileWriter fileWriter = new FileWriter(postDirectoryPath + "/comment.txt");
            fileWriter.write(caption.toCharArray());
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace(); // TODO log here
            throw new RuntimeException(e);
        }
    }

    private void saveAllImages(Node node, String postDirectoryPath) {
        List<Source> sources = node.getThumbnail_resources();
        Integer counter = 1;
        for (Source source : sources
                ) {
            savePictureSRC(source, postDirectoryPath + "/" + counter++ + ".jpg");
        }
    }

    private void savePictureSRC(Source source, String postDirectoryPath) {
        String srcURL = source.getSrc();
        new ImageSaver().saveImage(srcURL, postDirectoryPath);
    }


    private String getPostPath(Node node) {
        String mainDirectoryPath = "/home/Denis/Instagram/Posts";

        return mainDirectoryPath;
    }
}
