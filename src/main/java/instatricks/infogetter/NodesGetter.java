package instatricks.infogetter;

import com.google.gson.Gson;
import instatricks.entity.Media;
import instatricks.entity.Node;

import java.util.List;


public class NodesGetter {


    public List<Node> getNodes(String jsonInput) {
        Gson gson = new Gson();
        Media media = gson.fromJson(jsonInput, Media.class);

        return media.getNodes();
    }
}
