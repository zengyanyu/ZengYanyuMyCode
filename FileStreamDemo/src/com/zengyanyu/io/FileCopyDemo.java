package com.zengyanyu.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author ZengYanyu
 * @Description
 * @Date 2021年3月13日 下午10:17:20
 */
public class FileCopyDemo {

	/**
	 * 四大基流(重点)
	 * InputStream
	 * OutputStream
	 * Reader
	 * Writer
	 * 
	 * 文件流
	 * FileInputStream
	 * FileOutputStream
	 * FileReader
	 * FileWriter
	 * 
	 * 缓冲流
	 * BufferedInputStream
	 * BufferedOutputStream
	 * BufferedReader
	 * BufferedWriter
	 * 
	 * 转换流(把字节转换成字符)
	 * InputStreamReader
	 * OutputStreamWriter
	 * 
	 * 内存流(临时存储数据)
	 * ByteArrayInputStream
	 * ByteArrayOutputStream
	 * CharArrayReader
	 * CharArrayWriter
	 * StringReader
	 * StringWriter
	 * 
	 * 顺序流/合并流
	 * SequenceInputStream
	 * 
	 * 对象流(作用:做序列化和反序列化)
	 * ObjectInputStream
	 * ObjectOutputStream
	 * 
	 * 打印流
	 * PrintStream
	 * PrintWriter
	 * 
	 * 数据流
	 * DataInputStream
	 * DataOutputStream
	 * 
	 * 管道流(用于线程之间交互数据)
	 * PipedInputStream
	 * PipedOutputStream
	 * PipedReader
	 * PipedWriter
	 */
	/**
	 * @author ZengYanyu
	 * @Description
	 * @Date 2021年3月13日 下午10:26:54
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		Files.copy(Paths.get("files/test1.pdf"), new FileOutputStream("files/files_copy.txt"));
		Files.copy(new FileInputStream("files/test1.pdf"), Paths.get("files/files_copy1.txt"));
	}
}
