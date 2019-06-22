/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package portal;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;
/**
 * 
 *
 * @author Beka
 */
public class Portal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        System.setProperty("webdriver.gecko.driver", "C:\\Gecko\\geckodriver.exe");
// Create a new instance of the FireFox driver
WebDriver driver = new FirefoxDriver();
// Storing the Application Url in the String variable
String Url = "https://portal.aait.edu.et";
        String gradeReportUrl = "https://portal.aait.edu.et/Grade/GradeReport";
        String Username = "username";
        String Password = "password";

        driver.get(Url);

        //signing into my account
        WebElement usernameTextBox = driver.findElement(By.id("UserName"));
        WebElement passwordTextBox = driver.findElement(By.id("Password"));
        WebElement loginButton = driver.findElement(By.className("btn-success"));

        usernameTextBox.sendKeys(Username);
        passwordTextBox.sendKeys(Password);
        loginButton.click();

       
        driver.navigate().to(gradeReportUrl);

       
        List<WebElement> rows = driver.findElements(By.tagName("tr"));
         StringBuilder gradeReport;
        gradeReport = new StringBuilder();

        for (WebElement  row:
             rows) {
            for (WebElement td:
                 row.findElements(By.tagName("td"))) {
 gradeReport.append(td.getText()).append("  ");
            }
             gradeReport.append("\n");
        }
         write(gradeReport.toString());

        driver.close();
    }
    private static void write(String content) {
        String fileNameToStoreGradeReport = "C:\\Users\\Beka\\Desktop\\webscript\\Grade.txt";

        try {
            Writer writer = new FileWriter(fileNameToStoreGradeReport);
            writer.write(content);
            writer.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
