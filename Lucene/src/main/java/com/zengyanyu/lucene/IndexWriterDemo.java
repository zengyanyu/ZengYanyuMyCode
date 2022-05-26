package com.zengyanyu.lucene;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

//索引写入
public class IndexWriterDemo {

	public static void main(String[] args) throws Exception {
		//1.创建索引库目录
		Directory directory = FSDirectory.open(new File("F:/indexRepo"));
		//标准的分词器
		Analyzer analyzer = new StandardAnalyzer();
		IndexWriterConfig config = new IndexWriterConfig(Version.LATEST, analyzer);
		//2.创建写入索引的对象
		IndexWriter indexWriter = new IndexWriter(directory, config);
		//3.获取源文档
		File srcFile = new File(
				"D:\\视频\\17-Lucene&solr入门&进阶\\Lucene&solr-day01\\Lucene&solr-day01\\资料\\上课用的查询资料searchsource");
		File[] listFiles = srcFile.listFiles();
		for (File file : listFiles) {
			Document doc = new Document();
			//文件名称
			String fileName = file.getName();
			Field nameField = new TextField("name", fileName, Store.YES);
			doc.add(nameField);
			//文件大小
			long fileSize = FileUtils.sizeOf(file);
			Field sizeField = new TextField("size", fileSize + "", Store.YES);
			doc.add(sizeField);
			//文件路径
			String filePath = file.getPath();
			Field pathField = new TextField("path", filePath, Store.YES);
			doc.add(pathField);
			//文件内容
			String fileContent = FileUtils.readFileToString(file);
			Field contentField = new TextField("content", fileContent, Store.YES);
			doc.add(contentField);
			//
			indexWriter.addDocument(doc);
		}
		//关闭资源
		indexWriter.close();
	}

}
