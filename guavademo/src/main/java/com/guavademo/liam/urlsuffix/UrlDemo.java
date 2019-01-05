package com.guavademo.liam.urlsuffix;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.net.InternetDomainName;
import de.malkusch.whoisServerList.publicSuffixList.PublicSuffixList;
import de.malkusch.whoisServerList.publicSuffixList.PublicSuffixListFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

/**
 * Created by chaochun.ccc on 2017-05-31.
 */
public class UrlDemo {

  public static void main(String[] args) {

    InternetDomainName from = InternetDomainName.from("http://e.sm.cn/test");
    System.out.println(from.hasPublicSuffix());

//    ImmutableMap<String, PublicSuffixType> excluded = PublicSuffixPatterns.EXCLUDED;
//    System.out.println(excluded.values().contains(PublicSuffixType.PRIVATE));
//    ImmutableMap<String, PublicSuffixType> under1 = PublicSuffixPatterns.UNDER;
//    System.out.println(under1.values().contains(PublicSuffixType.PRIVATE));
//    ImmutableMap<String, PublicSuffixType> exact = PublicSuffixPatterns.EXACT;
//    System.out.println(exact.values().contains(PublicSuffixType.PRIVATE));
//    ArrayList<String> extraList = Lists.newArrayList();
//    for (Map.Entry<String, PublicSuffixType> entry : exact.entrySet()) {
//      if (entry.getValue() == PublicSuffixType.PRIVATE) {
//        extraList.add(entry.getKey());
//      }
//    }
//    Collections.sort(extraList);
//    for (String s : extraList) {
//      System.out.println(s);
//    }


//    ArrayList<String> excludedList = Lists.newArrayList(exact.keySet());
//    Collections.sort(excludedList);
//    System.out.println(excludedList);
//    ImmutableMap<String, PublicSuffixType> under = PublicSuffixPatterns.UNDER;
//    ArrayList<String> underList = Lists.newArrayList(under.keySet());
//    ArrayList<String> withStar = Lists.newArrayList();
//    for (String s : underList) {
//      withStar.add("*." + s);
//    }
//    Collections.sort(withStar);
//    System.out.println("------------------------");
//    for (String s : withStar) {
//      System.out.println(s);
//    }
//    System.out.println("------------------------");
//    System.out.println(withStar);
//    System.out.println(withStar.size());
//
//    String domain = "www.smaile.shop.biuz";
//    pslApiTest(domain);
//    guavaPslTest(domain);
  }

  public static void guavaPslTest(String domain) {
    System.out.println("============================================");
    InternetDomainName internetDomainName = InternetDomainName.from(domain);
    System.out.println(internetDomainName);
    System.out.println("hasPublicSuffix: " + internetDomainName.hasPublicSuffix());
    System.out.println("publicSuffix:" + internetDomainName.publicSuffix());
    System.out.println(internetDomainName.isPublicSuffix());
    System.out.println(internetDomainName.isTopPrivateDomain());
    System.out.println(internetDomainName.topPrivateDomain());
  }

  public static void pslApiTest(String domain) {

    PublicSuffixListFactory publicSuffixListFactory = new PublicSuffixListFactory();
    PublicSuffixList publicSuffixList = publicSuffixListFactory.build();
    boolean isPublicSuffix = publicSuffixList.isPublicSuffix(domain);
    boolean isRegistrable = publicSuffixList.isRegistrable(domain);
    String registrableDomain = publicSuffixList.getRegistrableDomain(domain);
    String publicSuffix = publicSuffixList.getPublicSuffix(domain);
    System.out.println(isPublicSuffix);
    System.out.println(isRegistrable);
    System.out.println(registrableDomain);
    System.out.println(publicSuffix);

  }
}
