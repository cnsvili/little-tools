package com.littlenb.tools.type;

import java.util.Objects;

/**
 * <code>ICodeEnum</code>适配器
 *
 * @author svili
 * @see ICodeEnum
 **/
public class CodeEnumAdapter {

  /**
   * adapt to {@link ICodeEnum} with code.
   *
   * @param <T> the type which implements {@link ICodeEnum} and extends {@link Enum}
   */
  public static <T> T toCodeEnum(Object code, Class<T> type) {

    if (!type.isEnum()) {
      throw new IllegalArgumentException(
          String.format("type must be Enum,type : %s", type.getSimpleName()));
    }

    if (!ICodeEnum.class.isAssignableFrom(type)) {
      throw new IllegalArgumentException(
          String.format("type must be a sub class of ICodeEnum,type : %s", type.getSimpleName()));
    }

    for (T t : type.getEnumConstants()) {

      ICodeEnum codeEnum = (ICodeEnum) t;

      if (Objects.equals(codeEnum.getCode(), code)) {
        return t;
      }

    }

    throw new IllegalArgumentException(
        "Cannot convert " + code + " to " + type.getSimpleName() + " by code.");
  }

}
