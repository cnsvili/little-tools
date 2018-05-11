package com.littlenb.tools.type;


/**
 * 枚举类型扩展
 *
 * 实现此接口的枚举类型,称之为<code>CodeEnum</code>
 *
 * @param <T> Integer or Float or String
 * @author svili
 */
public interface ICodeEnum<T> {

  T getCode();

}
