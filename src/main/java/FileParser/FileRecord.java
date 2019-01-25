package FileParser;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;


/**
 * This class holds a single record from the given tsv file
 *
 * @author Zain
 */
public class FileRecord {


    private String recordId;
    private String sentence;
    private boolean factSatus;


    public FileRecord(String recordId, String sentence, boolean factSatus) throws IOException, ClassNotFoundException, ParserConfigurationException, SAXException {
        this.recordId = recordId;
        this.sentence = sentence;
        this.factSatus = factSatus;
    }


    public String getRecordId() {
        return recordId;
    }

    public String getSentence() {
        return sentence;
    }

}
