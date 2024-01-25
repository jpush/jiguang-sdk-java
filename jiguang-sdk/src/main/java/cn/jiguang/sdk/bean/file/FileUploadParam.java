package cn.jiguang.sdk.bean.file;

import feign.form.FormProperty;
import lombok.Data;

import java.io.File;

@Data
public class FileUploadParam {

    @FormProperty("filename")
    private File file;

    @FormProperty("ttl")
    private Integer ttl = 720;

}
