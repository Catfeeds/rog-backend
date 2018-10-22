package com.rograndec.feijiayun.chain.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.charset.Charset;


public class SerializeUtil {
	private static final Charset charset = Charset.forName("UTF-8");
	private static final String hexStr = "0123456789ABCDEF";
	public static <T> byte[] serialize(T object) throws Exception {
		if (null == object) {
			return null;
		}

		if (!(object instanceof Serializable)) {
			throw new IllegalArgumentException("Object don't implement serializable interface.");
		}

		ObjectOutputStream oos = null;
		ByteArrayOutputStream baos = null;
		try {
			baos = new ByteArrayOutputStream();
			oos = new ObjectOutputStream(baos);
			oos.writeObject(object);
			byte[] bytes = baos.toByteArray();

			return bytes;
		} catch (Exception e) {
			throw new Exception(object.getClass().getName() + " serialization failure.", e);
		} finally {
			try {
				if (baos != null) {
					baos.close();
				}
				if (oos != null) {
					oos.close();
				}
			} catch (IOException e) {
				throw new Exception("Close stream failure.", e);
			}
		}
	}

	@SuppressWarnings("unchecked")
	public static <T> T deserialize(byte[] bytes) throws Exception {
		if (null == bytes || bytes.length == 0) {
			return null;
		}

		ByteArrayInputStream bais = null;
		ObjectInputStream ois = null;
		try {
			bais = new ByteArrayInputStream(bytes);
			ois = new ObjectInputStream(bais);

			return (T) ois.readObject();
		} catch (Exception e) {
			throw new Exception("Deserialize 【" + toHexString(bytes) + "】 failure.", e);
		} finally {
			try {
				if (bais != null) {
					bais.close();
				}
				if (ois != null) {
					ois.close();
				}
			} catch (IOException e) {
				throw new Exception("Close stream failure.", e);
			}
		}
	}

	public static byte[] serializeString(String string) {
		return (string == null ? null : string.getBytes(charset));
	}

	public static String deserializeString(byte[] bytes) {
		return (bytes == null ? null : new String(bytes, charset));
	}
	
	/**
	 * 转换成16进制
	 * 
	 * @param bytes
	 * @return
	 */
	public static String toHexString(byte[] bytes) {
		// 转换成16进制显示
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < bytes.length; i++) {
			// 字节高4位
			result.append(String.valueOf(hexStr.charAt((bytes[i] & 0xF0) >> 4)))
			// 字节低4位
			.append(String.valueOf(hexStr.charAt(bytes[i] & 0x0F)))
			.append(" ");
		}

		return result.toString();
	}
}
