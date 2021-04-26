package com.collectplatform.project.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

/**
 * @ Author: fuqiang
 * @ Date: 2021/4/25
 */

@Data
@EqualsAndHashCode(callSuper = false)
public class FileTreeNode {
    private String name;
    private String path;
    private Long length;
    private Boolean is_dir = false;
    private List<FileTreeNode> children = new ArrayList<>();

    public void addChild(FileTreeNode treeNode) {
        if (children == null) {
            children = new ArrayList<>();
        }
        children.add(treeNode);
    }

}
