import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Scanner;


public class SearchNews implements ActionListener {
    String half_anchor2;

    String full_anchor;
    Scanner sc=new Scanner(System.in);
    Elements anchors;
    String topic;

    String india_today="https://www.indiatoday.in";
    String guardian="https://www.theguardian.com";
    Document indian_doc=Jsoup.connect(india_today).get();
    Document international_doc=Jsoup.connect(guardian).get();
    static JFrame frame;
    Elements paras;
    JTextField field;
    Button b1;
    JScrollPane scrollpane;
    JLabel jl;
    JLabel jl1;
    static JTextArea ta;
    static Image img;
    private JPanel panel;

    SearchNews() throws IOException {
        frame = new JFrame("FILTERED NEWSPAPER");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 650);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setResizable(true);
        //frame.setLayout(new FlowLayout());
        frame.getContentPane().setLayout(new FlowLayout());



        field = new JTextField(20);
        field.setEditable(true);
        b1 = new Button("Search");
        b1.addActionListener((java.awt.event.ActionListener) this);
        ta = new JTextArea(70, 90);
        ta.setMinimumSize(new Dimension(500, 500));
        ta.setMaximumSize(new Dimension(214, 214));
        ta.setEditable(false);
        ta.setLineWrap(true);
        frame.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        frame.getContentPane().add(field);
        frame.getContentPane().add(b1);

        panel = new JPanel();
        frame.getContentPane().add(panel);
        panel.setLayout(null);
        frame.getContentPane().add(ta);
        scrollpane = new JScrollPane();
        scrollpane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        frame.pack();
    }
    public void finder(Document doc)
    {
        anchors = doc.select("a[href]");
        for(Element anchor:anchors)
        {
            System.out.println(anchor.text());
            System.out.println(anchor.attr("href"));
        }
    }
    public void topic_search(String half_anchor1,String s)
    {
        int availible;
        topic=s;
        for(Element anchor:anchors)
        {
            availible=anchor.text().indexOf(topic);
            if(availible >(-1))
            {
                half_anchor2=anchor.attr("href");
                full_anchor=half_anchor1+half_anchor2;
                System.out.println(full_anchor);
                break;
            }
            else{
                ta.setText("no news regarding this topic today , check back again later. ");
            }
        }
    }
    public void news_display() throws IOException
    {
        String mnews="";
        Document article=Jsoup.connect(full_anchor).get();
        //System.out.println(article.getElementsByTag("p").text());
        Elements paras=article.select("p");
        for(Element para:paras)
        {
            System.out.println(para.text());
            mnews=mnews+para.text();
        }
        System.out.println(mnews);
        ta.setText(mnews);

    }


    public void actionPerformed(ActionEvent e) {
        String mnews="";
        Element a;
        String s6=field.getText();
        finder(international_doc);
        topic_search("",s6);
        try {
            news_display();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        finally {


        }

        /*for(Element para:paras)
        {
            mnews=mnews+para.text();
        }*/


        //ta.getText().concat(mnews)
    }

}

  /*class Newspaper
 {

     String half_anchor2;

     String full_anchor;
     Scanner sc=new Scanner(System.in);
     Elements anchors;
     String topic;
     public void finder(Document doc)
     {
         anchors = doc.select("a[href]");
         for(Element anchor:anchors)
         {
             System.out.println(anchor.text());
             System.out.println(anchor.attr("href"));
         }
     }
     public void topic_search(String half_anchor1,String s)
     {
         int availible;
         topic=s;
         for(Element anchor:anchors)
         {
             availible=anchor.text().indexOf(topic);
             if(availible >(-1))
             {
                 half_anchor2=anchor.attr("href");
                 full_anchor=half_anchor1+half_anchor2;
                 System.out.println(full_anchor);
                 break;
             }
         }
     }
     public Elements news_display() throws IOException
     {
         Document article=Jsoup.connect(full_anchor).get();
         //System.out.println(article.getElementsByTag("p").text());
         Elements paras=article.select("p");
         for(Element para:paras)
         {
             System.out.println(para.text());
         }
         return paras;
     }
 }*/
 class Fnp
{
    public static void main(String[] args) throws IOException, URISyntaxException
    {
        new SearchNews();
        SearchNews.frame.pack();
        //System.out.println(doc.getElementsByTag("a").text());
        //Scanner sc= new Scanner(System.in);
        //String option=sc.nextLine();
        //Newspaper news=new Newspaper();
       /* if(option.equals("indian"))
        {
            news.finder(indian_doc);
            //news.topic_search(india_today);
            news.news_display();
        }
        else if(option.equals("international"))
        {
            news.finder(international_doc);
            //news.topic_search("");
            news.news_display();
        }*/
    }

}
