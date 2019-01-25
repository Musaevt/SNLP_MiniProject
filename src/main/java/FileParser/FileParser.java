package FileParser;

import Configuration.Config;
import Configuration.Constants;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * This class  parses the given file
 *
 * @author Zain
 */

public class FileParser {

    public ArrayList<FileRecord> fetchFileRecords(String fileName) throws Exception {

        File fileReader = new File(fileName);
        BufferedReader br = new BufferedReader(new InputStreamReader(
                new FileInputStream(fileReader), Config.Encoding));

        ArrayList<FileRecord> fileRecords = new ArrayList<>();
        String st;
        int count = 0;

        while ((st = br.readLine()) != null) {
            count++;
            if (count == 1 || st.compareTo(Constants.EmptyString) == 0)
                continue;

            String[] stringValidTokens = st.split(Constants.TabDelim);
            boolean fact = false;
            if (stringValidTokens.length > 3)
                if (stringValidTokens[2].compareTo(Constants.TrueValue) == 0)
                    fact = true;

            FileRecord fileRecord = new FileRecord(stringValidTokens[0], stringValidTokens[1], fact);
            fileRecords.add(fileRecord);

        }
        return fileRecords;
    }
}