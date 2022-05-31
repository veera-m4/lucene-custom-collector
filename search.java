import custom_collector.custom_collector;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.*;
import org.apache.lucene.search.*;
import org.apache.lucene.store.*;
import java.util.*;
import java.io.*;

class search
{
    public static void main(String [] args)throws IOException
    {
        Directory dir=FSDirectory.open(new File("e:test\\demoproject\\vms"));
        IndexReader reader=IndexReader.open(dir);
        IndexSearcher sear=new IndexSearcher(reader);
        Scanner sc=new Scanner(System.in);
        String s=sc.nextLine();
        int end=reader.maxDoc();
        Term t=new Term("fieldname",s);
        TermQuery query=new TermQuery(t);
        custom_collector docs = new custom_collector(sear);
        docs.collect(query,10);
        LinkedHashSet main_set= docs.set;
        int result_size=main_set.size();
        System.out.println("no_of_hits : "+result_size);
        System.out.println(main_set);
        for(Object c:main_set)
        {
            Document document=sear.doc((int)c);
            System.out.println("filename  "+document.get("file_name"));
        }
    }
}
