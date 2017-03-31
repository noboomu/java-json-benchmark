/**
 * 
 */
package io.bauer.benchmark;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;

import io.bauer.benchmark.models.User;

 
/**
 * @author jbauer
 *
 */
public abstract class Benchmark
{
	public byte[] jsonData = null;
	
	public List<User> users = new ArrayList<>();
	
	public long startTime = 0l;
	
	public String phase = null;
	
	public void startTimer(String phase)
	{
		this.phase = phase;
		//System.out.println("Starting [" + this.phase + "]");
		this.startTime = System.currentTimeMillis();
	}
	
	public void stopTimer()
	{
		final long totalTime = System.currentTimeMillis() - this.startTime;
		System.out.println("Completed [" + this.phase + "] in " + Duration.ofMillis(totalTime)); 
	}
 
	
	public void setup()
	{
		this.loadResources();
	}
	
	public void loadResources()
	{
		try
		{
			
			Path path = Paths.get("./resources/data/users.json");
			
			this.jsonData = Files.readAllBytes(path); 
			 
			this.users = JacksonBenchmark.mapper.readValue(this.jsonData, new TypeReference<List<User>>(){} );
			
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
