package com.calsoft.utils;

import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.validation.Valid;

@ConfigurationProperties(prefix = "core")
public class CoreProperties {

    @Valid
    private final FileTransferProperties fileTransfer = new FileTransferProperties();

    public FileTransferProperties getFileTransfer() {
        return fileTransfer;
    }

    public static class FileTransferProperties {
        private String uploadFolder;
        private String downloadURI;

        public String getUploadFolder() {
            return uploadFolder;
        }

        public void setUploadFolder(String uploadFolder) {
            this.uploadFolder = uploadFolder;
        }

        public String getDownloadURI() {
            return downloadURI;
        }

        public void setDownloadURI(String downloadURI) {
            this.downloadURI = downloadURI;
        }
    }
}
