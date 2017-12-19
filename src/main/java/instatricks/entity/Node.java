package instatricks.entity;

import lombok.ToString;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ToString
public class Node {
    private String __typename;
    private Long id;
    private String comments_disabled;
    private Map<String, Long> dimensions = new HashMap<String, Long>();
    private String gating_info;
    private String media_preview;
    private Map<String, Long> owner = new HashMap<String, Long>();
    private String thumbnail_src;
    private List<Source> thumbnail_resources = new ArrayList();
    private Boolean is_video;
    private String code;
    private Integer date;
    private String display_src;
    private String caption;
    private Map<String, Integer> comments = new HashMap<String, Integer>();
    private Map<String, Integer> likes = new HashMap<String, Integer>();


    public String get__typename() {
        return __typename;
    }

    public void set__typename(String __typename) {
        this.__typename = __typename;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComments_disabled() {
        return comments_disabled;
    }

    public void setComments_disabled(String comments_disabled) {
        this.comments_disabled = comments_disabled;
    }

    public Map<String, Long> getDimensions() {
        return dimensions;
    }

    public void setDimensions(Map<String, Long> dimensions) {
        this.dimensions = dimensions;
    }

    public String getGating_info() {
        return gating_info;
    }

    public void setGating_info(String gating_info) {
        this.gating_info = gating_info;
    }

    public String getMedia_preview() {
        return media_preview;
    }

    public void setMedia_preview(String media_preview) {
        this.media_preview = media_preview;
    }

    public Map<String, Long> getOwner() {
        return owner;
    }

    public void setOwner(Map<String, Long> owner) {
        this.owner = owner;
    }

    public String getThumbnail_src() {
        return thumbnail_src;
    }

    public void setThumbnail_src(String thumbnail_src) {
        this.thumbnail_src = thumbnail_src;
    }

    public List<Source> getThumbnail_resources() {
        return thumbnail_resources;
    }

    public void setThumbnail_resources(List<Source> thumbnail_resources) {
        this.thumbnail_resources = thumbnail_resources;
    }

    public Boolean getIs_video() {
        return is_video;
    }

    public void setIs_video(Boolean is_video) {
        this.is_video = is_video;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getDate() {
        return date;
    }

    public void setDate(Integer date) {
        this.date = date;
    }

    public String getDisplay_src() {
        return display_src;
    }

    public void setDisplay_src(String display_src) {
        this.display_src = display_src;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public Map<String, Integer> getComments() {
        return comments;
    }

    public void setComments(Map<String, Integer> comments) {
        this.comments = comments;
    }

    public Map<String, Integer> getLikes() {
        return likes;
    }

    public void setLikes(Map<String, Integer> likes) {
        this.likes = likes;
    }
}
