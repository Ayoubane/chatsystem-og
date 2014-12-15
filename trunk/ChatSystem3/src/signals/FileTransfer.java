package signals;

/**
 * The File Transfer Signal
 * @author Ayoub, Omar
 */
public class FileTransfer extends Signal {
        private byte[] file;

        /**
         * Creates a FileTransfer Class
         * @param file 
         */
        public FileTransfer(byte [] file) {
                super();
                this.file = file;
        }

        public byte[] getFile() {
                return file;
        }

        public void setFile(byte[] file) {
                this.file = file;
        }
}
