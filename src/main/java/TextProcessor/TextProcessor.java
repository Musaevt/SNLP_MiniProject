package TextProcessor;

import Configuration.Constants;
import WebSearch.RDFTriple;


public class TextProcessor {

    public static  String remove_parenthesis(String input_string){

            return input_string.replaceAll("\\s*\\([^\\)]*\\)\\s*", " ");
    }

    private static RDFTriple constructTripleFromRegularSequence(String input, String word, String relation) {
        int predIndex = input.indexOf(word);
        String subject = input.substring(0, predIndex);
        subject = normalize(subject);
        String object = input.substring(predIndex + word.length());
        object = normalize(object);
        
        return new RDFTriple(subject, relation, object);
    }

    private static RDFTriple constructTripleFromFlippedSubordinate(String input){
        int index = input.indexOf("subordinate is");
        String object = input.substring(0,index);
        String subject = input.substring(index+14);

        subject = normalize(subject).trim();
        object = normalize(object).trim();

        return new RDFTriple(subject, Constants.SubordinateLabel, object);
    }

    private static RDFTriple constructFlippedSequence(String input, String word, String relation) {
        int predIndex = input.indexOf(" is");
        String object = input.substring(0, predIndex);
        int honIndex = input.indexOf(word);
        String subject = input.substring(predIndex + 3, honIndex);
        subject = normalize(subject);
        object = normalize(object);

        return new RDFTriple(subject, relation, object);
    }

    private static RDFTriple constructTripleFromMovieRegularCase(String input){
        String subject = input.substring("Stars ".length(), input.indexOf(" has been"));
        String object = input.substring(input.lastIndexOf(' ') + 1);
        object = normalize(object);

        return new RDFTriple(subject, Constants.StarLabel, object);
    }

    private static RDFTriple constructTripleFromMovieFlippedCase(String input){
        int predIndex = input.indexOf("stars");
        String subject = input.substring(0, predIndex);
        String object = input.substring(predIndex + "stars".length() + 1);
        subject = normalize(subject);
        object = normalize(object);
        
        return new RDFTriple(subject, Constants.StarLabel, object);
    }

    private static RDFTriple constructTripleFromSubsidiaryClause(String input) {
        RDFTriple triples = constructTripleFromRegularSequence(input, "subsidiary", Constants.SubsidoryLabel);
        String sub = triples.getSubject();
        String obj = triples.getObject();
        triples.setSubject(obj);
        triples.setObject(sub);
        return triples;
    }

    public static RDFTriple constructTripleFromClause (String input) {
        
        if (input.contains("award is"))
            return constructTripleFromRegularSequence(input, "award is", Constants.AwardLabel);

        if (input.contains("award"))
            return constructFlippedSequence(input, "award", Constants.AwardLabel);

        if (input.contains("honour"))
            return constructFlippedSequence(input, "honour", Constants.AwardLabel);

        if (input.contains("nascence place"))
            return constructFlippedSequence(input, "nascence place", Constants.BirthPlaceLabel);

        if (input.contains("birth place is"))
            return constructTripleFromRegularSequence(input, "birth place is", Constants.BirthPlaceLabel);

        if (input.contains("birth place"))
            return constructFlippedSequence(input, "birth place", Constants.BirthPlaceLabel);

        if (input.contains("death place is"))
            return constructTripleFromRegularSequence(input, "death place is", Constants.DeathPlaceLabel);

        if (input.contains("death place"))
            return constructFlippedSequence(input, "death place", Constants.DeathPlaceLabel);

        if (input.contains("last place"))
            return constructFlippedSequence(input, "last place", Constants.DeathPlaceLabel);

        if (input.contains("spouse is"))
            return constructTripleFromRegularSequence(input, "spouse is", Constants.SpouseLabel);

        if (input.contains("spouse"))
            return constructFlippedSequence(input, "spouse", Constants.SpouseLabel);

        if (input.contains("better half"))
            return constructFlippedSequence(input, "better half", Constants.SpouseLabel);

        if (input.contains("role"))
            return constructFlippedSequence(input, "role", Constants.RoleLabel);

        if (input.contains("office is"))
            return constructTripleFromRegularSequence(input, "office is", Constants.RoleLabel);

        if (input.contains("office"))
            return constructFlippedSequence(input, "office", Constants.RoleLabel);

        if (input.contains("team is"))
            return constructTripleFromRegularSequence(input, "team is", Constants.TeamLabel);

        if (input.contains("team"))
            return constructFlippedSequence(input, "team", Constants.TeamLabel);

        if (input.contains(" squad."))
            return constructFlippedSequence(input, "squad", Constants.TeamLabel);

        if (input.contains("foundation place is"))
            return constructTripleFromRegularSequence(input, "foundation place is", Constants.FoundationLabel);

        if (input.contains("foundation place"))
            return constructFlippedSequence(input, "foundation place", Constants.FoundationLabel);

        if (input.contains("innovation place"))
            return constructFlippedSequence(input, "innovation place", Constants.FoundationLabel);

            // Since Movie is complicated case:
        if (input.startsWith("Stars"))
            return constructTripleFromMovieRegularCase(input);

        if (input.contains("stars"))
            return constructTripleFromMovieFlippedCase(input);

        if (input.contains(" generator."))
            return constructFlippedSequence(input, "generator", Constants.AuthorLabel);

        if (input.contains("author is"))
            return constructTripleFromRegularSequence(input, "author is", Constants.AuthorLabel);

        if (input.contains("author"))
            return constructFlippedSequence(input, "author", Constants.AuthorLabel);


        if (input.contains("subordinate is")) {
            return constructTripleFromFlippedSubordinate(input);
        }

        if (input.contains("subordinate"))
            return constructFlippedSequence(input, "subordinate", Constants.SubsidoryLabel);



        if (input.contains("subsidiary")) {
            return constructTripleFromSubsidiaryClause(input);
        }
            return null;
    }

    public static String normalize(String input) {
        if (input.contains("'")) {
            int index = input.lastIndexOf("'");
            input = input.substring(0, index);
        }


        input = input.split(",")[0];
        return input.replaceAll("\\.", "").trim();
    }

    public static  String cleanWikiText (String input){
        if (input.contains("'")) {
            int apIndex = input.lastIndexOf("'");
            input = input.substring(0, apIndex);
        }


        input = input.replaceAll("[\\d-]","");
        return input.replaceAll("\\.", "").trim();
    }


}
