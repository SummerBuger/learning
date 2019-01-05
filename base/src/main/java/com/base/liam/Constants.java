package com.base.liam;

import com.google.common.base.Joiner;

/**
 * 常量类
 *
 * @author Arthur yongqian.tyq@alibaba-inc.com
 * @since 2017年06月02日11:53
 */
public interface Constants {

  // ~ ----------------------- whitelist part ----------------------

  int FANGHUA_IDEA_FUNC = 99;

  /**
   * 共有创意styleType对应的用户id
   */
  long COMMON_STYLE_TYPE_USER_ID = -1;

  String STYLE_TYPE_SEPARATOR = ",";

  int IDEA_EXCEL_HEAD_ROW_COUNT = 3;

  Joiner DUN_HAO_JOINER = Joiner.on("、").skipNulls();

  String IDEA_EXCEL_TITLE_PREFIX = "神马品牌专区物料提交表-";

  int IDEA_EXCEL_TITLE_PREFIX_LEN = IDEA_EXCEL_TITLE_PREFIX.length();

  String IDEA_EXCEL_LEFT_TIPS = "注明\n" +
          "（为顺利上传，请仔细阅读）";

  String IDEA_EXCEL_RIGHT_TIPS = "1.请在对应位置填写对应内容，请不要删除、插入或合并任何列或者行，否则无法顺利上传\n" +
          "2.填写时请注意长度限制，否则不能顺利提交。英文、数字、英文标点计算为1个字符，汉字、汉字标点计算为2个字符\n" +
          "3.所有的图片请单独提交，图片如为白底或透明底，请添加1px描边，色值#e5e5e5\n" +
          "4.所有指向URL不能有无法访问的死链，填写时请完整包含\"http://\"或\"https://\"\n" +
          "5.所有指向URL都必须为推广账户注册域名下的url\n" +
          "6.APP的下载地址请提供常规应用市场或推广账户注册域名下的APK下载链接。\n" +
          "7、元素名称为“蓝光xxx”的字段，未升级蓝光样式时不用提交。";

  String IDEA_EXCEL_TITLE_MODULE = "模块";
  String IDEA_EXCEL_TITLE_ELEMENT = "元素";
  String IDEA_EXCEL_TITLE_DESC = "填写说明";
  String IDEA_EXCEL_TITLE_CONTENT = "文字内容";
}

