package com.yuntongxun.ytx.utils;

import java.io.*;

/**
 * 读取部分文件工具类
 * @author tangxy
 * @date 2019-03-30
 **/
public class FileReadPartUtil {

    /**
     * 读取部分文件
     *
     * @param file
     * @param offset 开始
     * @param length 长度
     * @return
     */
    public static String readPartOfFile(File file, int offset, int length) {
        String content = "";
        try {
            FileReader fr = new FileReader(file);
            char[] buffer = new char[length];
            fr.read(buffer);
            content = new String(buffer);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }

    /**
     * 读取文件xx行数据
     *
     * @param file
     * @param readLine
     * @return
     */
    public static String readFileLine(File file, int readLine) {
        StringBuilder content = new StringBuilder();
        try {
            FileInputStream is = new FileInputStream(file);
            InputStreamReader ir = new InputStreamReader(is, "UTF-8");
            BufferedReader br = new BufferedReader(ir);
            String line = "";
            int count = 0;
            while ((line = br.readLine()) != null) {
                content.append(line).append("<br/>");
                count++;
                if (count == readLine) {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content.toString();
    }


    /**
     * linux系统根据全路径文件获取文件名
     *
     * @param fullFile
     * @return
     */
    public static String getFileName(String fullFile) {
        String fileName = fullFile.substring(fullFile.lastIndexOf("/") + 1);
        return fileName;
    }

    /**
     * 根据全路径文件，文件名称获取文件uri
     *
     * @param fullFile
     * @param fileName
     * @return
     */
    public static String getFirstPath(String fullFile, String fileName) {
        int i = fullFile.indexOf(fileName);
        return fullFile.substring(0, i);
    }

}