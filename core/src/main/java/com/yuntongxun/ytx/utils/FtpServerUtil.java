package com.yuntongxun.ytx.utils;

import com.yuntongxun.ytx.constants.ConfigParams;
import com.yuntongxun.ytx.constants.EnumConstants;
import cn.hutool.json.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import java.io.*;

/**
 * ftp 工具类
 * @author tangxy
 * @date 2019-03-26
 **/
@Slf4j
public class FtpServerUtil {

    /**
     * 连接到服务器
     * @return
     */
    public static FTPClient getConnect(){
        JSONObject ftpJson = ConfigParams.paramsMap.get(EnumConstants.FtpServerConfig.FTP.getKey());
        Integer port = Integer.valueOf((String) ftpJson.get(EnumConstants.FtpServerConfig.PORT.getKey()));
        String username = (String) ftpJson.get(EnumConstants.FtpServerConfig.USERNAME.getKey());
        String password = (String) ftpJson.get(EnumConstants.FtpServerConfig.PASSWORD.getKey());
        String hostname = (String) ftpJson.get(EnumConstants.FtpServerConfig.HOSTNAME.getKey());

        FTPClient ftpClient = new FTPClient();
        ftpClient.setControlEncoding("UTF-8");
        //连接FTP服务器
        try {
            ftpClient.connect(hostname, port);
            //登录FTP服务器
            ftpClient.login(username, password);
            //是否成功登录FTP服务器
            int replyCode = ftpClient.getReplyCode();
            if(!FTPReply.isPositiveCompletion(replyCode)){
                return null;
            }
        } catch (IOException e) {
            log.info("连接FTP服务器异常:{}",e);
        }
        return ftpClient;
    }


