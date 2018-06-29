package action;

import java.io.File;

import service.excelReader;

public class FileUploadAction extends baseAction{

    private File upload;
    private String uploadContentType;
    private String uploadFileName;//上传图片的名字
    private excelReader er = new excelReader();
    public File getUpload() {
        return upload;
    }
    public void setUpload(File upload) {
        this.upload = upload;
    }
    public String getUploadContentType() {
        return uploadContentType;
    }
    public void setUploadContentType(String uploadContentType) {
        this.uploadContentType = uploadContentType;
    }
    public String getUploadFileName() {
        return uploadFileName;
    }
    public void setUploadFileName(String uploadFileName) {
        this.uploadFileName = uploadFileName;
    }

}
