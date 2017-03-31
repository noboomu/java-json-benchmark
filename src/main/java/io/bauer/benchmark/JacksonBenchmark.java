 
 
package io.bauer.benchmark;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.afterburner.AfterburnerModule;

import io.bauer.benchmark.models.User;
 
/****
 * @author jbauer
 *
 */
public class JacksonBenchmark extends Benchmark
{
	public static ObjectMapper mapper = new ObjectMapper();
	
	static {
		
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		mapper.configure(DeserializationFeature.EAGER_DESERIALIZER_FETCH,true); 
		mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
	
		mapper.registerModule(new AfterburnerModule());

		
	}
	final static TypeReference<List<User>> userListType =  new TypeReference<List<User>>(){};

	public JacksonBenchmark()
	{
		this.setup();
	}

 
	public int objectDeserializeTest( )
	{
		// TODO Auto-generated method stub
		
 		
 	 
			try
			{
				mapper.readValue(jsonData, userListType);

			} catch (Exception e)
			{
				e.printStackTrace();
			} 
			
			return 1;
 		
	}

	/* (non-Javadoc)
	 * @see json.Benchmark#objectSerializeTest()
	 */
	 
	public int objectSerializeTest( )
	{
		 
			try
			{
				mapper.writeValueAsString(users);
			} catch (JsonProcessingException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 
		 return 1;
		
	}

 
	 
	public int rawDerializeTest()
	{ 
		try
		{
			mapper.readTree(jsonData);
		} catch (Exception e)
		{
			e.printStackTrace();
		} 
		
		return 1;
		
	}
	

	 
	public void warmup()
	{
		for( int i = 0; i < 1000; i++ )
		{ 
			try
			{
				mapper.readValue(jsonData, userListType);
				mapper.writeValueAsString(users);

			} catch ( Exception e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
		
	}

	 
}