    /**
     * 上传文件（可供Action/Controller层使用）
     * @param pathname FTP服务器保存目录
     * @param fileName 上传到FTP服务器后的文件名称
     * @param inputStream 输入文件流
     * @return
     */
    public static boolean uploadFile(String pathname, String fileName, InputStream inputStream){
        boolean flag = false;
        FTPClient ftpClient = getConnect();
        if(ftpClient == null){
            log.info("创建ftp客户端失败!");
            return false;
        }
        try {
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            ftpClient.makeDirectory(pathname);
            ftpClient.changeWorkingDirectory(pathname);
            ftpClient.enterLocalPassiveMode();
            ftpClient.storeFile(fileName, inputStream);
            inputStream.close();
            ftpClient.logout();
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            if(ftpClient.isConnected()){
                try {
                    ftpClient.disconnect();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return flag;
    }




    /**
     * 上传文件（可供Action/Controller层使用）
     * @param pathname FTP服务器保存目录
     * @param fileName 上传到FTP服务器后的文件名称
     * @param inputStream 输入文件流
     * @return
     */
    public static boolean uploadFile(String pathname, String fileName, InputStream inputStream,FTPClient client){
        boolean flag = false;
        FTPClient ftpClient=null;
        if(client==null) {
            ftpClient = getConnect();
            if (ftpClient == null) {
                return false;
            }
        }
        else{
            ftpClient=client;
        }
        try {
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            ftpClient.makeDirectory(pathname);
            ftpClient.changeWorkingDirectory(pathname);
            ftpClient.enterLocalPassiveMode();
            ftpClient.storeFile(fileName, inputStream);
            inputStream.close();
            ftpClient.logout();
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            if(ftpClient.isConnected()){
                try {
                    ftpClient.disconnect();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return flag;
    }


    /**
     * 上传文件（可对文件进行重命名）
     * @param pathname FTP服务器保存目录
     * @param filename 上传到FTP服务器后的文件名称
     * @param originfilename 待上传文件的名称（绝对地址）
     * @return
     */
    public static boolean uploadFileFromProduction(String pathname, String filename, String originfilename,FTPClient client){
        boolean flag = false;
        try {
            InputStream inputStream = new FileInputStream(new File(originfilename));
            flag = uploadFile(pathname, filename, inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }



    /**
     * 上传文件（可对文件进行重命名）
     * @param pathname FTP服务器保存目录
     * @param filename 上传到FTP服务器后的文件名称
     * @param originfilename 待上传文件的名称（绝对地址）
     * @return
     */
    public static boolean uploadFileFromProduction(String pathname, String filename, String originfilename){
        boolean flag = false;
        try {
            InputStream inputStream = new FileInputStream(new File(originfilename));
            flag = uploadFile(pathname, filename, inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 上传文件（不可以进行文件的重命名操作）
     * @param pathname FTP服务器保存目录
     * @param originfilename 待上传文件的名称（绝对地址）
     * @return
     */
    public static boolean uploadFileFromProduction(String pathname, String originfilename){
        boolean flag = false;
        try {
            String fileName = new File(originfilename).getName();
            InputStream inputStream = new FileInputStream(new File(originfilename));
            flag = uploadFile(pathname, fileName, inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }


    /**
     * 删除文件
     * @param pathname FTP服务器保存目录
     * @param filename 要删除的文件名称
     * @return
     */
    public static boolean deleteFile(String pathname, String filename){
        boolean flag = false;
        FTPClient ftpClient = getConnect();
        if(ftpClient == null){
            return false;
        }
        try {
            //切换FTP目录
            ftpClient.changeWorkingDirectory(pathname);
            ftpClient.dele(filename);
            ftpClient.logout();
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            if(ftpClient.isConnected()){
                try {
                    ftpClient.logout();
                } catch (IOException e) {

                }
            }
        }
        return flag;
    }

    /**
     * 下载文件
     * @param pathname FTP服务器文件目录
     * @param filename 文件名称
     * @param localpath 下载后的文件路径
     * @return
     */
    public static boolean downloadFile(String pathname, String filename, String localpath){
        boolean flag = false;
        FTPClient     ftpClient = getConnect();
            if (ftpClient == null) {
                return false;
            }
        try {
            //切换FTP目录
            ftpClient.changeWorkingDirectory(pathname);
            ftpClient.enterLocalPassiveMode();
            FTPFile[] ftpFiles = ftpClient.listFiles();
            for(FTPFile file : ftpFiles){
                if(filename.equalsIgnoreCase(file.getName())){
                    File dir = new File(localpath);
                    if(!dir.exists()){
                        dir.mkdir();
                    }
                    File localFile = new File(localpath + "/" + file.getName());
                    OutputStream os = new FileOutputStream(localFile);
                    ftpClient.retrieveFile(file.getName(), os);
                    os.close();
                }
            }
            ftpClient.logout();
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            if(ftpClient.isConnected()){
                try {
                    ftpClient.logout();
                } catch (IOException e) {

                }
            }
        }
        return flag;
    }



    /**
     * 下载文件
     * @param pathname FTP服务器文件目录
     * @param filename 文件名称
     * @param localpath 下载后的文件路径
     * @return
     */
    public static boolean downloadFile(String pathname, String filename, String localpath,FTPClient client){
        boolean flag = false;
        FTPClient ftpClient=null;
        if(client==null) {
            ftpClient = getConnect();
            if (ftpClient == null) {
                return false;
            }
        }
        else{
            ftpClient=client;
        }
        try {
            //切换FTP目录
            ftpClient.changeWorkingDirectory(pathname);
            ftpClient.enterLocalPassiveMode();
            FTPFile[] ftpFiles = ftpClient.listFiles();
            for(FTPFile file : ftpFiles){
                if(filename.equalsIgnoreCase(file.getName())){
                    File dir = new File(localpath);
                    if(!dir.exists()){
                        dir.mkdir();
                    }
                    File localFile = new File(localpath + "/" + file.getName());
                    OutputStream os = new FileOutputStream(localFile);
                    ftpClient.retrieveFile(file.getName(), os);
                    os.close();
                }
            }
            ftpClient.logout();
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            if(ftpClient.isConnected()){
                try {
                    ftpClient.logout();
                } catch (IOException e) {

                }
            }
        }
        return flag;
    }

    /**
     * 下载文件
     * @param pathname FTP服务器文件目录
     * @param filename 文件名称
     * @param localpath 下载后的文件路径
     * @param localFileName 下载后的文件名称
     * @return
     */
    public static boolean downloadFile(String pathname, String filename, String localpath,String localFileName){
        boolean flag = false;
        FTPClient ftpClient = getConnect();
        if(ftpClient == null){
            return false;
        }
        try {
            //切换FTP目录
            ftpClient.changeWorkingDirectory(pathname);
            ftpClient.enterLocalPassiveMode();
            FTPFile[] ftpFiles = ftpClient.listFiles();
            for(FTPFile file : ftpFiles){
                if(filename.equalsIgnoreCase(file.getName())){
                    File dir = new File(localpath);
                    if(!dir.exists()){
                        dir.mkdir();
                    }
                    File localFile = new File(localpath + "/" + localFileName);
                    OutputStream os = new FileOutputStream(localFile);
                    ftpClient.retrieveFile(file.getName(), os);
                    os.close();
                }
            }
            ftpClient.logout();
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            if(ftpClient.isConnected()){
                try {
                    ftpClient.logout();
                } catch (IOException e) {

                }
            }
        }
        return flag;
    }

    /**
     * 下载文件
     * @param pathname FTP服务器文件目录
     * @param filename 文件名称
     * @return
     */
    public static boolean downloadFile(String pathname, String filename){
        boolean flag = false;
        FTPClient ftpClient = getConnect();
        if(ftpClient == null){
            return false;
        }
        try {
            //切换FTP目录
            ftpClient.changeWorkingDirectory(pathname);
            ftpClient.enterLocalPassiveMode();
            FTPFile[] ftpFiles = ftpClient.listFiles();
            for(FTPFile file : ftpFiles){
                if(filename.equalsIgnoreCase(file.getName())){
                    File localFile = new File(file.getName());
                    OutputStream os = new FileOutputStream(localFile);
                    ftpClient.retrieveFile(file.getName(), os);
                    os.close();
                }
            }
            ftpClient.logout();
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            if(ftpClient.isConnected()){
                try {
                    ftpClient.logout();
                } catch (IOException e) {

                }
            }
        }
        return flag;
    }

    /**
     * 下载文件
     * @param pathname FTP服务器文件目录
     * @param filename 文件名称
     * @return
     */
    public static OutputStream downloadFile(String pathname, String filename,OutputStream outputStream){
        FTPClient ftpClient = getConnect();
        if(ftpClient == null){
            return null;
        }
        try {
            //切换FTP目录
            ftpClient.changeWorkingDirectory(pathname);
            ftpClient.enterLocalPassiveMode();
            FTPFile[] ftpFiles = ftpClient.listFiles();
            for(FTPFile file : ftpFiles){
                if(filename.equalsIgnoreCase(file.getName())){
                    ftpClient.retrieveFile(file.getName(), outputStream);
                }
            }
            ftpClient.logout();

        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            if(ftpClient.isConnected()){
                try {
                    ftpClient.logout();
                } catch (IOException e) {

                }
            }
        }
        return outputStream;
    }

}
