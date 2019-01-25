package WebSearch;

public class RDFTriple {

    public String getFactId() {
        return factId;
    }

    public void setFactId(String factId) {
        this.factId = factId;
    }

    private String factId;
    private String domain;
    private String predicate;
    private String range;

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    private double score;

    public RDFTriple(String domain, String predicate, String range) {
        this.domain = domain;
        this.predicate = predicate;
        this.range = range;
    }


    public String getSubject() {
        return domain.toLowerCase();
    }

    public String getJoinedSubject (){
        return domain.replaceAll(" ", "_");
    }

    public void setSubject(String subject) {
        this.domain = subject;
    }

    public String getPredicate() {
        return predicate;
    }


    public String getObject() {
        return range.toLowerCase();
    }

    public void setObject(String object) {
        this.range = object;
    }

    @Override
    public String toString() {
        return domain + " " + predicate + " " + range+" "+score;
    }

    public String[] toArray() {
        String[] arr = new String[3];
        arr[0] = domain;
        arr[1] = predicate;
        arr[2] = range;

        return arr;
    }



    public String getSerializeFormat (){
        String format = "<http://swc2017.aksw.org/task2/dataset/"+factId+"><http://swc2017.aksw.org/hasTruthValue>\""
                +score+
                "\"^^<http://www.w3.org/2001/XMLSchema#double> .";
        return format;
    }

}