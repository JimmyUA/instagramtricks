package instatricks.autopost;

import instatricks.autopost.pagevalidate.PageValidatableData;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;

import java.util.Arrays;
import java.util.List;

public class PageValidator {

    private static final String[] GOOD_DATA = new String[]{"kiev", "ukraine", "киев", "украина", " 099", " 095", " 096", " 097"
        , " 050", " 066", " 067", " 068", " 063", " 073", "а", "б", "в", "г", "е", "ж", "з", "и", "й", "к", "л", "м"
            , "н", "о", "п", "р", "с", "т", "у", "ф", " +38", " 38",  "(099", "(095", "(096", "(097"
            , "(050", "(066", "(067", "(068", "(063", "(073",};

    private static final String[] BAD_DATA = new String[]{"+79", " 79", " +7", "москва", "россия", "магазин"};

    private static PageValidatableData  validatableDataGlobal;


    public static boolean validate(PageValidatableData validatableData){

        validatableDataGlobal = validatableData;

        String followers = validatableData.getFollowers();

        String description = validatableData.getDescription();

        String followedOrNot = validatableData.getFollowedOrNot();

        boolean enoughFollowers = isEnoughFollowers(followers);
        boolean goodDescription = isGoodDescription(description);
        boolean notFollowedYet = isNotFollowedYet(followedOrNot);
        return enoughFollowers && goodDescription && notFollowedYet;
    }

    private static boolean isNotFollowedYet(String followedOrNot) {
        if (followedOrNot.equals("Стежити")){
            return true;
        }else {
            validatableDataGlobal.setErrorMessage("Already followed");
            return false;
        }
    }

    private static boolean isGoodDescription(String description) {

        List<String> badData = Arrays.asList(BAD_DATA);
        for (String bad : badData
                ) {
            if (StringUtils.containsIgnoreCase(description, bad)){
                validatableDataGlobal.setErrorMessage("Contains data from bad strings array " + bad);
                return false;
            }
        }

        List<String> goodData = Arrays.asList(GOOD_DATA);
        for (String good : goodData
             ) {
            if (StringUtils.containsIgnoreCase(description, good)){
                return true;
            }
        }

//        validatableDataGlobal.setErrorMessage("Did not found good data inside");
        return true;
    }

    private static boolean isEnoughFollowers(String followers) {
//        if (followers.contains("тис")){
//            return true;
//        } else {
//            try {
//                if (Integer.parseInt(followers) > 4000) {
//                    return true;
//                }
//            } catch (NumberFormatException e){
//                e.printStackTrace();
//            }
//            validatableDataGlobal.setErrorMessage("Not enough followers " + followers);
//            return false;
//        }
        return true;
    }
}
