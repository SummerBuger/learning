package com.base.liam;

import com.fasterxml.jackson.core.type.TypeReference;
import com.google.common.base.Charsets;
import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.common.io.Files;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by chaochun.ccc on 2017-06-13.
 */
public class Main {
  private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

  private static final Splitter LINE_SPLITTER = Splitter.on("\t").trimResults().omitEmptyStrings();

  public static void main(String[] args) throws IOException {
    String ideaFile = "/Users/liam/test/idea.context.all.log";

//    checkValidJson(ideaFile);
     test1();

  }

  public static void test1() {
    ImmutableList<String> map = ImmutableList.of("http://e.sm.cn/cpt/web/fs/cptimg/10000388/brandstyle4/image1_10000388_48220a042e2c0bae1b2bea6d3d97ea03.jpg",
            "http://e.sm.cn/cpt/web/fs/cptimg/66755/brandstyle1/image1_66755_06667de3929cea8e95897cd9a9a2eadc.jpg",
            "http://e.sm.cn/cpt/web/fs/cptimg/2050/brandstyle3/image4_2050_d0968d37df73b69f33fbad78413a1146.jpg",
            "http://e.sm.cn/cpt/web/fs/cptimg/2050/brandstyle3/image3_2050_56fb463e409fc8e8b48e96c8e36f6410.jpg",
            "http://e.sm.cn/cpt/web/fs/cptimg/2050/brandstyle3/image2_2050_d927e7142f36597760bc1c50e4539f4e.jpg",
            "http://e.sm.cn/cpt/web/fs/cptimg/1167/brandstyle4/image6_1167_88df0b59b3148a25818d61e4547506d5.jpg",
            "http://e.sm.cn/cpt/web/fs/cptimg/99317/brandstyle3/image4_99317_5f471e4bb51bf7c7eb5e1662b257b10c.jpg",
            "http://e.sm.cn/cpt/web/fs/cptimg/99317/brandstyle3/image3_99317_2a6815969cdf3118633a8119ce5128cb.jpg",
            "http://e.sm.cn/cpt/web/fs/cptimg/99317/brandstyle3/image2_99317_7113216fe30d98ebc84d7fb112176fd4.jpg",
            "http://e.sm.cn/cpt/web/fs/cptimg/99317/brandstyle3/image5_99317_725b69b9c8912e6b1b8f12d376ce2c04.jpg",
            "http://e.sm.cn/cpt/web/fs/cptimg/99317/brandstyle3/image1_99317_7e3aabeaecfedd063f3974ad34ec6ef7.jpg",
            "http://e.sm.cn/cpt/web/fs/cptimg/2423/brandstyle3/image3_2423_b2332a013b13ff3a3aaa80a4ddd8e786.jpg",
            "http://e.sm.cn/cpt/web/fs/cptimg/2423/brandstyle3/image2_2423_d8c982e0351b25cb2f8efbff5f5c14dc.jpg",
            "http://e.sm.cn/cpt/web/fs/cptimg/1157/brandstyle3/image4_1157_6f700f5f8b1fe130e9cc25afce22f6fe.jpg",
            "http://e.sm.cn/cpt/web/fs/cptimg/1157/brandstyle3/image3_1157_d3f4b5a9ed5739a088af27e2c28945d7.jpg",
            "http://e.sm.cn/cpt/web/fs/cptimg/1157/brandstyle3/image2_1157_67548270c49d631b90a2566645d91ce3.jpg",
            "http://e.sm.cn/cpt/web/fs/cptimg/1157/brandstyle3/image5_1157_bd923ff118fdff13048bc907d66b0e93.jpg",
            "http://e.sm.cn/cpt/web/fs/cptimg/1157/brandstyle3/image1_1157_d5a6781132de7ecc7ae5bbde27fb90f2.jpg",
            "http://e.sm.cn/cpt/web/fs/cptimg/10008089/brandstyle4/image4_10008089_0b6e48f18ec1e1b837cea21038514a3b.jpg",
            "http://e.sm.cn/cpt/web/fs/cptimg/10008089/brandstyle4/image3_10008089_830815796fd4df683a351d0c09d0d9c6.jpg",
            "http://e.sm.cn/cpt/web/fs/cptimg/10008089/brandstyle4/image2_10008089_c19248081616a0a3fb7245a35930a004.jpg",
            "http://e.sm.cn/cpt/web/fs/cptimg/10008089/brandstyle4/image5_10008089_d9fccedc850d38a66b343276a75095a3.jpg",
            "http://e.sm.cn/cpt/web/fs/cptimg/10008089/brandstyle4/image6_10008089_cb6e96e702bfb5ab1a4a3411d18a2185.jpg",
            "http://e.sm.cn/cpt/web/fs/cptimg/878/brandstyle1/image1_878_1131287edd3ca522cde00525fd0d7dce.jpg",
            "http://e.sm.cn/cpt/web/fs/cptimg/1591/brandstyle3/image4_1591_297e80e1c87bb957f12812c4842724a0.jpg",
            "http://e.sm.cn/cpt/web/fs/cptimg/1591/brandstyle3/image3_1591_a292bdcb975550869466cf6f065e9ad4.jpg",
            "http://e.sm.cn/cpt/web/fs/cptimg/1591/brandstyle3/image2_1591_87a5913e025e8bc802edc6832d784f82.jpg",
            "http://e.sm.cn/cpt/web/fs/cptimg/1591/brandstyle3/image5_1591_a6b3980f0d318c7ea641d92d4c4eda72.jpg",
            "http://e.sm.cn/cpt/web/fs/cptimg/1591/brandstyle3/image1_1591_fadc65a180fe5d57e65c93bfc5528d5a.jpg",
            "http://e.sm.cn/cpt/web/fs/cptimg/10006635/brandstyle2/image1_10006635_6265cd82ded3c664db15ad2608d12e65.jpg",
            "http://e.sm.cn/cpt/web/fs/cptimg/10911022/brandstyle3/image4_10911022_700ec742139d3b65da9fdd854630b17d.jpg",
            "http://e.sm.cn/cpt/web/fs/cptimg/10911022/brandstyle3/image3_10911022_f060b71fee44634727b19d2c5661d3d6.jpg",
            "http://e.sm.cn/cpt/web/fs/cptimg/10911022/brandstyle3/image2_10911022_bf0c649467fdbd99382ea5ccf9248f19.jpg",
            "http://e.sm.cn/cpt/web/fs/cptimg/10911022/brandstyle3/image5_10911022_4423659538789cefbd5f5d765a6edd93.jpg",
            "http://e.sm.cn/cpt/web/fs/cptimg/10911022/brandstyle3/image1_10911022_45eac157eb38a0cbb638daa1c4b24d5d.jpg",
            "http://e.sm.cn/cpt/web/fs/cptimg/11131015/brandstyle2/image1_11131015_653814f87c9f1ff655e6989e9ccadda9.jpg",
            "http://e.sm.cn/cpt/web/fs/cptimg/11170590/brandstyle3/image4_11170590_cc76a0f523f6a5317ce8933062e8f85d.jpg",
            "http://e.sm.cn/cpt/web/fs/cptimg/11170590/brandstyle3/image3_11170590_2584e65f7606b9cc6e6a5be5c23bdf6c.jpg",
            "http://e.sm.cn/cpt/web/fs/cptimg/11170590/brandstyle3/image2_11170590_8a55023fde9a9360b6477280caa617d3.jpg",
            "http://e.sm.cn/cpt/web/fs/cptimg/11170590/brandstyle3/image1_11170590_e76a2fb686694026d3aea6e6620f25e0.jpg",
            "http://e.sm.cn/cpt/web/fs/cptimg/1380/brandstyle1/image1_1380_60564db9645845f03bf6f5b77fdf85da.jpg",
            "http://e.sm.cn/cpt/web/fs/cptimg/878/brandstyle1/image1_878_1131287edd3ca522cde00525fd0d7dce.jpg",
            "http://e.sm.cn/cpt/web/fs/cptimg/1591/brandstyle3/image4_1591_013238adf276cba0d44e61495e4c8253.jpg",
            "http://e.sm.cn/cpt/web/fs/cptimg/1591/brandstyle3/image3_1591_63e44ecb0fbcf489580dd13cc3a057ec.jpg",
            "http://e.sm.cn/cpt/web/fs/cptimg/1591/brandstyle3/image2_1591_2e5ebf499bbb4e82b008106804e31385.jpg",
            "http://e.sm.cn/cpt/web/fs/cptimg/1591/brandstyle3/image5_1591_22167f57845576399f3a0e540f30dc50.jpg",
            "http://e.sm.cn/cpt/web/fs/cptimg/1591/brandstyle3/image1_1591_6ae5bc0fb26ee1cd45b53ffbc3e25a92.jpg",
            "http://e.sm.cn/cpt/web/fs/cptimg/1591/brandstyle3/image4_1591_013238adf276cba0d44e61495e4c8253.jpg",
            "http://e.sm.cn/cpt/web/fs/cptimg/1591/brandstyle3/image3_1591_63e44ecb0fbcf489580dd13cc3a057ec.jpg",
            "http://e.sm.cn/cpt/web/fs/cptimg/1591/brandstyle3/image2_1591_2e5ebf499bbb4e82b008106804e31385.jpg",
            "http://e.sm.cn/cpt/web/fs/cptimg/1591/brandstyle3/image5_1591_22167f57845576399f3a0e540f30dc50.jpg");


    Set<String> set = Sets.newHashSet();
    for (String s : map) {
      set.add(s);
    }

    for (String s : set) {
      String http = s;
      String https = s.replace("http", "https");
      System.out.println(".put(\"" + https + "\", \"" + http + "\")");
    }
  }

