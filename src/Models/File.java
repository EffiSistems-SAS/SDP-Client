package Models;

public class File {

    private String uploadDate;
    private String _id;
    private int length;
    private int chunkSize;
    private String filename;
    private String md5;
    private String contentType;

    public String getUploadDate() {
        return uploadDate;
    }

    public String getId() {
        return _id;
    }

    public int getLength() {
        return length;
    }

    public int getChunkSize() {
        return chunkSize;
    }

    public String getFilename() {
        return filename;
    }

    public String getMd5() {
        return md5;
    }

    public String getContentType() {
        return contentType;
    }

    @Override
    public String toString() {
        return "File{" + "uploadDate=" + uploadDate + ", _id=" + _id + ", length=" + length + ", chunkSize=" + chunkSize + ", filename=" + filename + ", md5=" + md5 + ", contentType=" + contentType + '}';
    }

}
