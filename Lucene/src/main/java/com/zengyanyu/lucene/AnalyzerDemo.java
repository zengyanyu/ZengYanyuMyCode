package com.zengyanyu.lucene;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.wltea.analyzer.lucene.IKAnalyzer;

//分词器测试
public class AnalyzerDemo {

	public static void main(String[] args) throws Exception {
		//标准分词器
		//Analyzer analyzer = new StandardAnalyzer();//英文按照空格分词,对中文一个字一个字
		//Analyzer analyzer = new CJKAnalyzer();//CJK中日韩分词器(中文两个字两个字)
		//聪明的中国人分词器(对英文的分词效果不是很好,出出现英文缺字母的情况)
		//Analyzer analyzer = new SmartChineseAnalyzer();
		Analyzer analyzer = new IKAnalyzer();
		//		TokenStream tokenStream = analyzer.tokenStream(null,
		//				"The spring framework provides a comprehensive programming and configuration model ");
		TokenStream tokenStream = analyzer.tokenStream(null,
				"apache全文检索是将整本书java.马云传智播客指定的索引库的位置 config apache周杰伦传智播客");
		CharTermAttribute addAttribute = tokenStream.addAttribute(CharTermAttribute.class);
		tokenStream.reset();
		while (tokenStream.incrementToken()) {
			System.out.println(addAttribute);
		}
		/**
		 * 获取到分词器之后的具体内容
			spring
			framework
			provides
			comprehensive
			programming
			configuration
			model
		 */
	}

}
