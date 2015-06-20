package org.westcoasthonorcamp.ma.common.message;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 
 * @author Joshua
 */
@NoArgsConstructor
@AllArgsConstructor
public class Status implements Serializable
{

	private static final long serialVersionUID = 2326826449032765001L;	

	@Getter
	@Setter
	private String serverUrl;
	
	@Getter
	@Setter
	private long systemUpdateId;
	
	@Getter
	@Setter
	private boolean reload;
	
	@Getter
	@Setter
	private long musicUpdateId;
	
	@Getter
	@Setter
	private int musicId;
	
	@Getter
	@Setter
	private long startTime;
	
	@Getter
	@Setter
	private boolean override;
	
}
