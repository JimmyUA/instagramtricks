package instatricks;

import instatricks.popularhashtags.MostPopularHashTagsGetter;
import instatricks.popularhashtags.getters.ExcelMapper;


public class FindTags {

    private final static String INPUT_FILE_PATH = "D:/tags/tags.txt";
    private final static String OUTPUT_FILE_PATH = "D:/tags/tags.xls";

    public static void main(String[] args) {
        MostPopularHashTagsGetter tagsGetter = new MostPopularHashTagsGetter();
        ExcelMapper mapper = new ExcelMapper();

        mapper.mapToExcelFile(tagsGetter.getAllPopularTags(INPUT_FILE_PATH), OUTPUT_FILE_PATH);
    }
}
