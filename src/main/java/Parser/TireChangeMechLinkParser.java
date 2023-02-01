package Parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.*;
import java.util.List;

public class TireChangeMechLinkParser extends HttpHandler implements Parser{
    @Override
    public String getLink(String phone) throws IOException {
        String html = getHtml(phone);
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
//        System.out.println(fakeHtml);

        Document doc = Jsoup.parse(html);
        List<Element> rows = doc.selectXpath("//div[@class='col-xs-12 col-md-8']");
        String message = rows.stream()
                .filter(m -> m.text().contains("There is a new tire change request near you. Tap the link to respond")).findFirst()
                .map(m -> m.text())
                .orElse(null);
        int idx = message.indexOf("http");
        String link = message.substring(idx);
        return link;
    }
}
