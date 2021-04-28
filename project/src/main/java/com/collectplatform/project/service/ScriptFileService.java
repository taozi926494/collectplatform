package com.collectplatform.project.service;

import com.collectplatform.project.entity.FileTreeNode;
import com.collectplatform.project.vo.ScriptFileVo.CreatFileVo;
import com.collectplatform.project.vo.ScriptFileVo.CreateFolderVo;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @Author fuqiang
 * @Date 2021/4/14
 */

public interface ScriptFileService {
    String storeFile(MultipartFile file, String id);

    Resource loadFileAsResource(String id);

    String deleteFile(String fileName, String id);

    String changeFile(MultipartFile file, String id);

    List<FileTreeNode> fileTree(String id);

    String deleteTempFile(String id);

    String creatFile(CreatFileVo creatFileVo) throws IOException;

    String createFolder(CreateFolderVo createFolderVo);
}
