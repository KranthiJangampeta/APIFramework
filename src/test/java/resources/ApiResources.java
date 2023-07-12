package resources;

public enum ApiResources {

	SEARCH("/search");

	private String resource;

	ApiResources(String resource) {

		this.resource = resource;
	}

	public String getResource() {

		return resource;
	}

}
