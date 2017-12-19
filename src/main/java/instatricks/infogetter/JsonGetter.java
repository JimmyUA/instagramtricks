package instatricks.infogetter;

public class JsonGetter {

    private static final String JSON_START = "_sharedData = ";
    private static final String END_LINE = "</script>";
    private static final String MEDIA_START = "\"media\": ";
    private static final String MEDIA_END = "\"saved_media\": ";


    public  String findJsonPart(String page){
        int starJsonIndex = page.indexOf(JSON_START) + 14;

        String jsonPartStart = page.substring(starJsonIndex);
        int jsonEnd = jsonPartStart.indexOf(END_LINE);
        String json = jsonPartStart.substring(0, jsonEnd);

        return json;
    }

    public  String cutToMedia(String input){
        int start = input.indexOf(MEDIA_START) + 9;
        int end = input.indexOf(MEDIA_END);
        return input.substring(start, end - 2);
    }
}
