package SNLP_Project;

import Configuration.Config;
import FileParser.FileRecord;
import TextProcessor.TextProcessor;
import WebSearch.RDFTriple;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 */
public class App {

    public static void main(String[] args) throws Exception {

        if (args.length!=0 &&args[0]!=null) {
            Config.FILE_NAME = args[0];
        }

        if (args.length!=0 &&args[1]!=null) {
            Config.Encoding = args[1];
        }

        if (args.length!=0 && args[2]!=null){
            Config.Outputfile_Name = args[2];
        }

        List<FileRecord> list = new FileParser.FileParser().fetchFileRecords(Config.FILE_NAME);
        List<RDFTriple> triplesList = new ArrayList<>();
        int i = 0;
        for (FileRecord record : list) {
            i++;
            System.out.println(i+"--");
            RDFTriple triple = TextProcessor.constructTripleFromClause(record.getSentence());
            triple.setFactId(record.getRecordId());


            triplesList.add(triple);
            System.out.println(triple);
        }

        BufferedWriter writer = new BufferedWriter(new FileWriter(Config.Outputfile_Name));

        for (RDFTriple tr: triplesList){
            writer.write(tr.getSerializeFormat());
            writer.newLine();
        }
        writer.close();

    }


}
