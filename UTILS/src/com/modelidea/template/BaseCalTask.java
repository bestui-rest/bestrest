package com.modelidea.template;

/**
 * 
 * 
 * @author zyd
 * @version 1.0
 */
public abstract class BaseCalTask {
  

	public BaseCalTask(){
		
	}
	
	public abstract Boolean init();
	
	public abstract Boolean preTask();
	
	public Boolean run(){
		//autocommit false
		
		
   if(init()&&preTask()) {
			
	 if(updateT18_taskForDoing()){
				
			}
			updateT18_taskForDone();
	 }
		  
     Boolean iscontinue=true;
		//commit;
		
		return iscontinue;
	 }
	
	public abstract Boolean delTask();
	
	public Boolean saveException(){
     Boolean iscontinue=true;
		
		
		return iscontinue;
	}
	
	public abstract Boolean finTask() ;
	public   Boolean taskDep(){
    Boolean iscontinue=true;
		
		
		return iscontinue;
	}
	public Boolean updateT18_taskForDoing(){
		
	    Boolean iscontinue=true;
		
		
			return iscontinue;
	}
	public Boolean updateT18_taskForDone (){
		
	    Boolean iscontinue=true;
		
		
			return iscontinue;
	}
	private void preTest(){
		
	}
}
