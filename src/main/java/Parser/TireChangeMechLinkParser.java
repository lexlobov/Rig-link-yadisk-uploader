package Parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.*;
import java.util.List;

public class TireChangeMechLinkParser extends HttpHandler implements Parser{
    public TireChangeMechLinkParser(String phoneNumber) {
        super(phoneNumber);
    }

    private String link;

    @Override
    public String getMechLink() throws IOException {
        String html = getHtml();
        if (html.equals("false")){
            System.out.println("Request wasn't successful");
            return null;
        }
//        String fakeHtml = "";
//        File file = new File("src/main/resources/page.html");
//        BufferedReader reader = new BufferedReader(new FileReader(file));
//        String line = reader.readLine();
//        while (line != null) {
//            fakeHtml += line +"\n";
//            line = reader.readLine();
//        }

        Document doc = Jsoup.parse(html); //fakeHtml
        List<Element> rows = doc.selectXpath("//div[@class='col-xs-12 col-md-8']");
        String message = rows.stream()
                .filter(m -> m.text().contains("There is a new tire change request near you. Tap the link to respond")).findFirst()
                .map(m -> m.text())
                .orElse(null);
        int idx = message.indexOf("http");
        String link = message.substring(idx);
        setLink(link);
        return link;
    }


    public void setLink(String link) {
        this.link = link;
    }
}
