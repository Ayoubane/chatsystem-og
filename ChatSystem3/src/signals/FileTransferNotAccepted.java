/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package signals;

/**
 * The FileTransferNotAccepted Signal
 * @author Ayoub, Omar
 */
public class FileTransferNotAccepted extends Signal {
    private String fileName;
    private String remoteUsername;

    /**
     * Creates a FileTransferNotAccepted Signal
     * @param fileName
     * @param remoteUsername 
     */
    public FileTransferNotAccepted(String fileName, String remoteUsername) {
        this.fileName = fileName;
        this.remoteUsername = remoteUsername;
    }

    public String getFileName() {
        return fileName;
    }

    public String getRemoteUsername() {
        return remoteUsername;
    }
}
