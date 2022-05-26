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
		//1.指定索引库的位置
		Directory directory = FSDirectory.open(new File("F:/indexRepo"));
		//2.
		IndexReader indexReader = DirectoryReader.open(directory);
		//3.创建查询索引的对象
		IndexSearcher indexSearcher = new IndexSearcher(indexReader);
		//4.指定查询方法  query:指定条件  n:查询数据量的限制
		Query query = new TermQuery(new Term("content", "spring"));
		TopDocs search = indexSearcher.search(query, 1000);
		//总记录数
		System.out.println(search.totalHits);
		//
		indexReader.close();
	}

}
