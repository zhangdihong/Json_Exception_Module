package FastJsonHttpMessageConverterEx;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.Map;

import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.HttpMessageNotWritableException;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.forward.exceptionHandler.util.BaseException;
import com.forward.exceptionHandler.util.FieldErrorCompose;
import com.google.common.collect.Maps;

public class FastJsonHttpMessageConverterExt extends FastJsonHttpMessageConverter {

	private Charset chartset=UTF8;
	private SerializerFeature[] features=new SerializerFeature[0];
	public SerializerFeature[] getFeatures() {
		return features;
	}
	public void setFeatures(SerializerFeature... features) {
		this.features = features;
	}
	
	public Charset getChartset() {
		return chartset;
	}
	public void setChartset(Charset chartset) {
		this.chartset = chartset;
	}
	@Override
	protected void writeInternal(Object obj, HttpOutputMessage outputMessage)
			throws IOException, HttpMessageNotWritableException {
		    OutputStream out= outputMessage.getBody();
		    byte[] bytes=null;
		    if(obj instanceof BaseException || obj instanceof FieldErrorCompose){
		    	String text = JSON.toJSONString(obj, features);
		         bytes = text.getBytes(chartset);
		        
		    }else{
		    	Map<String,Object> map=Maps.newHashMap();
		    	map.put("Content",obj);
		    	map.put("success",true);
		    	String text=JSON.toJSONString(map,features);
		    	bytes=text.getBytes(chartset);
		    }
		    out.write(bytes);
	}
	
}
