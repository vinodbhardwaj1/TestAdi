package apiUtils;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class ApiProcessor {
    private static final Logger logger = LoggerFactory.getLogger(ApiProcessor.class);

    private RequestSpecBuilder requestSpecBuilder;
    private RequestSpecification requestSpec;
    private ResponseSpecBuilder responseSpecBuilder;

    private void initRequestResponse(String baseUrl, Map<String, String> pathParams, Object payload) {
        requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.setBaseUri(baseUrl);
        requestSpecBuilder.setContentType(ContentType.JSON);
        if (pathParams != null)
            requestSpecBuilder.addPathParams(pathParams);

        requestSpec = requestSpecBuilder.build();
        if (payload != null)
            requestSpec.body(payload);

        responseSpecBuilder = new ResponseSpecBuilder();
        responseSpecBuilder.expectContentType(ContentType.JSON);
    }


    private Response getResponse(String requestType, String endPoint) {
        Response response = null;
        try {
            if (requestType.equalsIgnoreCase("GET")) {
                response = given().spec(requestSpec).get(endPoint);
            } else if (requestType.equalsIgnoreCase("POST")) {
                response = given().spec(requestSpec).post(endPoint);
            } else if (requestType.equalsIgnoreCase("PUT")) {
                response = given().spec(requestSpec).put(endPoint);
            } else if (requestType.equalsIgnoreCase("DELETE")) {
                response = given().spec(requestSpec).delete(endPoint);
            }
            logger.info("API Response Time: " + response.time() / 1000 + " Sec");

        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        return response;
    }


    public Response getApiResponse(String baseUrl, String endPoint, Map<String, String> pathParams, Object payload, String requestType) {
        this.initRequestResponse(baseUrl, pathParams, payload);
        return this.getResponse(requestType, endPoint);
    }


    public Object getJsonFile(String filePath) throws IOException, ParseException {
        JSONParser jsonParser = new JSONParser();
        FileReader reader = new FileReader(filePath);
        return jsonParser.parse(reader);
    }


}
