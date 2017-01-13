package com.guavademo.file;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created by chaochun.ccc on 2017/1/4.
 */
public class VersionInfo {

  private Integer projects;

  private List<String> versions = Lists.newArrayList();

  public Integer getProjects() {
    return projects;
  }

  public void setProjects(Integer projects) {
    this.projects = projects;
  }

  public List<String> getVersions() {
    return versions;
  }

  public void setVersions(List<String> versions) {
    this.versions = versions;
  }
}
