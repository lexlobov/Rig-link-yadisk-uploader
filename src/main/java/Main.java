import FileHandler.TxtWriter;
import Parser.Parser;
import Parser.TireChangeMechLinkParser;
import com.yandex.disk.rest.exceptions.ServerException;
import yadisk.YadiskUploader;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws ServerException, IOException {
        Parser parser = new TireChangeMechLinkParser();
        TxtWriter txtWriter = new TxtWriter();
        YadiskUploader uploader = new YadiskUploader();
        uploader.yadiskUploader(txtWriter.write(parser.getLink("")));
    }
}
