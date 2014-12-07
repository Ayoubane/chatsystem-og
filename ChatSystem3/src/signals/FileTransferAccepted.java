package signals;

/**
 *
 * @author gb
 */
public class FileTransferAccepted extends Signal{
    private String fileName;
    private String remoteUsername;

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

