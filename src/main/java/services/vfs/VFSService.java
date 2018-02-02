/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.vfs;

import java.util.Iterator;

/**
 *
 * @author Ivan Naumov
 */
public interface VFSService {
    
    boolean ifExists(String path);
    
    boolean isDirrectory(String path);
    
    String getAbsolutePath(String file);
    
    byte[] getBytes(String file);
    
    String getUTF8Text(String file);
    
    Iterator<String> getIterator(String startDir);
}
