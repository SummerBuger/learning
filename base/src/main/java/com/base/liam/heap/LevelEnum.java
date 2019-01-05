package com.base.liam.heap;

public enum LevelEnum {
  NOT_SUPPORT(0),
  FIRST_LEVEL(1),
  SECOND_LEVEL(2),
  THIRD_LEVEL(3);

  private int code;

  private LevelEnum(int code) {
    this.code = code;
  }

  public int getCode() {
    return this.code;
  }

  public void setCode(int code) {
    this.code = code;
  }

  public static LevelEnum getLevelEnumByCode(int code) {
    LevelEnum[] var1 = values();
    int var2 = var1.length;

    for (int var3 = 0; var3 < var2; ++var3) {
      LevelEnum levelEnum = var1[var3];
      if (levelEnum.code == code) {
        return levelEnum;
      }
    }

    return NOT_SUPPORT;
  }
}
