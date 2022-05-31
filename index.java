import org.apache.lucene.analysis.core.WhitespaceAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.*;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.store.*;
import org.apache.lucene.util.Version;
import  org.apache.lucene.document.*;
import java.io.*;


class LuceneTester {

    public static void main(String args[]) throws IOException, ParseException
    {
        WhitespaceAnalyzer analyzer = new WhitespaceAnalyzer();
        Directory directory = FSDirectory.open(new File("e:test\\demoproject\\vms"));
        IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_4_10_3, analyzer);
        config.setOpenMode(IndexWriterConfig.OpenMode.CREATE);
        IndexWriter indexWriter = new IndexWriter(directory,config);
        File[] files = new File("E:\\test\\demoproject\\textfile").listFiles();
        for(File file_for_index : files)
        {
            Document doc = new Document();
            Reader read=new FileReader(file_for_index);
            String str="";
            String s;
            BufferedReader br=new BufferedReader(read);
            while((s=br.readLine())!=null)
            {
                str+=s+"\n";
            }
            System.out.println(str);
            doc.add(new TextField("fieldname", str, Field.Store.YES));
            doc.add(new StringField("file_name",file_for_index.getName() , Field.Store.YES));
            indexWriter.addDocument(doc);
            indexWriter.commit();

        }
        indexWriter.close();
    }
}