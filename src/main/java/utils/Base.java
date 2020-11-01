package utils;

import org.openqa.selenium.WebDriver;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class Base {
    public WebDriver webDriver;
    public HashMap<Object, Object> DataStore = new LinkedHashMap<>();
}
