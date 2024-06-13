package BaiKiemTra1;

import common.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.DriverManager;

import java.time.Duration;

public class BaiKiemTra1 extends BaseTest {
    public void loginCRM() {
        driver.get("https://cms.anhtester.com/login");
        driver.findElement(By.xpath("//input[@id='email']")).sendKeys("admin@example.com");
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys("123456");
        driver.findElement(By.xpath("//button[normalize-space()='Login']")).click();
    }
    @Test
    public void addProduct(Method method) throws Exception{

        loginCRM();
        sleep(3);
        Actions action = new Actions(driver);

        driver.findElement(By.xpath("//span[normalize-space()='Products']")).click();
        sleep(1);
        driver.findElement(By.xpath("//span[contains(@class, 'aiz-side-nav-text') and contains(text(), 'Add New Product')]")).click();
        sleep(1);
        driver.findElement(By.xpath("//input[@placeholder='Product Name']")).sendKeys("quà tặng");
        sleep(1);
        driver.findElement(By.xpath("//div[contains(text(),'Sport shoes')]")).click();
        driver.findElement(By.xpath("(//span[@class='text'][normalize-space()='---- T-shirt'])[1]")).click();
        sleep(1);
        driver.findElement(By.xpath("//div[contains(text(),'Select Brand')]")).click();
        driver.findElement(By.xpath("//span[normalize-space()='Demo brand1']")).click();
        sleep(1);
        driver.findElement(By.xpath("//input[@placeholder='Unit (e.g. KG, Pc etc)']")).sendKeys("kg");
        sleep(1);
        driver.findElement(By.xpath("//input[@placeholder='0.00']")).clear();
        driver.findElement(By.xpath("//input[@placeholder='0.00']")).sendKeys("4");
        sleep(1);
        driver.findElement(By.xpath("//input[@name='min_qty']")).clear();
        driver.findElement(By.xpath("//input[@name='min_qty']")).sendKeys("2");
        sleep(1);
        driver.findElement(By.xpath("//span[@role='textbox']")).sendKeys("product tag");
        action.sendKeys(Keys.ENTER).build().perform();
        sleep(3);
        driver.findElement(By.xpath("//div[@data-multiple='true']//div[@class='form-control file-amount'][normalize-space()='Choose File']")).click();
        driver.findElement(By.xpath("//input[@placeholder='Search your files']")).sendKeys("icon");
        sleep(3);
        driver.findElement(By.xpath("/html[1]/body[1]/div[3]/div[1]/div[1]/div[2]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/img[1]")).click();
        driver.findElement(By.xpath("//button[normalize-space()='Add Files']")).click();
        sleep(1);
        driver.findElement(By.xpath("//input[@placeholder='Unit price']")).sendKeys("8000");
        driver.findElement(By.xpath("//input[@placeholder='Discount']")).clear();
        driver.findElement(By.xpath("//input[@placeholder='Discount']")).sendKeys("5");
        driver.findElement(By.xpath("//div[@class='col-md-3']//div[@class='filter-option-inner-inner'][normalize-space()='Flat']")).click();
        driver.findElement(By.xpath("//a[@id='bs-select-6-1']")).click();
        sleep(3);
        driver.findElement(By.xpath("//input[@placeholder='Quantity']")).clear();
        driver.findElement(By.xpath("//input[@placeholder='Quantity']")).sendKeys("10");
        driver.findElement(By.xpath("//button[normalize-space()='Save & Unpublish']")).click();
        sleep(2);
    }

    @Test
    public void verifyAddProduct(Method method) {

        loginCRM();
        Actions action = new Actions(driver);
        String producrName = "quà tặng";
        String totalStock = "10";

        driver.findElement(By.xpath("//span[normalize-space()='Products']")).click();
        sleep(1);
        driver.findElement(By.xpath("//span[normalize-space()='All products']")).click();
        sleep(1);
        driver.findElement(By.id("search")).sendKeys("quà tặng");
        action.sendKeys(Keys.ENTER).build().perform();
        sleep(4);
        String getProductName = driver.findElement(By.xpath("(//span[@class='text-muted text-truncate-2'])[1]")).getText();
        System.out.println(getProductName);
        Assert.assertEquals(producrName, getProductName, "FAILED. Product Name not match.");
        sleep(2);
        String getTotalStock = driver.findElement(By.xpath("//td[normalize-space()='10']")).getText();
        System.out.println(getTotalStock);
        Assert.assertEquals(totalStock, getTotalStock, "FAILED. Total Stock not match.");
        sleep(2);

    }


}

