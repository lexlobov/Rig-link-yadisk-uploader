package FileHandler;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class TxtWriter {

    public boolean write(String link){
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
