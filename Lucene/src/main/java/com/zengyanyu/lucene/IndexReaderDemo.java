package com.zengyanyu.lucene;

import java.io.File;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

public class IndexReaderDemo {

	public static void main(String[] args) throws Exception {
		//1.ָ���������λ��
		Directory directory = FSDirectory.open(new File("F:/indexRepo"));
		//2.
		IndexReader indexReader = DirectoryReader.open(directory);
		//3.������ѯ�����Ķ���
		IndexSearcher indexSearcher = new IndexSearcher(indexReader);
		//4.ָ����ѯ����  query:ָ������  n:��ѯ������������
		Query query = new TermQuery(new Term("content", "spring"));
		//5.��ȡ������
		TopDocs search = indexSearcher.search(query, 100);
		//�ܼ�¼��
		System.out.println("�ܼ�¼��  = " + search.totalHits);
		ScoreDoc[] scoreDocs = search.scoreDocs;
		for (ScoreDoc scoreDoc : scoreDocs) {
			int docID = scoreDoc.doc;
			Document doc = indexSearcher.doc(docID);
			//��ȡ�ĵ�����
			System.out.println("�ļ�����:" + doc.get("name"));
			System.out.println("�ļ���С:" + doc.get("size"));
			System.out.println("�ļ�·��:" + doc.get("path"));
			//System.out.println("�ļ�����:" + doc.get("content"));
			System.out.println("**************************************************************************");
			System.out.println("**************************************************************************");
		}
		//
		indexReader.close();
	}

}
