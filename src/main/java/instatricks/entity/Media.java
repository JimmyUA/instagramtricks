package instatricks.entity;

import lombok.ToString;

import java.util.List;

@ToString
public class Media {

    List<Node> nodes;

    public List<Node> getNodes() {
        return nodes;
    }

    public void setNodes(List<Node> nodes) {
        this.nodes = nodes;
    }
}

