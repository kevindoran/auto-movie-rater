/**
 * 
 */
package util;

import java.nio.file.Path;
import java.util.Collection;

/**
 * @author Kevin Doran
 *
 */
public interface FileRetriever {
    
    public Collection<Path> getAllFilesInDirectoryTree(Path directory);
}
