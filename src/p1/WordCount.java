package p1;

import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.api.java.utils.ParameterTool;

@SuppressWarnings("serial")
public class WordCount {
	public static void main(String[] args) throws Exception {
		// Start the flink enviroment
		ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();

		// Convert the arguments to a parameters of flink
		ParameterTool params = ParameterTool.fromArgs(args);

		// Set the parameters to flink configuration
		env.getConfig().setGlobalJobParameters(params);
		
		if(params.has("njobs")) {
			env.setParallelism(Integer.parseInt(params.get("njobs")));
		}

		// Read a textFile
		DataSet<String> text = env.readTextFile(params.get("input"));

		// Filter words that start with "N"
		DataSet<String> filtered = text.filter(new FilterFunction<String>() {

			@Override
			public boolean filter(String value) throws Exception {

				return value.startsWith("N");
			}
		});

		// Obtain a tuple of (name, 1)
		DataSet<Tuple2<String, Integer>> tokenized = filtered.map(new Tokenizer());
		
		// Group by name and sum the value
		DataSet<Tuple2<String, Integer>> counts = tokenized.groupBy(0).sum(1);
		
		// Write dataset into a file
		if(params.has("output")) {
			counts.writeAsCsv(params.get("output"), "\n", ";");
			
			env.execute("Word Count");
		}
	}
}

@SuppressWarnings("serial")
final class Tokenizer implements MapFunction<String, Tuple2<String, Integer>> {

	@Override
	public Tuple2<String, Integer> map(String value) throws Exception {

		return new Tuple2<String, Integer>(value, Integer.valueOf(1));
	}

}