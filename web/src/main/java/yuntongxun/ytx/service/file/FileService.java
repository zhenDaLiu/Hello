package yuntongxun.ytx.service.file;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

/**
 * 文件Service
 * @author sintang
 * @date 2019-07-22
 **/
public interface FileService {
    /**
     * 保存上传的文件
     * @param file
     * @return
     */
    String saveUploadFile(MultipartFile file);

    /**
     * 批量保存上传的文件
     * @param files
     * @return
     */
    List<String> saveUploadFiles(List<MultipartFile> files);

    /**
     * 上传本地文件
     * @param file
     * @return
     */
    String saveLocalFile(File file);

    /**
     * 从ftp上删除文件
     * @param fileName
     * @return
     */
    void deleteFileFromFtp(String fileName);
}
