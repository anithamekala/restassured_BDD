package stepdefs;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Shared {

	public RequestSpecification request;
	public Response response;
	public Scenario scenario;
	
	@Before
	public void Method1(Scenario s)
	{
		this.scenario=s;
	}
	@After
	public void Method2(Scenario s)
	{
		s.log(s.getId()+" is" +s.getStatus().name());
	}
	
	}
