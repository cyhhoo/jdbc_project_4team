import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PropertyLoadTest {

    @Test
    public void testUpdateBookPropertyExists() throws IOException {
        Properties prop = new Properties();
        String resourcePath = "com/ohgiraffers/mapper/book-query.xml";
        InputStream is = getClass().getClassLoader().getResourceAsStream(resourcePath);
        assertNotNull(is, "Could not find resource: " + resourcePath);

        prop.loadFromXML(is);

        String sql = prop.getProperty("updateBook");
        assertNotNull(sql, "Property 'updateBook' should not be null");
        System.out.println("updateBook SQL: " + sql);
    }
}
