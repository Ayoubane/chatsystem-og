package signals;

/**
 * The FileTransferZccepted Signal
 * @author Ayoub, Omar
 */
public class FileTransferAccepted extends Signal{
    private String fileName;
    private String remoteUsername;

    /**
     * Creates a FileTransferAccepted Signal
     * @param fileName
     * @param remoteUsername 
     */
    public FileTransferAccepted(String fileName, String remoteUsername) {
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

