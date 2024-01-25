package cn.jiguang.sdk.bean.admin;

import feign.form.FormProperty;
import lombok.Data;

import java.io.File;

@Data
public class CertificateUploadParam {

    @FormProperty("devCertificatePassword")
    private String devCertificatePassword;

    @FormProperty("devCertificateFile")
    private File devCertificateFile;

    @FormProperty("proCertificatePassword")
    private String proCertificatePassword;

    @FormProperty("proCertificateFile")
    private File proCertificateFile;

}
