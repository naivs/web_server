/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.vfs;

import java.io.File;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author Ivan Naumov
 */
public class VFSImpl implements VFSService {

    private final String root;

    public VFSImpl(String root) {
        this.root = root;
    }

    @Override
    public boolean ifExists(String path) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isDirrectory(String path) {
        File file = new File(path);
        return file.isDirectory();
    }

    @Override
    public String getAbsolutePath(String file) {
        File f = new File(file);
        return f.getAbsolutePath();
    }

    @Override
    public byte[] getBytes(String file) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getUTF8Text(String file) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Iterator<String> getIterator(String startDir) {
        return new FileIterator(startDir);
    }

    private class FileIterator implements Iterator<String> {

        private Queue<File> files = new LinkedList<>();
        
        public FileIterator(String path) {
            files.add(new File(root + path));
        }
        
        @Override
        public boolean hasNext() {
            return !files.isEmpty();
        }

        @Override
        public String next() {
            File file = files.peek();
            if(file.isDirectory()) {
                for (File subFile : file.listFiles()) {
                    files.add(subFile);
                }
            }
            return files.poll().getPath();
        }
    }
}
