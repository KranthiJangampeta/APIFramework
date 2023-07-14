package stepDefinitions;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.assertEquals;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.SearchResponse;
import resources.ApiResources;
import resources.Utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.runner.Request;

import io.cucumber.datatable.DataTable;

public class catalogResourceStepDefinition extends Utils {

	RequestSpecification reqSpec;
	ResponseSpecification resSpec;
	Response response;
	Request request;

	@Given("construct a fully qualified URL content request with parameters")
	public void construct_a_fully_qualified_URL_content_request_with_parameters(DataTable dataTable)
			throws IOException {

		List<Map<String, String>> queryParameters = dataTable.asMaps();
		for (Map<String, String> queryParameter : queryParameters) {
			generateQueryParameters(queryParameter.get("Key"), queryParameter.get("Value"));
		}

		reqSpec = given().spec(requestSpecification()).queryParams(params);

	}

	@When("user calls {string} resource with {string} http request")
	public void user_calls_resource_with_http_request(String resource, String requestType) {
		System.out.println(ApiResources.valueOf(resource).getResource());

		resSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

		if (requestType.equalsIgnoreCase("get")) {

			response = reqSpec.when().get(ApiResources.valueOf(resource).getResource());

		} else if (requestType.equalsIgnoreCase("post")) {

		}

	}

	@Then("the API call is success with status code {int}")
	public void the_api_call_is_success_with_status_code(Integer int1) {

		assertEquals(response.getStatusCode(), int1);

	}

	@Then("{string} in the response body is {string}")
	public void in_the_response_body_is(String key, String expectedValue) throws IOException {

		assertEquals(getJsonStringValue(response, key), expectedValue);
	}

	@Then("{string} in the response body is {int}")
	public void in_the_response_body_is(String key, Integer expectedValue) throws IOException {

		assertEquals(getJsonIntValue(response, key), expectedValue);

	}

	@Then("{string} in the response body is {string} in result {int}")
	public void in_the_response_body_is_in_result(String key, String expectedValue, Integer resultNumber)
			throws IOException {

		assertEquals(getJsonStringValue(response, key, resultNumber), expectedValue);
	}

}