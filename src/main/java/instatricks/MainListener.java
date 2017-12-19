package instatricks;

import instatricks.entity.Node;
import instatricks.entity.Source;
import instatricks.infogetter.JsonGetter;
import instatricks.infogetter.NodesGetter;
import instatricks.infogetter.PageGetter;
import instatricks.saveimage.ImageSaver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;


@Controller
@EnableAutoConfiguration
@EnableScheduling
public class MainListener {

    @RequestMapping("/")
    @ResponseBody
    public String home(){
        return "home";
    }
    public static void main(String[] args) {
        SpringApplication.run(MainListener.class);
        new MainListener().getNewPosts();
    }

    @Scheduled(initialDelay = 1000 * 60, fixedDelay = 1000 * 60)
    public void getNewPosts(){
        System.out.println("started");
        PageGetter pageGetter = new PageGetter();
        String pageCode = pageGetter.getPageCode();

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
        String postDirectoryPath = getPostPath(node) + "/post" + node.getId();

        final File postPathFile = new File(postDirectoryPath);
        if (!postPathFile.exists()){
            postPathFile.mkdirs();
        }

        String mainImageURL = node.getDisplay_src();
        new ImageSaver().saveImage(mainImageURL, postDirectoryPath + "/main.jpg");

        saveAllImages(node, postDirectoryPath);

        saveComment(node.getCaption(), postDirectoryPath);

    }

    private void saveComment(String caption, String postDirectoryPath) {
        try {
            FileWriter fileWriter = new FileWriter(postDirectoryPath + "/comment.txt");
            fileWriter.write(caption.toCharArray());
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e){
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
        String mainDirectoryPath = "D:/Denis/Instagram/Posts";

        return mainDirectoryPath;
    }
}
