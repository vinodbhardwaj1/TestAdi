package apiUtils;

import io.restassured.response.Response;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.PropertiesHelper;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class PetsUtil {
    private static final Logger logger = LoggerFactory.getLogger(PetsUtil.class);
    HashMap<String, String> pathParams;
    ApiProcessor apiProcessor = new ApiProcessor();

    public boolean getAvailablePets() {
        boolean flag = false;
        Response response = apiProcessor.getApiResponse(PropertiesHelper.getProperty("BASE_URL"), PropertiesHelper.getProperty("GET_PET_BY_STATUS"), null, null, "GET");
        logger.info("Response Code: " + response.statusCode());
        logger.info("Response body: " + response.getBody().asString());
        List<String> allStatus = response.jsonPath().getList("status");
        logger.info("Total available pets: " + allStatus.size());
        logger.info("Expected Status: available");
        for (String s : allStatus) {
            if (!s.equalsIgnoreCase("available")) {
                logger.error("Actuals Status: "+s);
                return false;
            }else flag = true;
        }
        return flag;
    }

    public boolean addNewPet() throws IOException, ParseException {
        boolean flag = false;
        Object payload = apiProcessor.getJsonFile("./src/test/resources/json/newpet.json");
        Response response = apiProcessor.getApiResponse(PropertiesHelper.getProperty("BASE_URL"), PropertiesHelper.getProperty("ADD_PET"), null, payload, "POST");
        logger.info("Response Code: " + response.statusCode());
        logger.info("Response body: " + response.getBody().asString());
        int id = response.jsonPath().getInt("id");
        if(id==123456789 && response.statusCode()==200)
            flag=true;
        return flag;
    }


    public boolean updatePet() throws IOException, ParseException {
        boolean flag = false;
        Object payload = apiProcessor.getJsonFile("./src/test/resources/json/updatepet.json");
        Response response = apiProcessor.getApiResponse(PropertiesHelper.getProperty("BASE_URL"), PropertiesHelper.getProperty("UPDATE_PET"), null, payload, "PUT");
        logger.info("Response Code: " + response.statusCode());
        logger.info("Response body: " + response.getBody().asString());
        String  updatedStatus = response.jsonPath().get("status");
        if(updatedStatus.equals("sold") && response.statusCode()==200)
            flag=true;
        return flag;
    }


    public boolean deletePet() {
        boolean flag = false;
        pathParams = new HashMap<>();
        pathParams.put("petId","123456789");
        Response response = apiProcessor.getApiResponse(PropertiesHelper.getProperty("BASE_URL"), PropertiesHelper.getProperty("DELETE_PET_BY_ID"), pathParams, null, "DELETE");
        logger.info("Response Code: " + response.statusCode());
        logger.info("Response body: " + response.getBody().asString());

        String  message = response.jsonPath().get("message");
        if(response.statusCode()==200 && message.equalsIgnoreCase("123456789"))
            flag=true;
        return flag;
    }


}
