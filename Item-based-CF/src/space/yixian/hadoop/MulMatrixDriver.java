package space.yixian.hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hdfs.DFSClient.Conf;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;

public class MulMatrixDriver extends Configured implements Tool{

	@Override
	public int run(String[] arg0) throws Exception {
		
		Configuration configuration  = new Configuration();
		configuration.set("mapreduce.job.jar","CF.jar");
		
		Job job3 = Job.getInstance(configuration);
		
		job3.setJobName("MulMatrix");
		job3.setMapperClass(MulMatrixMapper3.class);
		job3.setReducerClass(MulMatrixReducer3.class);
		
		job3.setMapOutputKeyClass(Text.class);
		job3.setMapOutputValueClass(Text.class);
		
		job3.setOutputValueClass(DoubleWritable.class);
		job3.setOutputKeyClass(Text.class);
		
		FileInputFormat.addInputPath(job3, new Path(arg0[0])); 
   		FileOutputFormat.setOutputPath(job3, new Path(arg0[1])); 
		  		
		
   		return job3.waitForCompletion(true)?0:1;
	}
	
		
}
