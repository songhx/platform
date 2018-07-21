package com.platform.core.config;

import java.io.Serializable;

/**
 * 项目配置
 *
 * @author bjsonghongxu
 * @create 2018-07-20 17:57
 **/
public class ProjectConfig implements Serializable {

    //项目名称
    private String projectName;
    //项目简介
    private String projectDesc;
    //类型 0 maven 1 module-jar  2 module-web
    private int type = 0;
    //项目输出路径
    private String outPath;

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectDesc() {
        return projectDesc;
    }

    public void setProjectDesc(String projectDesc) {
        this.projectDesc = projectDesc;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getOutPath() {
        return outPath;
    }

    public void setOutPath(String outPath) {
        this.outPath = outPath;
    }
}
