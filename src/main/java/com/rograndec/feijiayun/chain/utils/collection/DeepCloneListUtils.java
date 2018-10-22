package com.rograndec.feijiayun.chain.utils.collection;

import java.io.*;
import java.util.List;

/**
 * 功能描述 深度复制List集合
 * Created by dong.ma on 2017/11/04 15:48
 */
public class DeepCloneListUtils {
    public static <T> List<T> deepCopy(List<T> src) throws IOException, ClassNotFoundException {
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(byteOut);
        out.writeObject(src);

        ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
        ObjectInputStream in = new ObjectInputStream(byteIn);
        @SuppressWarnings("unchecked")
        List<T> dest = (List<T>) in.readObject();
        return dest;
    }

}
