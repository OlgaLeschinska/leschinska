import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.filefilter.RegexFileFilter;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.CodeSource;
import java.util.Collection;
import java.util.List;

public class ReportFile {

    public static void main(String[] args) {
        final File folder;
        try {
            folder = new File(getJarContainingFolder(new ReportFile().getClass()));
        } catch (Exception e) {
            e.printStackTrace();
        }
//        Collection collections = getAllFilesInDir(folder);
 //       System.out.println(folder);
    }

    public static void getFiles(String filePath) throws IOException {
        Path dir = FileSystems.getDefault().getPath( filePath );
        DirectoryStream<Path> stream = Files.newDirectoryStream(dir);
        stream.close();
    }

    public static void listFilesForFolder(final File folder) {
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                listFilesForFolder(fileEntry);
            } else {
                System.out.println(fileEntry.getName());
            }
        }
    }

//    private List<String> getFileNames(List<String> fileNames, Path dir) {
//        try(DirectoryStream<Path> stream = Files.newDirectoryStream(dir)) {
//            for (Path path : stream) {
//                if(path.toFile().isDirectory()) {
//                    getFileNames(fileNames, path);
//                } else {
//                    fileNames.add(path.toAbsolutePath().toString());
//                    System.out.println(path.getFileName());
//                }
//            }
//        } catch(IOException e) {
//            e.printStackTrace();
//        }
//        return fileNames;
//    }
//
//    public File compareLists(Collection prevList, Collection modelList) {
//        if (prevList.size() == modelList.size()) {
//            for (Object modelListdata : modelList) {
//                for (Object prevListdata : prevList) {
//                    if (prevListdata.equals(modelListdata)){
//                        return  true;
//
//                    }
//                }
//
//            }
//        }
//        else{
//            return true;
//        }
//        return false;
//
//    }
//
    public static String getJarContainingFolder(Class aclass) throws Exception {
        CodeSource codeSource = aclass.getProtectionDomain().getCodeSource();

        File jarFile;

        if (codeSource.getLocation() != null) {
            jarFile = new File(codeSource.getLocation().toURI());
        } else {
            String path = aclass.getResource(aclass.getSimpleName() + ".class").getPath();
            String jarFilePath = path.substring(path.indexOf(":") + 1, path.indexOf("!"));
            jarFilePath = URLDecoder.decode(jarFilePath, "UTF-8");
            jarFile = new File(jarFilePath);
        }
        return jarFile.getParentFile().getAbsolutePath();
    }

    public static Collection<File> getAllFilesInDir(File dir) {
        return FileUtils.listFiles(
                dir,
                new RegexFileFilter("^(.*?)"),
                DirectoryFileFilter.DIRECTORY
        );
    }
}
