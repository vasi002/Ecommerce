package test;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import webdriver.WebDriverManager;

public class Ord {

    private static WebDriver driver;
 
    @BeforeClass
    public static void setUp() {
    	WebDriverManager.chromedriver();
       // System.setProperty("webdriver.chrome.driver", "C:\\Users\\Vasigaran R\\eclipse-workspace\\sele\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
    }
    
    

    @Test
    public void runAllTests() throws InterruptedException 
    {
        testLogin();
        Thread.sleep(5000);

        addItemToCart();
        Thread.sleep(5000);

        testCheckout();
        Thread.sleep(5000);
    }

    public void testLogin() 
    {
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        String title = driver.getTitle();
        if (title.contains("Swag"))
        {
            System.out.println("Login Successful");
        } else {
            System.out.println("Login Failed");
        }
    }

    public void addItemToCart()                  
    {
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        driver.findElement(By.className("shopping_cart_link")).click();

        boolean isItemAdded = driver.findElement(By.className("inventory_item_name")).isDisplayed();

        if (isItemAdded) 
        {
            System.out.println("Product added to cart");
        }
        else
        {
            System.out.println("Product not added");
        }
    }

    public void testCheckout() 
    {
        driver.findElement(By.className("shopping_cart_link")).click();
        driver.findElement(By.id("checkout")).click();
        driver.findElement(By.id("first-name")).sendKeys("Vasi");
        driver.findElement(By.id("last-name")).sendKeys("garan");
        driver.findElement(By.id("postal-code")).sendKeys("698546");
        driver.findElement(By.id("continue")).click();
        driver.findElement(By.id("finish")).click();

        String successMessage = driver.findElement(By.className("complete-header")).getText();
        if (successMessage.toLowerCase().contains("thank you")) 
        {
            System.out.println("Checkout Success");
       	    System.out.println(successMessage);
        } 
        else 
        {
            throw new AssertionError("Checkout not success");
        }        
    }

    @AfterClass
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
            System.out.println("Browser closed.");

        }
    }
    
}






//package test;
//
//import org.junit.AfterClass;
//import org.junit.BeforeClass;
//import org.junit.Test;
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//import org.junit.Assert;
//
//import java.time.Duration;
//
//public class Orderingproducts {
//
//    private static WebDriver driver;
//    private static WebDriverWait wait;
//
//    @BeforeClass
//    public static void setUp() {
//        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Vasigaran R\\eclipse-workspace\\sele\\chromedriver.exe");
//        driver = new ChromeDriver();
//        wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Explicit wait
//        driver.manage().window().maximize();
//        driver.get("https://www.saucedemo.com/");
//    }
//
//    @Test
//    public void runAllTests() {
//        testLogin();
//        addItemToCart();
//        testCheckout();
//    }
//
//    public void testLogin() {
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user-name"))).sendKeys("standard_user");
//        driver.findElement(By.id("password")).sendKeys("secret_sauce");
//        driver.findElement(By.id("login-button")).click();
//
//        WebElement productTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("title")));
//        Assert.assertEquals("PRODUCTS", productTitle.getText()); // Validates successful login
//
//        System.out.println("Login successful and validated.");
//    }
//
//    public void addItemToCart() {
//        wait.until(ExpectedConditions.elementToBeClickable(By.id("add-to-cart-sauce-labs-backpack"))).click();
//        driver.findElement(By.className("shopping_cart_link")).click();
//
//        WebElement item = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("inventory_item_name")));
//        Assert.assertTrue(item.isDisplayed());
//
//        System.out.println("Product added to cart and validated.");
//    }
//
//    public void testCheckout() {
//        driver.findElement(By.id("checkout")).click();
//
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("first-name"))).sendKeys("Vasi");
//        driver.findElement(By.id("last-name")).sendKeys("garan");
//        driver.findElement(By.id("postal-code")).sendKeys("698546");
//        driver.findElement(By.id("continue")).click();
//
//        wait.until(ExpectedConditions.elementToBeClickable(By.id("finish"))).click();
//
//        WebElement message = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("complete-header")));
//        String actualMessage = message.getText();
//
//        Assert.assertTrue(actualMessage.contains("THANK YOU"));
//
//        System.out.println("Checkout completed successfully with message: " + actualMessage);
//    }
//
////    @AfterClass
////    public static void tearDown() {
////        if (driver != null) {
////            driver.quit();
////            System.out.println("Browser closed.");
////        }
////    }
//}