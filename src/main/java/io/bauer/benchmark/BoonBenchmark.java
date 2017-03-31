/**
 * 
 */
package io.bauer.benchmark;

import java.io.IOException;
import java.util.List;

import org.boon.json.JsonFactory;
import org.boon.json.JsonParser;
import org.boon.json.JsonParserFactory;
import org.boon.json.ObjectMapper;
import org.boon.json.serializers.impl.JsonSimpleSerializerImpl;

import com.jsoniter.JsonIterator;

import io.bauer.benchmark.models.User;

/**
 * @author jbauer
 */
public class BoonBenchmark extends Benchmark
{

	public final static JsonSimpleSerializerImpl json = new JsonSimpleSerializerImpl();
	private final JsonParser parser = new JsonParserFactory().create();
	private final ObjectMapper mapper = JsonFactory.create();

	public BoonBenchmark()
	{
		this.setup();
	}

	public int objectDeserializeTest()
	{
		// TODO Auto-generated method stub

		mapper.readValue(jsonData, List.class, User.class);

		return 1;
	}

	/*
	 * (non-Javadoc)
	 * @see json.Benchmark#objectSerializeTest()
	 */

	public int objectSerializeTest()
	{

		json.serialize(users);
		return 1;
	}

	/*
	 * (non-Javadoc)
	 * @see json.Benchmark#rawDerializeTest()
	 */

	public int rawDerializeTest()
	{

		parser.parse(jsonData);
		return 1;
	}

	public void warmup()
	{

		mapper.readValue(jsonData, List.class, User.class);
		json.serialize(users);
	}

}
