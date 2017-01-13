package com.guavademo.file;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.*;

/**
 * Created by chaochun.ccc on 2017/1/4.
 */
public class JarInfoTransfor {

  private FileReader fileReader;

  public JarInfoTransfor() {
    this.fileReader = new FileReader();
  }

  public List<JarBaseInfo> tranform(List<Projects> projectsList) {
    Map<Projects, Map<String, String>> jarInfoMap = fileReader.readLines(projectsList);

    Map<String, JarBaseInfo> result = Maps.newHashMap();
    for (Map.Entry<Projects, Map<String, String>> mapEntry : jarInfoMap.entrySet()) {
      Map<String, String> value = mapEntry.getValue();
      for (Map.Entry<String, String> entry : value.entrySet()) {
        if (result.containsKey(entry.getKey())) {
          JarBaseInfo jarBaseInfo = result.get(entry.getKey());
          jarBaseInfo.getVersionInfoMap().put(mapEntry.getKey().getOrder(),
              buildVersionInfo(mapEntry.getKey(), entry.getValue()));
        } else {
          result.put(entry.getKey(), convert(mapEntry.getKey(), entry));
        }
      }
    }

    List<JarBaseInfo> values = Lists.newArrayList(result.values());

//    for (JarBaseInfo value : values) {
//      List<VersionInfo> versionInfoList = value.getVersionInfoMap();
//
//      Collections.sort(versionInfoList, new Comparator<VersionInfo>() {
//        public int compare(VersionInfo o1, VersionInfo o2) {
//          return o1.getProjects().compareTo(o2.getProjects());
//        }
//      });
//      value.setVersionInfoMap(versionInfoList);
//    }

    return values;
  }

  private VersionInfo buildVersionInfo(Projects key, String value) {
    VersionInfo versionInfo = new VersionInfo();
    versionInfo.setProjects(key.getOrder());
    ArrayList<String> verList = Lists.newArrayList(value.split(";"));
    versionInfo.getVersions().addAll(verList);
    return versionInfo;
  }


  private JarBaseInfo convert(Projects key, Map.Entry<String, String> entry) {
    JarBaseInfo jarBaseInfo = new JarBaseInfo();
    String entryKey = entry.getKey();
    List<String> list = FileReader.MAOHAO_SPLITTER.splitToList(entryKey);
    jarBaseInfo.setGroup(list.get(0));
    jarBaseInfo.setArtifactId(list.get(1));
    LinkedHashMap<Integer, VersionInfo> versionInfoList = jarBaseInfo.getVersionInfoMap();
    VersionInfo versionInfo = new VersionInfo();
    versionInfo.setProjects(key.getOrder());
    ArrayList<String> verList = Lists.newArrayList(entry.getValue().split(";"));
    versionInfo.getVersions().addAll(verList);
    versionInfoList.put(key.getOrder(), versionInfo);
    return jarBaseInfo;
  }
}
