package com.yuntongxun.ytx.service.file.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONObject;
import com.google.common.collect.Lists;
import com.yuntongxun.ytx.constants.ConfigParams;
import com.yuntongxun.ytx.constants.EnumConstants;
import com.yuntongxun.ytx.utils.FtpServerUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import com.yuntongxun.ytx.fast.exception.ClientBusinessException;
import com.yuntongxun.ytx.service.file.FileService;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

/**
 * 文件服务Service
 * @author sintang
 * @date 2019-07-22
 **/
@Service
@Slf4j
public class FileServiceImpl implements FileService {

    /**
     * 保存上传的文件
     * @param file
     * @return
     */
    @Override
    public String saveUploadFile(MultipartFile file) {
        // 获取ftpserver的配置
        JSONObject ftpJson = ConfigParams.paramsMap.get(EnumConstants.FtpServerConfig.FTP.getKey());
        if(ftpJson == null){
            log.info("未配置ftpServer信息。");
            throw new ClientBusinessException("未配置ftpServer信息。");
        }
        if(file == null){
            log.info("上传文件为空");
            return null;
        }

        log.info("开始上传文件");
        String filePath = (String) ftpJson.get(EnumConstants.FtpServerConfig.BASEPATH.getKey());
        log.info("ftp根路径："+filePath);

        // 设置上传文件的路径
        String avatarPath = DateUtil.today();

        // 上传文件名
        String fileName = file.getOriginalFilename();
        StringBuilder fileBuild = new StringBuilder();
        fileBuild.append(fileName);
        log.info("将要上传的文件名："+fileBuild.toString());
        // 上传文件到ftpserver
        boolean uploadResult = false;
        StringBuilder uploadPath = new StringBuilder(filePath).append("/").append(avatarPath);
        log.info("上传文件路径：{}",uploadPath.toString());
        try {
            uploadResult = FtpServerUtil.uploadFile(uploadPath.toString(),fileBuild.toString(),file.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(!uploadResult){
            log.info("文件上传有误");
        }
        log.info("文件上传成功");

        String nginxUrl = (String)ftpJson.get(EnumConstants.FtpServerConfig.NGINX_URL.getKey());
        if(StringUtils.isEmpty(nginxUrl)){
            log.info("nginx映射地址未配置");
            return null;
        }
        StringBuilder resultUrl = new StringBuilder(nginxUrl);
        resultUrl.append("/").append(uploadPath.toString()).append("/").append(fileName);
        return resultUrl.toString();
    }

    /**
     * 批量保存上传的文件
     * @param files
     * @return
     */
    @Override
    public List<String> saveUploadFiles(List<MultipartFile> files) {
        List<String> filePaths = Lists.newArrayList();
        if(files != null && files.size() > 0){
            String filePath;
            for(MultipartFile file : files){
                filePath = saveUploadFile(file);
                if(!StringUtils.isEmpty(filePath)){
                    filePaths.add(filePath);
                }
            }
        }
        return filePaths;
    }

    /**
     * 保存本地文件
     * @param file
     * @return
     */
    @Override
    public String saveLocalFile(File file) {
        // 获取ftpserver的配置
        JSONObject ftpJson = ConfigParams.paramsMap.get(EnumConstants.FtpServerConfig.FTP.getKey());
        if(ftpJson == null){
            log.info("未配置ftpServer信息。");
            throw new ClientBusinessException("未配置ftpServer信息。");
        }
        if(file == null){
            log.info("上传文件为空");
            return null;
        }

        log.info("开始上传文件");
        String filePath = (String) ftpJson.get(EnumConstants.FtpServerConfig.BASEPATH.getKey());
        log.info("ftp根路径："+filePath);
        // 上传文件名
        String fileName = file.getName();
        StringBuilder fileBuild = new StringBuilder();
        fileBuild.append(fileName);
        log.info("将要上传的文件名："+fileBuild.toString());
        // 上传文件到ftpserver
        boolean uploadResult = false;
        try {
            uploadResult = FtpServerUtil.uploadFile(filePath,fileBuild.toString(),new FileInputStream(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(!uploadResult){
            log.info("文件上传有误");
        }
        log.info("文件上传成功");

        String nginxUrl = (String)ftpJson.get(EnumConstants.FtpServerConfig.NGINX_URL.getKey());
        if(StringUtils.isEmpty(nginxUrl)){
            log.info("nginx映射地址未配置");
            return null;
        }
        StringBuilder resultUrl = new StringBuilder(nginxUrl);
        resultUrl.append("/").append(filePath.toString()).append("/").append(fileName);
        return resultUrl.toString();
    }

    /**
     * 从ftp上删除文件
     *
     * @param fileName
     * @return
     */
    @Override
    public void deleteFileFromFtp(String fileName) {
        // 获取ftpserver的配置
        JSONObject ftpJson = ConfigParams.paramsMap.get(EnumConstants.FtpServerConfig.FTP.getKey());
        if(ftpJson == null){
            log.info("未配置ftpServer信息。");
            throw new ClientBusinessException("未配置ftpServer信息。");
        }
        if(StringUtils.isEmpty(fileName)){
            log.info("文件为空");
            return;
        }

        String filePath = (String) ftpJson.get(EnumConstants.FtpServerConfig.BASEPATH.getKey());
        FtpServerUtil.deleteFile(filePath,fileName);
        log.info("文件删除成功");
    }
}
