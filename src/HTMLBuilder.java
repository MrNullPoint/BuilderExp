import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class HTMLBuilder extends Builder {

    private String fileName;
    private PrintWriter writer;

    @Override
    public void makeTitle(String title) {
        fileName = title + ".html";
        try {
            writer = new PrintWriter(fileName);
            writer.println("<html><head><title>" + title + "</title></head><body>");
            writer.println("<h1>" + title + "</h1>");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void makeString(String str) {
        writer.println("<p>" + str + "</p>");
    }

    @Override
    public void makeItems(String[] items) {
        writer.println("<ul>");
        for(String item : items) {
            writer.println("<li>" + item + "</li>");
        }
        writer.println("</ul>");
    }

    @Override
    public void close() {
        writer.println("</body></html>");
        writer.close();
    }

    public String getResult() {
        return fileName;
    }
}
