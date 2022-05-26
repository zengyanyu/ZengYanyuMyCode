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
		//1.指定索引库的位置
		Directory directory = FSDirectory.open(new File("F:/indexRepo"));
		//2.
		IndexReader indexReader = DirectoryReader.open(directory);
		//3.创建查询索引的对象
		IndexSearcher indexSearcher = new IndexSearcher(indexReader);
		//4.指定查询方法  query:指定条件  n:查询数据量的限制
		Query query = new TermQuery(new Term("content", "spring"));
		//5.获取插叙结果
		TopDocs search = indexSearcher.search(query, 100);
		//总记录数
		System.out.println("总记录数  = " + search.totalHits);
		ScoreDoc[] scoreDocs = search.scoreDocs;
		for (ScoreDoc scoreDoc : scoreDocs) {
			int docID = scoreDoc.doc;
			Document doc = indexSearcher.doc(docID);
			//获取文档内容
			System.out.println("文件名称:" + doc.get("name"));
			System.out.println("文件大小:" + doc.get("size"));
			System.out.println("文件路径:" + doc.get("path"));
			//System.out.println("文件内容:" + doc.get("content"));
			System.out.println("**************************************************************************");
			System.out.println("**************************************************************************");
		}
		//
		indexReader.close();
	}

}
