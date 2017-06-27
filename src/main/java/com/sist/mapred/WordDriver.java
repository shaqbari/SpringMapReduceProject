package com.sist.mapred;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class WordDriver {
	public static void main(String[] args) {

		// 이코딩은 스프링에 이미 되어 있다. mapper
		try {
			Configuration conf = new Configuration();
			Job job = Job.getInstance(conf);

			job.setMapperClass(WordMapper.class);// 자를떄 사용할 클래스 선택
			job.setReducerClass(WordReducer.class);
			job.setJarByClass(WordDriver.class);

			job.setOutputKeyClass(Text.class);
			job.setOutputValueClass(IntWritable.class);
			FileInputFormat.addInputPath(job, new Path("./input_data"));// 작업할 path //폴더지정해주면 그안의 파일을 모두 분석한다.
			FileOutputFormat.setOutputPath(job, new Path("./output")); // 출력할
																		// path

			job.waitForCompletion(true);
			// 파일이 이미 있으면 안된다.

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}

	}

}
