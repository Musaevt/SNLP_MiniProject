package Configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Constants {
    public static final String TrueValue = "1.0";
    public static final String EmptyString = "";
    public static final String TabDelim = "\t";

    public static final String  RoleLabel = "role";
    public static final String  SpouseLabel = "spouse";
    public static final String  AwardLabel = "Award";
    public static final String  BirthPlaceLabel = "birth place";
    public static final String  DeathPlaceLabel = "death place";
    public static final String  TeamLabel = "team";
    public static final String  FoundationLabel = "foundation";
    public static final String  StarLabel = "star";
    public static final String  AuthorLabel = "author";
    public static final String  SubsidoryLabel = "subsidory";
    public static final String  SubordinateLabel = "subordinate";

    
    public static double FactTrue = 1.0;
    public static double FactFalse = 0.0;

    public static HashMap<String,String> CLASSIFIER;

    public static HashMap<String, List<String>> FREQUENTWORDS;

    static {
        intializeClassifierMapping ();
        intializeFrequencyWords();
    }

    private static void intializeClassifierMapping (){
        CLASSIFIER = new HashMap<>();
        CLASSIFIER.put("author","author");

        CLASSIFIER.put("star","star");
        CLASSIFIER.put("nokia", "star");

        CLASSIFIER.put("subsidiary", "subsidiary");

        CLASSIFIER.put("office","role");

        CLASSIFIER.put("spouse","spouse");
        CLASSIFIER.put("team","team");

        CLASSIFIER.put("nascence place","birth place");
        CLASSIFIER.put("birth place","birth place");
        CLASSIFIER.put("death place", "death place");

        CLASSIFIER.put("last place","death place");

        CLASSIFIER.put("foundation place", "foundation place");
        CLASSIFIER.put("innovation place","foundation place");

        CLASSIFIER.put("generator", "author");
        CLASSIFIER.put("award","award");
        CLASSIFIER.put("honour", "award");
        CLASSIFIER.put("subordinate","subsidiary");
        CLASSIFIER.put("office","role");
        CLASSIFIER.put("better half","spouse");
        CLASSIFIER.put("team","team");
        CLASSIFIER.put("role","role");
        CLASSIFIER.put("squad","team");
    }

    private static void intializeFrequencyWords () {
        FREQUENTWORDS = new HashMap<>();

        //Role
        ArrayList<String> roleMap = new ArrayList<>();
        roleMap.add("preceded by");
        roleMap.add("president of");
        roleMap.add("succeeded by");
        roleMap.add("politician");
        roleMap.add("prime minister of");
        roleMap.add("leader of opposition");
        roleMap.add("minister of");
        roleMap.add("chancellor of");

        //Spouse
        ArrayList<String> spouseMap = new ArrayList<>();
        spouseMap.add("spouse");
        spouseMap.add("partner");
        spouseMap.add("wife");
        spouseMap.add("husband");
        spouseMap.add("boyfriend");
        spouseMap.add("girlfriend");
        spouseMap.add("spouse(s)");
        spouseMap.add("partner(s)");

        ArrayList<String> deathPlaceMap = new ArrayList<>();
        deathPlaceMap.add("deathplace");
        deathPlaceMap.add("died");

        ArrayList<String> birthPlaceMap = new ArrayList<>();
        birthPlaceMap.add("birthplace");
        birthPlaceMap.add("born");

        ArrayList<String> foundationMap = new ArrayList<>();
        foundationMap.add("founded");

        ArrayList<String> teamMap = new ArrayList<>();
        teamMap.add("career history");

        ArrayList<String> authorMap = new ArrayList<>();
        authorMap.add("author");
        authorMap.add("writer");

        ArrayList<String> awardMap = new ArrayList<>();
        awardMap.add("notable awards");
        awardMap.add("awards");

        ArrayList<String> subordinateMap = new ArrayList<>();
        subordinateMap.add("fate");
        subordinateMap.add("parent");
        subordinateMap.add("owned by");

        ArrayList<String> starringMap = new ArrayList<>();
        starringMap.add("starring");


        FREQUENTWORDS.put(Constants.RoleLabel,roleMap);
        FREQUENTWORDS.put(Constants.SpouseLabel,spouseMap);
        FREQUENTWORDS.put(Constants.DeathPlaceLabel,deathPlaceMap);
        FREQUENTWORDS.put(Constants.BirthPlaceLabel,birthPlaceMap);
        FREQUENTWORDS.put(Constants.FoundationLabel,foundationMap);
        FREQUENTWORDS.put(Constants.TeamLabel,teamMap);
        FREQUENTWORDS.put(Constants.AuthorLabel, authorMap);
        FREQUENTWORDS.put(Constants.AwardLabel, awardMap);
        FREQUENTWORDS.put(Constants.SubordinateLabel, subordinateMap);
        FREQUENTWORDS.put(Constants.SubsidoryLabel, subordinateMap);
        FREQUENTWORDS.put(Constants.StarLabel, starringMap);

    }
}
