package org.westcoasthonorcamp.ma.common.data;

import java.sql.Timestamp;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import lombok.Getter;
import lombok.Setter;

import org.westcoasthonorcamp.ma.common.adapter.TimestampDate;
import org.westcoasthonorcamp.ma.common.enums.ScheduleScope;

/**
 * 
 * @author Joshua
 */
@Entity
@XmlRootElement
public class Schedule implements BaseEntity
{
	
	private static final long serialVersionUID = -4443250372917421556L;

	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Getter
	@Setter
	private String name;
	
	@Getter
	@Setter
	@Enumerated(EnumType.STRING)
	private ScheduleScope scheduleScope;

	@Getter(onMethod = @_({@XmlJavaTypeAdapter(TimestampDate.class)}))
	@Setter
	private Timestamp creationTime;
	
	@Getter(onMethod = @_({@XmlJavaTypeAdapter(TimestampDate.class)}))
	@Setter
	private Timestamp nextEventTime;
	
	@Getter
	@Setter
	private int repeatScale;
	
	@Getter
	@Setter
	private boolean repeatLimited;
	
	@Getter
	@Setter
	private int repeatLimit;
	
	@Getter
	@Setter
	private boolean enabled = true;
	
	@Getter(onMethod = @_({@XmlTransient}))
	@ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	@JoinColumn(name = "MUSIC_ID")
	private Music music;
	
	public void setMusic(Music music)
	{
		this.music = music;
		music.getSchedules().add(this);
	}
	
	public boolean isExpired()
	{
		return nextEventTime.before(new Date());
	}
	
	public void generateNextEventTime()
	{
		if(!repeatLimited)
		{
			nextEventTime = new Timestamp(scheduleScope.next(nextEventTime, repeatScale, null).getTime());
		}
		else if(repeatLimited &&  repeatLimit > 0)
		{
			AtomicInteger limit = new AtomicInteger(repeatLimit);
			nextEventTime = new Timestamp(scheduleScope.next(nextEventTime, repeatScale, limit).getTime());
			repeatLimit = limit.get();
		}
	}
	
}
