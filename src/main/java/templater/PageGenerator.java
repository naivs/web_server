package templater;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import java.io.BufferedReader;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;

public class PageGenerator {

    private static final String HTML_DIR = "resources/pages";

    private static PageGenerator pageGenerator;
    private final Configuration cfg;

    public static PageGenerator instance() {
        if (pageGenerator == null) {
            pageGenerator = new PageGenerator();
        }
        return pageGenerator;
    }

    public String getPage(String filename, Map<String, Object> data) {
        Writer stream = new StringWriter();
        try {
            Template template = cfg.getTemplate(HTML_DIR + File.separator + filename);
            template.process(data, stream);
        } catch (IOException | TemplateException e) {
            System.err.println("Templater exception: " + e.getMessage());
        }
        return stream.toString();
    }

    public String getPage(String filename) {
        Writer stream = new StringWriter();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(HTML_DIR + File.separator + filename))) {
            String line;    
            while ((line = reader.readLine()) != null) {
                stream.append(line);
            }
        } catch (IOException ex) {
            System.err.println("Page generator, getPage eaception. " + ex.getMessage());
        }
        return stream.toString();
    }

    private PageGenerator() {
        cfg = new Configuration();
    }
}
