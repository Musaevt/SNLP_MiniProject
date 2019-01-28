package WebSearch;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class WikipediaSearcher {

    public static String getWikiURLNovel (String label){
        String []labels = label.split(" ");
        String tag = String.join("_", labels);
        return "https://en.wikipedia.org/wiki/"+tag+"_(novel)";
    }
    
    public static String getWikiPediaUrl (String label){
        String []labels = label.split(" ");
        String tag = String.join("_", labels);
        return "https://en.wikipedia.org/wiki/"+tag;
    }

    public static int verifyURL (String url) throws IOException {
        URL u = new URL ( url);
        HttpURLConnection huc =  (HttpURLConnection)  u.openConnection ();
        huc.setRequestMethod ("GET");  //OR  huc.setRequestMethod ("HEAD");
        huc.connect () ;
        int code = huc.getResponseCode() ;
        return code;
    }

    public static Elements extractWikipediaInfoBox (String url) throws IOException {

        Document doc = Jsoup.connect(url).userAgent("Mozilla/5.0").get();
        Elements infobox = doc.select("table[class*=infobox]");
        return infobox;
    }



}
