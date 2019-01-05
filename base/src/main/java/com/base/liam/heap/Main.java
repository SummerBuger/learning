package com.base.liam.heap;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.FileUtils;

public class Main {

  public static void main(String[] args) throws IOException {
    File file = new File(
      "/Users/liam/mlearn/projs/learning/base/src/main/java/com/base/liam/heap/region.json");
    String json = FileUtils.readFileToString(file, Charsets.UTF_8);
    System.out.println(json);
    List<DictTreeVo> dictTreeVos = JSON.parseArray(json, DictTreeVo.class);
    System.out.println(dictTreeVos.size());
    System.out.println("======================================");
    System.out.println(extendRegionList(dictTreeVos, 1812791296L));
  }

  private static List<Long> extendRegionList(List<DictTreeVo> allRegionTree, Long regionId) {
    DictTreeVo root = new DictTreeVo();
    root.setId(-1L);
    root.setChildren(allRegionTree);

    DictTreeVo curNode = null;
    Queue<DictTreeVo> q=new LinkedList<>();
    q.add(root);


    DictTreeVo p;
    while(!q.isEmpty()){
      p =  q.poll();
      if (p == null || p.getId() == null) {
        continue;
      }
      if (p.getId().equals(regionId)) {
        curNode = p;
        break;
      }
      if (CollectionUtils.isNotEmpty(p.getChildren())) {
        q.addAll(p.getChildren());
      }
    }

    if (curNode == null) {
      return Lists.newArrayList(regionId);
    }
    return loopFindChildRegion(curNode);
  }

  private static List<Long> loopFindChildRegion(DictTreeVo node) {
    if (node == null) {
      return Collections.emptyList();
    }

    List<Long> result = Lists.newArrayList();
    Stack<DictTreeVo> nodeStack = new Stack<>();
    nodeStack.push(node);

    while (!nodeStack.empty()) {
      DictTreeVo n = nodeStack.pop();
      if (CollectionUtils.isEmpty(n.getChildren())) {
        result.add(n.getId().longValue());
      } else {
        for (DictTreeVo vo : n.getChildren()) {
          if (vo != null) {
            nodeStack.push(vo);
          }
        }
      }
    }

    return result;
  }
}
