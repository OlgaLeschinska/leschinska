import org.apache.commons.io.FileUtils;
import java.io.*;
import java.net.URISyntaxException;
import java.util.*;
public class ReportFile {

    /**
     * @param args String[] args
     * @throws URISyntaxException URISyntaxException
     */
    public static void main(String[] args) throws URISyntaxException {
    //    File folder = new File("/Users/Danil/Desktop/test");
        File folder = new File(ReportFile.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath());
        File[] arrFiles = folder.listFiles();
        if (arrFiles!=null) {
            List<File> files = Arrays.asList(arrFiles);
            List<String> names = compareFiles(files);
                if (names != null) {
                    makeReport(folder,names);
                }
        }
    }

    /**
     * @param fileNames List<String> fileNames
     */
    private static void makeReport(File folder, List<String> fileNames) {
        try {
            File file = new File(folder, "Report");
            FileWriter writer = new FileWriter(file, false);
            for (String fileName:fileNames) {
                writer.write(fileName);
                writer.append('\n');
            }
            writer.flush();

        } catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * @param files List<File>
     * @return List<String>
     */
    private static List<String> compareFiles(List<File> files){
        List<String> fileNames = new ArrayList<>();
        for (int i = 0; i < files.size(); i++) {
            for (int j = i+1; j < files.size(); j++) {
                try {
                    if (FileUtils.contentEquals(files.get(i), files.get(j))) {
                        fileNames.add(files.get(i).getName());
                        fileNames.add(files.get(j).getName());
                    }
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
        return fileNames;
    }
}
