package com.collectplatform.project.service.impl;

import com.collectplatform.project.exception.ScriptFileException;
import com.collectplatform.project.property.ScriptFileProperties;
import com.collectplatform.project.service.ScriptFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.MalformedURLException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Enumeration;
import java.util.Objects;
import java.util.zip.*;

/**
 * @ Author: fuqiang
 * @ Date: 2021/4/15
 */

@Service
public class ScriptFileServiceImpl implements ScriptFileService {

    @Autowired
    ScriptFileProperties scriptFileProperties;

    @Override
    public String storeFile(MultipartFile file, String id) {
        System.out.println("### upload dir " + scriptFileProperties.getUploadDir());

        mkdir(scriptFileProperties.getUploadDir() + File.separator + id);
        return savaFile(file, id);
    }

    @Override
    public Resource loadFileAsResource( String id) {
        try {
            String path = generateFile(scriptFileProperties.getUploadDir() + "/" +id);
            Path filePath = Paths.get(path);
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists()) {
                return resource;
            } else {
                throw new ScriptFileException("文件不存在");
            }
        } catch (MalformedURLException e) {
            throw new ScriptFileException("文件不存在", e);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ScriptFileException("压缩文件失败", e);
        }
    }

    @Override
    public String deleteFile(String fileName, String id) {
        return delFile(fileName, id);
    }

    @Override
    public String changeFile(MultipartFile file, String id) {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        delFile(fileName, id);
        return savaFile(file, id);
    }


    private void mkdir(String path) {
        File fd = null;
        File file = new File(path);
        try {
            fd = new File(path);
            if (!fd.exists()) {
                fd.mkdirs();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            fd = null;
        }
    }


    public String savaFile(MultipartFile file, String id) {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        try {
            if (fileName.contains("..")) {
                throw new ScriptFileException("请检查文件名" + fileName);
            }
            Path targetLocation = Paths.get(scriptFileProperties.getUploadDir(), id, fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            if (Objects.requireNonNull(file.getContentType()).contains("zip")) {
                if (unZip(fileName, id)) {
                    delFile(fileName, id);
                }
            }
            return fileName;
        } catch (IOException e) {
            throw new ScriptFileException(fileName + "文件存储失败请重试", e);
        }
    }

    public String delFile(String fileName, String id) {
        String filePath = scriptFileProperties.getUploadDir() + "/" + id + "/" + fileName;
        File file = new File(filePath);

        if (!file.exists()) {
            throw new ScriptFileException("文件不存在");
        } else {
            if(file.delete()){
                return "删除成功";
            } else {
                return "删除失败";
            }
        }
    }

    public String deleteTempFile(String id) {
        System.out.println("id is " + id);
//        System.out.println(scriptFileProperties.getUploadDir());
        System.out.println("######################");
        String filePath = "./upload/" + id + ".zip";
        System.out.println(filePath);
        File file = new File(filePath);
        if (!file.exists()) {
            throw new ScriptFileException("文件不存在");
        } else {
            if(file.delete()){
                return "删除成功";
            } else {
                return "删除失败";
            }
        }
    }

    public boolean unZip(String fileName, String id) {
        String filePath = scriptFileProperties.getUploadDir() + "/" + id + "/" + fileName;
        File zipFile = new File(filePath);
        ZipFile zip = null;
        try {
            // 指定编码，否则压缩包里面不能有中文目录
            zip = new ZipFile(zipFile, Charset.forName("gbk"));
            for (Enumeration entries = zip.entries(); entries.hasMoreElements(); ) {
                ZipEntry entry = (ZipEntry) entries.nextElement();
                String zipEntryName = entry.getName();
                InputStream in = zip.getInputStream(entry);
                String outPath = (scriptFileProperties.getUploadDir() + "/" + id + "/" + zipEntryName).replace("/",
                        File.separator);
                // 判断路径是否存在,不存在则创建文件路径
                File file = new File(outPath.substring(0,
                        outPath.lastIndexOf(File.separator)));
                if (!file.exists()) {
                    file.mkdirs();
                }
                // 判断文件全路径是否为文件夹,如果是上面已经上传,不需要解压
                if (new File(outPath).isDirectory()) {
                    continue;
                }

                OutputStream out = new FileOutputStream(outPath);
                byte[] buf1 = new byte[2048];
                int len;
                while ((len = in.read(buf1)) > 0) {
                    out.write(buf1, 0, len);
                }
                in.close();
                out.close();
            }
            // 必须关闭，否则无法删除该zip文件
            zip.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public String generateFile(String path) throws Exception {
        File file = new File(path);
        // 压缩文件的路径不存在
        if (!file.exists()) {
            throw new Exception("路径 " + path + " 不存在文件，无法进行压缩...");
        }
        // 用于存放压缩文件的文件夹
        String generateFile = file.getParent();
        File compress = new File(generateFile);
        // 如果文件夹不存在，进行创建
        if( !compress.exists() ){
            compress.mkdirs();
        }
        // 目的压缩文件
        String generateFileName = compress.getAbsolutePath() + File.separator + file.getName() + ".zip" ;
        // 输入流 表示从一个源读取数据
        // 输出流 表示向一个目标写入数据
        // 输出流
        FileOutputStream outputStream = new FileOutputStream(generateFileName);
        // 压缩输出流
        ZipOutputStream zipOutputStream = new ZipOutputStream(new BufferedOutputStream(outputStream));
        generateFile(zipOutputStream,file,"");
        System.out.println("源文件位置：" + file.getAbsolutePath() + "，目的压缩文件生成位置：" + generateFileName);
        // 关闭 输出流
        zipOutputStream.close();
        return generateFileName;
    }

    private static void generateFile(ZipOutputStream out, File file, String dir) throws Exception {
        // 当前的是文件夹，则进行一步处理
        if (file.isDirectory()) {
            //得到文件列表信息
            File[] files = file.listFiles();
            //将文件夹添加到下一级打包目录
            out.putNextEntry(new ZipEntry(dir + "/"));
            dir = dir.length() == 0 ? "" : dir + "/";
            //循环将文件夹中的文件打包
            for (int i = 0; i < files.length; i++) {
                generateFile(out, files[i], dir + files[i].getName());
            }
        } else { // 当前是文件
            // 输入流
            FileInputStream inputStream = new FileInputStream(file);
            // 标记要打包的条目
            out.putNextEntry(new ZipEntry(dir));
            // 进行写操作
            int len = 0;
            byte[] bytes = new byte[1024];
            while ((len = inputStream.read(bytes)) > 0) {
                out.write(bytes, 0, len);
            }
            // 关闭输入流
            inputStream.close();
        }
    }

}
