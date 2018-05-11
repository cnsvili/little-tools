package com.littlenb.tools.type;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import com.alibaba.fastjson.serializer.SerializeWriter;
import java.io.IOException;
import java.lang.reflect.Type;

/**
 * @author svili
 **/
public class CodeEnumSerializer implements ObjectSerializer {

  public static CodeEnumSerializer instance = new CodeEnumSerializer();

  @Override
  public void write(JSONSerializer serializer, Object object, Object fieldName, Type fieldType,
      int features) throws IOException {
    SerializeWriter out = serializer.out;

    if (object == null) {
      out.writeNull();
      return;
    }

    if (!(object instanceof ICodeEnum)) {
      Class<?> clazz = (Class<?>) fieldType;
      throw new JSONException(
          "write " + clazz.getSimpleName() + " error, the field \"" + fieldName
              + "\" is not a sub class of ICodeEnum.");
    }

    ICodeEnum codeEnum = (ICodeEnum) object;
    Object code = codeEnum.getCode();
    if (code instanceof Integer) {
      out.writeInt((Integer) code);
    } else if (code instanceof Float) {
      out.writeFloat((Float) code, true);
    } else if (code instanceof String) {
      out.writeString((String) code);
    } else {
      Class<?> clazz = (Class<?>) fieldType;
      throw new JSONException("write " + clazz.getSimpleName()
          + " error,the field is a sub class of ICodeEnum,but the code type is not in (Integer,Float,String).");
    }

  }
}
