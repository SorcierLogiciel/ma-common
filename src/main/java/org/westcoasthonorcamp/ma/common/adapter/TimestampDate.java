package org.westcoasthonorcamp.ma.common.adapter;

import java.sql.Timestamp;
import java.util.Date;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * 
 * @author Joshua
 */
public class TimestampDate extends XmlAdapter<Date, Timestamp>
{

	@Override
	public Date marshal(Timestamp obj) throws Exception
	{
		return new Date(obj.getTime());
	}

	@Override
	public Timestamp unmarshal(Date obj) throws Exception
	{
		return new Timestamp(obj.getTime());
	}
	
}
