package com.guavademo.file;


import com.google.common.collect.Maps;

import java.util.LinkedHashMap;

/**
 * Created by chaochun.ccc on 2017/1/4.
 */
public class JarBaseInfo {
  private String group;

  private String artifactId;

  private LinkedHashMap<Integer, VersionInfo> versionInfoMap = Maps.newLinkedHashMap();

  public String getGroup() {
    return group;
  }

  public void setGroup(String group) {
    this.group = group;
  }

  public String getArtifactId() {
    return artifactId;
  }

  public void setArtifactId(String artifactId) {
    this.artifactId = artifactId;
  }

  public LinkedHashMap<Integer, VersionInfo> getVersionInfoMap() {
    return versionInfoMap;
  }

  public void setVersionInfoMap(LinkedHashMap<Integer, VersionInfo> versionInfoMap) {
    this.versionInfoMap = versionInfoMap;
  }

  public String toString() {

    StringBuilder sbr = new StringBuilder("| ").append(group).append(" | ").append(artifactId).append(" | ");
    for (int i = 1 ; i <= 5; i++) {
      VersionInfo versionInfo = versionInfoMap.get(i);
      if (versionInfo == null) {
        sbr.append(" \\- ");
      } else {
        sbr.append(FileReader.ENTER_JOINER.join(versionInfo.getVersions()));
      }
      sbr.append(" | ");
    }
    return sbr.toString();
  }
}
