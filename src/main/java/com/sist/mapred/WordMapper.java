package com.sist.mapred;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import java.util.*;

//단어 자르는 클래스
public class WordMapper extends Mapper<LongWritable, Text, Text, IntWritable>{
	private final IntWritable one=new IntWritable(1); //단어 자를때마다 1을 준다.
	private Text res=new Text();
	
	//줄갯수 만큼 수행해서 단어 잘라 context에 누적해 보내준다. value가 한줄
	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
		StringTokenizer st=new StringTokenizer(value.toString());//stringTokenizer 문자 없으면 공백으로 자른다.
		while (st.hasMoreTokens()) {
			
			//String ==>Text
			res.set(st.nextToken());
						
			context.write(res, one);
			
		}
		
	}
	
	
	
	
	

}
