package io.bauer.benchmark;

import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Group;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

/**
 * 
 */

/**
 * @author jbauer
 *
 */
@State(Scope.Thread)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
public class JSONBenchmark
{
	private final static JacksonBenchmark jackson = new JacksonBenchmark();
	private final static JsoniterBenchmark jsoniter = new JsoniterBenchmark();
	private final static BoonBenchmark boon = new BoonBenchmark();

	
	public static void main(String[] args) throws RunnerException {
		Options options = new OptionsBuilder()
				.include(JSONBenchmark.class.getSimpleName())
				.forks(1)
				.warmupBatchSize(5)
				.warmupIterations(5)
				.measurementBatchSize(5)
				.measurementIterations(1)
				.mode(Mode.AverageTime)
				.measurementTime(TimeValue.seconds(20)) 
				.build();

		new Runner(options).run();
	}
	
	@Setup
	public void setup()
	{
		jackson.setup();
		boon.setup();
		jsoniter.setup();
		
		jackson.warmup();
		boon.warmup();
		jsoniter.warmup();
	}
	
	@Benchmark  
	public int jacksonObjectDeserialize()
	{
		return jackson.objectDeserializeTest();
	}
	
	@Benchmark  
	public int jacksonObjectSerialize()
	{
		return jackson.objectSerializeTest();
	}
	
	@Benchmark  
	public int jacksonRawDeserialize()
	{
		return jackson.rawDerializeTest();
	}
	
	@Benchmark  
	public int boonObjectDeserialize()
	{
		return boon.objectDeserializeTest();
	}
	
	@Benchmark  
	public int boonObjectSerialize()
	{
		return boon.objectSerializeTest();
	}
	
	@Benchmark  
	public int boonRawDeserialize()
	{
		return boon.rawDerializeTest();
	}
	
	@Benchmark  
	public int jsoniterObjectDeserialize()
	{
		return jsoniter.objectDeserializeTest();
	}
	
	@Benchmark  
	public int jsoniterObjectSerialize()
	{
		return jsoniter.objectSerializeTest();
	}
	
	@Benchmark  
	public int jsoniterRawDerialize()
	{
		return jsoniter.rawDeserializeTest();
	}
	
	
}
