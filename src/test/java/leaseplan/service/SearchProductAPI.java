package leaseplan.service;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import net.serenitybdd.core.environment.EnvironmentSpecificConfiguration;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.util.EnvironmentVariables;

public class SearchProductAPI {

    EnvironmentVariables environmentVariables;

    public String getBaseURL() {
        return EnvironmentSpecificConfiguration.from(environmentVariables).getProperty("restapi.baseurl");
    }
    public Response getSearchResults(String keyword) {
        String url=getBaseURL() + "/api/v1/search/test/{keyword}";
       return SerenityRest.given()
                .pathParams("keyword", keyword)
                .contentType(ContentType.JSON)
                .get(url);
    }
}
