package com.labcorp.e2e.step_definitions;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.labcorp.pojo.User;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import static org.hamcrest.Matchers.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.TimeZone;

public class MockarooApiServiceStepDefs {

    RequestSpecification request = RestAssured.given();

    private String id;
    private Response response;
    private final static String USER_END_POINT = "https://6143a99bc5b553001717d06a.mockapi.io/testapi/v1/Users";

    @Given("user sets the header content type and accept that as {string}")
    public void i_set_request_header_content_type_as(String contentType) {
        request.header("Content-Type", contentType);
        request.header("Accept", "application/json");
    }

    @When("user makes a post request with a user payload and verifies the status code and id")
    public void user_makes_a_post_request_with_a_user_payload_and_verifies_the_status_code_and_id() throws JsonProcessingException {

        User user = new User();
        user.setCreatedAt(1631825833);
        user.setEmployee_firstname("TestData12345");
        user.setEmployee_lastname("TestData12345");
        user.setEmployee_phonenumbe("264-783-9453");
        user.setAdemployee_emaildress("ademployee_emaildress 1");
        user.setCitemployee_addressy("citemployee_addressy 1");
        user.setStateemployee_dev_level("stateemployee_dev_level 1");
        user.setEmployee_gender("employee_gender 1");
        SimpleDateFormat format = new SimpleDateFormat(
                "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US);
        format.setTimeZone(TimeZone.getTimeZone("UTC"));
        try {
            user.setEmployee_hire_date(format.parse("2025-10-31T16:35:45.426Z"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        user.setEmployee_onleave(true);
        user.setTech_stack(new ArrayList<>());
        user.setProject(new ArrayList<>());

        ObjectMapper mapper = new ObjectMapper();
        String jsonObject = mapper.writeValueAsString(user);

        Response response = request.given().body(jsonObject).post(USER_END_POINT);

        response.then()
                .assertThat()
                .statusCode(201)
                .body("$", hasKey("id"));

        User responseUser = response.getBody().as(User.class);
        id = responseUser.getId();
        Assert.assertEquals(201, response.getStatusCode());
    }

    @And("user makes a get request with a user id")
    public void user_makes_a_get_request_with_a_user_id(){
       response = request.given().get(USER_END_POINT + "/" + id);
    }

    @Then("user should get valid status code and payload")
    public void user_should_get_valid_status_code_and_payload() {
        response.then().assertThat()
                .statusCode(200)
                .body("employee_firstname",equalTo("TestData12345"))
                .body("employee_lastname",equalTo("TestData12345"))
                .body("createdAt", equalTo(1631825833))
                .body("employee_phonenumbe", equalTo("264-783-9453"))
                .body("ademployee_emaildress", equalTo("ademployee_emaildress 1"))
                .body("citemployee_addressy", equalTo("citemployee_addressy 1"))
                .body("stateemployee_dev_level", equalTo("stateemployee_dev_level 1"))
                .body("employee_gender", equalTo("employee_gender 1"))
                .body("employee_hire_date", equalTo("2025-10-31T16:35:45.426Z"))
                .body("employee_onleave", equalTo(true))
                .body("tech_stack", hasSize(0))
                .body("project", hasSize(0));
    }
}
