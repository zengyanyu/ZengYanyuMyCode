package com.zengyanyu.lucene;

import java.io.File;

import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
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
		TopDocs search = indexSearcher.search(query, 1000);
		//�ܼ�¼��
		System.out.println(search.totalHits);
		//
		indexReader.close();
	}

}
