import FileHandler.TxtWriter;
import Parser.Parser;
import Parser.TireChangeMechLinkParser;
import com.yandex.disk.rest.exceptions.ServerException;
import yadisk.YadiskUploader;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws ServerException, IOException {

        try {
            String number = args[0];

            Parser parser = new TireChangeMechLinkParser(number);
            TxtWriter txtWriter = new TxtWriter(parser);
            YadiskUploader uploader = new YadiskUploader();
            uploader.yadiskUploader(txtWriter.write());
        } catch (IndexOutOfBoundsException e){
            System.out.println("Phone number is needed. Restart program with phone number in args :<");
        }
    }
}
