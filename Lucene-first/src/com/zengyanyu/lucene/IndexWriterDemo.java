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

//����д��
public class IndexWriterDemo {

	public static void main(String[] args) throws Exception {
		//1.����������Ŀ¼
		Directory directory = FSDirectory.open(new File("F:/indexRepo"));
		//��׼�ķִ���
		Analyzer analyzer = new StandardAnalyzer();
		IndexWriterConfig config = new IndexWriterConfig(Version.LATEST, analyzer);
		//2.����д�������Ķ���
		IndexWriter indexWriter = new IndexWriter(directory, config);
		//3.��ȡԴ�ĵ�
		File srcFile = new File(
				"D:\\��Ƶ\\17-Lucene&solr����&����\\Lucene&solr-day01\\Lucene&solr-day01\\����\\�Ͽ��õĲ�ѯ����searchsource");
		File[] listFiles = srcFile.listFiles();
		for (File file : listFiles) {
			Document doc = new Document();
			//�ļ�����
			String fileName = file.getName();
			Field nameField = new TextField("name", fileName, Store.YES);
			doc.add(nameField);
			//�ļ���С
			long fileSize = FileUtils.sizeOf(file);
			Field sizeField = new TextField("size", fileSize + "", Store.YES);
			doc.add(sizeField);
			//�ļ�·��
			String filePath = file.getPath();
			Field pathField = new TextField("path", filePath, Store.YES);
			doc.add(pathField);
			//�ļ�����
			String fileContent = FileUtils.readFileToString(file);
			Field contentField = new TextField("content", fileContent, Store.YES);
			doc.add(contentField);
			//
			indexWriter.addDocument(doc);
		}
		//�ر���Դ
		indexWriter.close();
	}

}
