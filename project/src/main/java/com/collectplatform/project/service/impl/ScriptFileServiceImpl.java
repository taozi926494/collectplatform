package com.collectplatform.project.service.impl;

import com.collectplatform.project.entity.FileTreeNode;
import com.collectplatform.project.exception.ScriptFileException;
import com.collectplatform.project.property.ScriptFileProperties;
import com.collectplatform.project.service.ScriptFileService;
import com.collectplatform.project.vo.ScriptFileVo.CreatFileVo;
import com.collectplatform.project.vo.ScriptFileVo.CreateFolderVo;
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
import java.util.*;
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
        File folderPath = new File(scriptFileProperties.getUploadDir() + File.separator + id);
        if (folderPath.exists() || folderPath.mkdir()) {
            return savaFile(file, id);
        } else {
            return "创建文件路径失败";
        }
    }

    @Override
    public Resource loadFileAsResource(String id) {
        try {
            String path = generateFile(scriptFileProperties.getUploadDir() + File.separator + id);
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

    @Override
    public List<FileTreeNode> fileTree(String id) {
        List<FileTreeNode> baseTreeNodes = new ArrayList<>();
        String filePath = scriptFileProperties.getUploadDir() + File.separator + id;
        File file = new File(filePath);
        return getFileTreeNodes(baseTreeNodes, file);

    }

    @Override
    public String creatFile(CreatFileVo creatFileVo) throws IOException {
        File file = new File(scriptFileProperties.getUploadDir() + File.separator + creatFileVo.getId() + File.separator + creatFileVo.getPath() + File.separator + creatFileVo.getFileName());
        if (!checkPath(creatFileVo.getId(), creatFileVo.getPath(), creatFileVo.getFileName())) {
            throw new ScriptFileException("请检查文件名或路径");
        } else if (file.createNewFile()) {
            return "创建成功";
        } else {
            return "创建失败";
        }
    }


    @Override
    public String createFolder(CreateFolderVo createFolderVo) {
        File folder = new File(scriptFileProperties.getUploadDir() + File.separator + createFolderVo.getId() + File.separator + createFolderVo.getPath() + File.separator + createFolderVo.getFolderName());
        if (!checkPath(createFolderVo.getId(), createFolderVo.getPath(), createFolderVo.getFolderName())) {
            throw new ScriptFileException("请检查文件名或路径");
        } else if (folder.mkdir()) {
            return "创建成功";
        } else {
            return "创建失败";
        }
    }

    public boolean checkPath(String id, String path, String name) {
        String parentPath = scriptFileProperties.getUploadDir() + File.separator + id + File.separator + path;
        String childPath = parentPath + File.separator + name;
        File parent = new File(parentPath);
        File child = new File(childPath);
        return parent.exists() && !child.exists();
    }

    private List<FileTreeNode> getFileTreeNodes(List<FileTreeNode> baseTreeNodes, File file) {
        File[] childFiles = file.listFiles();
        if (childFiles != null) {
            for (File listFile : childFiles) {
                FileTreeNode baseTreeNode = new FileTreeNode();
                baseTreeNode.setName(listFile.getName());
                baseTreeNode.setIs_dir(listFile.isDirectory());
                baseTreeNode.setPath(listFile.getAbsolutePath());
                baseTreeNode.setLength(listFile.length());
                baseTreeNode.getChildren().addAll(getFileTree(listFile));
                baseTreeNodes.add(baseTreeNode);
            }
        }

        return baseTreeNodes;
    }

    private List<FileTreeNode> getFileTree(File file) {
        List<FileTreeNode> baseTreeNodes = new ArrayList<>();
        return getFileTreeNodes(baseTreeNodes, file);
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
        String filePath = scriptFileProperties.getUploadDir() + File.separator + id + File.separator + fileName;
        File file = new File(filePath);

        if (!file.exists()) {
            throw new ScriptFileException("文件不存在");
        } else {
            if (file.delete()) {
                return "删除成功";
            } else {
                return "删除失败";
            }
        }
    }

    public String deleteTempFile(String id) {
        String filePath = "./upload/" + id + ".zip";
        File file = new File(filePath);
        if (!file.exists()) {
            throw new ScriptFileException("文件不存在");
        } else {
            if (file.delete()) {
                return "删除成功";
            } else {
                return "删除失败";
            }
        }
    }

    public boolean unZip(String fileName, String id) {
        String filePath = scriptFileProperties.getUploadDir() + File.separator + id + File.separator + fileName;
        File zipFile = new File(filePath);
        ZipFile zip = null;
        try {
            zip = new ZipFile(zipFile, Charset.forName("gbk"));
            for (Enumeration entries = zip.entries(); entries.hasMoreElements(); ) {
                ZipEntry entry = (ZipEntry) entries.nextElement();
                String zipEntryName = entry.getName();
                InputStream in = zip.getInputStream(entry);
                String outPath = (scriptFileProperties.getUploadDir() + "/" + id + "/" + zipEntryName).replace("/",
                        "/");
                File file = new File(outPath.substring(0,
                        outPath.lastIndexOf("/")));
                if (!file.exists()) {
                    file.mkdirs();
                }
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
            zip.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public String generateFile(String path) throws Exception {
        File file = new File(path);
        if (!file.exists()) {
            throw new Exception("路径 " + path + " 不存在文件，无法进行压缩...");
        }
        String generateFile = file.getParent();
        File compress = new File(generateFile);
        if (!compress.exists()) {
            compress.mkdirs();
        }
        String generateFileName = compress.getAbsolutePath() + File.separator + file.getName() + ".zip";
        FileOutputStream outputStream = new FileOutputStream(generateFileName);
        ZipOutputStream zipOutputStream = new ZipOutputStream(new BufferedOutputStream(outputStream));
        generateFile(zipOutputStream, file, "");
        zipOutputStream.close();
        return generateFileName;
    }

    private static void generateFile(ZipOutputStream out, File file, String dir) throws Exception {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            out.putNextEntry(new ZipEntry(dir + File.separator));
            dir = dir.length() == 0 ? "" : dir + File.separator;
            for (int i = 0; i < files.length; i++) {
                generateFile(out, files[i], dir + files[i].getName());
            }
        } else {
            FileInputStream inputStream = new FileInputStream(file);
            out.putNextEntry(new ZipEntry(dir));
            int len = 0;
            byte[] bytes = new byte[1024];
            while ((len = inputStream.read(bytes)) > 0) {
                out.write(bytes, 0, len);
            }
            inputStream.close();
        }
    }

}
