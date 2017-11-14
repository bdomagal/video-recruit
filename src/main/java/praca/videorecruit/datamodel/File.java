package praca.videorecruit.datamodel;

import javax.persistence.*;

@Entity
public class File {
    private Integer fileId;
    private String fileUrl;
    private Application application;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fileId", nullable = false)
    public Integer getFileId() {
        return fileId;
    }

    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }

    @Basic
    @Column(name = "fileURL", nullable = false, length = 1000)
    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        File file = (File) o;

        if (fileId != null ? !fileId.equals(file.fileId) : file.fileId != null) return false;
        if (fileUrl != null ? !fileUrl.equals(file.fileUrl) : file.fileUrl != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = fileId != null ? fileId.hashCode() : 0;
        result = 31 * result + (fileUrl != null ? fileUrl.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "applicationId", referencedColumnName = "applicationId", nullable = false)
    public Application getApplication() {
        return application;
    }

    public void setApplication(Application applicationByApplicationId) {
        this.application = applicationByApplicationId;
    }
}
