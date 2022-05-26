package com.zengyanyu.lucene;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.wltea.analyzer.lucene.IKAnalyzer;

//�ִ�������
public class AnalyzerDemo {

	public static void main(String[] args) throws Exception {
		//��׼�ִ���
		//Analyzer analyzer = new StandardAnalyzer();//Ӣ�İ��տո�ִ�,������һ����һ����
		//Analyzer analyzer = new CJKAnalyzer();//CJK���պ��ִ���(����������������)
		//�������й��˷ִ���(��Ӣ�ĵķִ�Ч�����Ǻܺ�,������Ӣ��ȱ��ĸ�����)
		//Analyzer analyzer = new SmartChineseAnalyzer();
		Analyzer analyzer = new IKAnalyzer();
		//		TokenStream tokenStream = analyzer.tokenStream(null,
		//				"The spring framework provides a comprehensive programming and configuration model ");
		TokenStream tokenStream = analyzer.tokenStream(null,
				"apacheȫ�ļ����ǽ�������java.���ƴ��ǲ���ָ�����������λ�� config apache�ܽ��״��ǲ���");
		CharTermAttribute addAttribute = tokenStream.addAttribute(CharTermAttribute.class);
		tokenStream.reset();
		while (tokenStream.incrementToken()) {
			System.out.println(addAttribute);
		}
		/**
		 * ��ȡ���ִ���֮��ľ�������
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
