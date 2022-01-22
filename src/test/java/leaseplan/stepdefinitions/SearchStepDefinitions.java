package leaseplan.stepdefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import leaseplan.model.search.SearchResponseItem;
import leaseplan.service.SearchProductAPI;
import net.thucydides.core.annotations.Steps;

import java.util.List;
import java.util.stream.Collectors;

import static net.serenitybdd.rest.SerenityRest.restAssuredThat;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
public class SearchStepDefinitions {

    @Steps
    public SearchProductAPI searchProductAPI;
    Response searchResultsResponse;

    @When("(.*) search for (.*)$")
    public void heCallsEndpointForSearch(String user,String keyword) {
        searchResultsResponse = searchProductAPI.getSearchResults(keyword);
    }

    @Then("he sees the results displayed for {string}")
    public void heSeesTheResultsDisplayedForApple(String keyword) {
        restAssuredThat(response -> response.statusCode(200));
        List<SearchResponseItem> searchList = searchResultsResponse.as(new TypeRef<List<SearchResponseItem>>() {});
        List<String> titleList = searchList.stream().map(se->se.getTitle().toLowerCase() ).collect(Collectors.toList());
        assertThat("Search Results are not as expected",titleList, (everyItem(containsString(keyword))));
    }

    @Then("user does not see result and error message as {string} should be return")
    public void userDoesNotSeeResultAndErrorMessageAsShouldBeReturn(String errorMessage) {
//        SearchErrorResponse errorResponse = searchResultsResponse.as(SearchErrorResponse.class);
//        assertThat("Search Results are not as expected",errorResponse.getDetail().getMessage(), is(errorMessage));
        restAssuredThat(response -> response.body("detail.error", is(true)));
        restAssuredThat(response -> response.body("detail.message", is(errorMessage)));
    }

    @Then("user should get an error message as {string} and does not see search results")
    public void userShouldGetAnErrorMessageAsAndDoesNotSeeSearchResults(String errorMessage) {
        restAssuredThat(response -> response.body("detail.error", is(true)));
        restAssuredThat(response -> response.body("detail.message", is(errorMessage)));
    }
}
