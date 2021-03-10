package testutils;

import org.apache.logging.log4j.core.util.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class JsonFileReader {
    /**
     * Reads a resourceFilePath from resources
     *
     * @param resourceFilePath path starting at src/test/resources
     * @return content (json) as String
     * @throws IOException resourceFilePath not found
     */
    public static String readFile(String resourceFilePath) throws IOException {
        InputStream stream = JsonFileReader.class.getClassLoader().getResourceAsStream(resourceFilePath);
        assert stream != null;
        return IOUtils.toString(new InputStreamReader(stream));
    }
}
