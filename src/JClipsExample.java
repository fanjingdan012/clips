import java.io.File;
import java.io.FileNotFoundException;
import java.net.URI;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import jclips.JClips;

public class JClipsExample implements Observer {

    private static final String KNOWLEDGE_FILE = "myknowledge.clp";

    public void update(Observable jClips, Object messageObj) {
        String message = (String) messageObj;
        System.out.println("Message received: " + message);
    }

    public static void main(String[] args) {
        System.out.println(JClips.getVersion());

        JClips jClips = JClips.getInstance();
        jClips.init();
        jClips.addObserver(new JClipsExample());

        try {
            URL fileUrl = JClipsExample.class.getResource(KNOWLEDGE_FILE);
            if (fileUrl == null) {
                throw new FileNotFoundException(KNOWLEDGE_FILE);
            }
            File file = new File(new URI(fileUrl.toString()));
            jClips.load(file.getPath());
            jClips.reset();
            jClips.run();
            jClips.executeCommand("(assert (do-rest))");
            jClips.run();
            //jClips.commandLoop();
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }

        jClips.deallocate();
    }

}
