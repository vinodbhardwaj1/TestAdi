package steps;

import apiUtils.PetsUtil;
import io.cucumber.java.en.Given;
import org.json.simple.parser.ParseException;
import org.testng.Assert;

import java.io.IOException;

public class ApiSteps {

    PetsUtil petsUtil = new PetsUtil();

    @Given("user get all 'available' pets and validate")
    public void getAndValidateAllAvilablePets() {
        boolean flag = petsUtil.getAvailablePets();
        Assert.assertTrue(flag, "Get Pets Operation failed");
    }


    @Given("user add a new pet")
    public void addNewPet() throws IOException, ParseException {
        boolean flag = petsUtil.addNewPet();
        Assert.assertTrue(flag, "Add Pet Operation failed");
    }


    @Given("user update existing pet")
    public void updatePet() throws IOException, ParseException {
        boolean flag = petsUtil.updatePet();
        Assert.assertTrue(flag, "Update Pet Operation failed");
    }

    @Given("user delete an existing pet")
    public void deletePet() {
        boolean flag = petsUtil.deletePet();
        Assert.assertTrue(flag, "Delete Pet Operation failed");
    }
}
