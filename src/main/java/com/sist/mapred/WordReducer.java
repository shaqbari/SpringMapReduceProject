package com.sist.mapred;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

//같은단어끼리 묶어주는 클래스                               단어		1				단어	묶는값
/**
 * @author sist
 *	Hello 1
 *  Helo 1    
 *  Hello 1
 *  
 *   묶어주는거는 자동으로 해준다.
 *   ==> Hello[1, 1, 1] ==>Hello 3
 */
public class WordReducer extends Reducer<Text, IntWritable, Text, IntWritable>{
    private IntWritable res=new IntWritable();

	@Override
	protected void reduce(Text key, Iterable<IntWritable> values,
			Reducer<Text, IntWritable, Text, IntWritable>.Context context) throws IOException, InterruptedException {
			int sum=0;
			for (IntWritable i : values) {
				sum+=i.get();//get은 인트로 바꾼다.
				
			}
			res.set(sum);// set은 intwriterble로 바꾼다.
			context.write(key, res);
		
	}
	
    
	
}