  public static void checkValidJson(String fileName) throws IOException {
    List<String> lines = null;
    try {
      lines = Files.readLines(new File(fileName), Charsets.UTF_8);
    } catch (IOException e) {
      e.printStackTrace();
    }

    File logFile = new File("/Users/liam/test/content.check.log");

    Map<String, List<String>> invalidDataMap = Maps.newHashMap();
    File picTestHtml = new File("/Users/liam/test/pic_all_test.hml");
    File httpsPicHtml = new File("/Users/liam/test/pic_https_test.hml");

    System.out.println("<html>\r\n<body>");
    Files.append("<html>\r\n<body>\r\n", picTestHtml, Charsets.UTF_8);
    Files.append("<html>\r\n<body>\r\n", httpsPicHtml, Charsets.UTF_8);
    for (int i = 0; i < lines.size(); i += 3) {
      String id = lines.get(i + 1);
      String content = lines.get(i + 2);
      StringBuilder sbr = new StringBuilder().append(id).append(":").append(content);
      try {
        if ("content".equals(content) || "NULL".equals(content)) {
          continue;
        }
        Map<String, String> map =
            NewJsonUtils.fromJsonToMap(content, new TypeReference<Map<String, String>>() {});

        for (Map.Entry<String, String> entry : map.entrySet()) {
          String value = entry.getValue();
          if (entry.getValue().startsWith("http") && (entry.getValue().endsWith("jpg")
              || value.endsWith("jpeg") || value.endsWith("png"))) {
            Files.append("id: " + id + " key: " + entry.getKey() + " imgUrl: " + value
                + "  <img src=\"" + value + "\" /><br />\r\n", picTestHtml, Charsets.UTF_8);
            // System.out.println(id + " " + entry.getKey() + " " + value);
            if (value.startsWith("https://e.sm.cn/cpt/web/fs")) {
              Files.append("id: " + id + " key: " + entry.getKey() + " imgUrl: " + value
                      + "  <img src=\"" + value + "\" /><br />\r\n", httpsPicHtml, Charsets.UTF_8);
              System.out.println("\"" + value + "\",");
            }
          }
        }

      } catch (Exception e) {
        System.out.println(content);
        if (invalidDataMap.containsKey(e.getMessage())) {
          invalidDataMap.get(e.getMessage()).add(sbr.toString());
        } else {
          invalidDataMap.put(e.getMessage(), Lists.newArrayList(sbr.toString()));
        }
      }
    }
    Files.append("</body>\r\n</html>\r\n", picTestHtml, Charsets.UTF_8);
    Files.append("</body>\r\n</html>\r\n", httpsPicHtml, Charsets.UTF_8);

    for (Map.Entry<String, List<String>> entry : invalidDataMap.entrySet()) {
      String log = fileName + " #@# " + entry.getKey() + " #@# "
          + NewJsonUtils.toJson(entry.getValue()) + "\r\n";
      try {

        Files.append(log, logFile, Charsets.UTF_8);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    System.out.println("=-=-=-=-=-=-= " + fileName + " : " + invalidDataMap.values().size());
  }
}
