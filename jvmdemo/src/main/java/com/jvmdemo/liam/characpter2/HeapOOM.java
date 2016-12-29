package com.jvmdemo.liam.characpter2;

import java.util.ArrayList;
import java.util.List;

/**
 * -verbose:gc
 * -Xms20M   初始堆内存大小  默认为物理内存的 1/64 ( <1G )
 * -Xmx20M   最大
 * -Xmn10M
 * -XX:+PrintGCDetails  打印GC 详细信息
 * -XX:SurvivorRatio=8  Eden区与Survivor区的大小比值
 *
 * Created by guavademo on 2016/11/21.
 */
public class HeapOOM {

    static class OOMObject{

    }

    public static void main(String[] args) {
        List<OOMObject> list = new ArrayList<OOMObject>();
        while (true) {
            list.add(new OOMObject());
        }
    }
}
