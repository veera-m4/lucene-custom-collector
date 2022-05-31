package custom_collector;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import org.apache.lucene.document.Document;
import org.apache.lucene.search.*;
public class custom_collector  {
    private IndexSearcher searcher;
    public int totalHits=0;
    private long num;
    private int docBase;
    File file;
    public LinkedHashSet <Integer>set = new LinkedHashSet<Integer>();
    public custom_collector(IndexSearcher search )
    {
        this.searcher=search;

    }
    public void collect(TermQuery query,int n) throws IOException
    {
        int i=0;
        File file=new File("D:\\demo.txt");
        BufferedReader br=new BufferedReader(new FileReader(file));
        ArrayList<String> str = new ArrayList<String>();
        String s;
        while((s = br.readLine()) != null)
        {
            s=s.replace("\\s","");
            str.add(s);

        }
        Sort sort = new Sort(SortField.FIELD_SCORE, new SortField("fieldname", SortField.Type.STRING,true));

        TopDocs d=searcher.search(query,n,sort);
        ScoreDoc[] result=d.scoreDocs;

        for(String st : str)
        {
            for(int a=0;a<d.totalHits;a++) {
                Document document = searcher.doc(result[a].doc);
                if (document.get("fieldname").indexOf(st) !=-1) {
                    set.add(result[a].doc);
                }
            }
            i++;
        }
        if(set.size()<n)
        {
            for(int a=0;a<d.totalHits;a++)
            {
                set.add(result[a].doc);
            }
        }
    }
}
