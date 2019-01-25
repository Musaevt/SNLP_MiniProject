package WebSearch;
import Configuration.Constants;
import TextProcessor.TextProcessor;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.List;

public class RecordProcessor {

    private static boolean seenCareerHistory = false;

    public static RDFTriple processRecords(RDFTriple record) {
        String predicate = record.getPredicate();
        RDFTriple triple = processWikipediaRecord (record);
        return triple;
    }

    private static RDFTriple processWikipediaRecord (RDFTriple record){
        String url = WikipediaSearcher.getWikiPediaUrl(record.getJoinedSubject());
        try {
            if (WikipediaSearcher.verifyURL(url) == 200 || WikipediaSearcher.verifyURL(url) == 304) {
                Elements infoBox = WikipediaSearcher.extractWikipediaInfoBox(url);

                if (infoBox.size() == 0) {
                    if (record.getPredicate().equals(Constants.AuthorLabel)){
                        String novelUrl = WikipediaSearcher.getWikiURLNovel(record.getJoinedSubject());
                        if (WikipediaSearcher.verifyURL(novelUrl) == 200 || WikipediaSearcher.verifyURL(novelUrl) == 304){
                            infoBox = WikipediaSearcher.extractWikipediaInfoBox(novelUrl);
                        }
                    }
                }

                record = processRoleRecord(record, infoBox);
            }
        } catch (IOException |IndexOutOfBoundsException ex) {
            record.setScore(-1.0);
        }
        return record;
    }

    private static RDFTriple processRoleRecord(RDFTriple record, Elements infoBox) {

        seenCareerHistory = false;
        Elements infoboxRows = infoBox.get(0).select("tbody").select("tr");
        boolean factStatus = false;
        for (Element e : infoboxRows) {
            String line = e.text();

            if (record.getPredicate().equals(Constants.TeamLabel) && isTeamRecordExists (line.toLowerCase())){
                if (verifyRoleFact(TextProcessor.normalize(line.toLowerCase()), record.getObject())) {
                    factStatus = true;
                    break;
                }
            }
            else {
                if (doesLineContainRelevantWord(line.toLowerCase(), record.getPredicate()))
                    if (verifyRoleFact(TextProcessor.cleanWikiText(line.toLowerCase()), record.getObject())) {
                        factStatus = true;
                        break;
                    }
            }
        }

        if (factStatus == true)
            record.setScore(Constants.FactTrue);
        else
            record.setScore(Constants.FactFalse);

        return record;
    }

    // since team records are different
    private static boolean isTeamRecordExists (String line){
        if (line.contains("as player:") || line.contains("career history")){
            seenCareerHistory = true;
        }
        return seenCareerHistory;
    }

    private static boolean doesLineContainRelevantWord(String line, String predicate) {
        List<String> frequentWords = Constants.FREQUENTWORDS.get(predicate);
        if (frequentWords.parallelStream().anyMatch(line::contains))
            return true;
        return false;
    }

    private static boolean verifyRoleFact(String line, String relVal) {
        relVal = TextProcessor.remove_parenthesis (relVal);
        if (line.contains(relVal))
            return true;
        return false;
    }


}
