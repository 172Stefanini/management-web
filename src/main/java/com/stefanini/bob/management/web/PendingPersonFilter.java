package com.stefanini.bob.management.web;

public class PendingPersonFilter {

	private Long workGroupId;
	
	public PendingPersonFilter(){
		
	}
	
	public PendingPersonFilter(Long workGroupIdParam){
		this.workGroupId = workGroupIdParam;
	}

	/**
	 * @return the workGroupId
	 */
	public Long getWorkGroupId() {
		return workGroupId;
	}

	/**
	 * @param workGroupId the workGroupId to set
	 */
	public void setWorkGroupId(Long workGroupId) {
		this.workGroupId = workGroupId;
	}
}
