package resources;
//enum is special class in java which has collection of constants or  methods
public enum APIResources {
	
	getTokenAPI("/"),
	createCapsuleAPI("/capsules"),
	getCapsuleAPI("/capsules");
		
	private String res;
	
	APIResources(String resource)
	{
		this.res=resource;
	}
	
	public String getResource()
	{
		return res;
	}
	

}
