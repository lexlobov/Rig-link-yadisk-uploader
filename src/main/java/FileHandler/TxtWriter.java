package FileHandler;

import Parser.Parser;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class TxtWriter {

    Parser parser;

    public TxtWriter(Parser parser){
        this.parser=parser;
    }

    public boolean write() throws IOException {
        String link = parser.getMechLink();
        if (link == null) return false;
        File file = new File("link.txt");
        try (Writer writer = new FileWriter(file)) {
            writer.write(link);
        } catch (IOException e) {
            System.out.println("Wasn't able to write file");
            return false;
        }
        return true;
    }
}
