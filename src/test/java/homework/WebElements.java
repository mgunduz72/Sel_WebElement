package homework;

import static org.testng.Assert.fail;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WebElements {
	String name;
	String lastName;
	int num;
	int num1;
	int num2;
	Faker data = new Faker();
	List<String> checkBoxes;
	List<String> radio;
	
WebDriver driver;
	
	@BeforeClass //runs once for all tests
	public void setUp() {
		System.out.println("Setting up WebDriver in BeforeClass...");
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().window().fullscreen();
		name = data.name().firstName();
		lastName = data.name().lastName();
		num = data.number().numberBetween(1, 4);
		num1 = data.number().numberBetween(1, 4);
		num2 = data.number().numberBetween(1, 4);
		checkBoxes= new ArrayList<>();
		checkBoxes.add("First Question" + data.number().numberBetween(1, 4));
		checkBoxes.add("Second Question" + data.number().numberBetween(1, 4));
		checkBoxes.add("Third Question" + data.number().numberBetween(1, 4));
		radio = new ArrayList<>();
		radio.add("First Question" + data.number().numberBetween(1, 4));
		radio.add("Second Question" + data.number().numberBetween(1, 4));
		radio.add("Third Question" + data.number().numberBetween(1, 4));
		
	}
	
	
	@Test
	public void homework() throws InterruptedException {
		driver.navigate().to("https://forms.zohopublic.com/murodil/form/SeleniumWebElements/formperma/eCecYgX4WMcmjxvXVq6UdhA2ABXIoqPAxnAF8H8CCJg");
	    driver.findElement(By.xpath("//input[@name='SingleLine']")).sendKeys(name + Keys.TAB);
	    driver.findElement(By.xpath("//input[@name='SingleLine1']")).sendKeys(lastName + Keys.TAB);
	    Select tag = new Select(driver.findElement(By.name("Dropdown")));
	    tag.selectByIndex(num);
	    Select tag1 = new Select(driver.findElement(By.name("Dropdown1")));
	    tag1.selectByIndex(num1);
	    Select tag2 = new Select(driver.findElement(By.name("Dropdown2")));
	    tag2.selectByIndex(num2);
	    checkBox(checkBoxes);
	    radioButton(radio);
	    driver.findElement(By.xpath("//button[@elname='submit']")).click();
	}
	
	public void checkBox(List<String> box) throws InterruptedException {
		for (String str : box) {
			String word = str.substring(0, str.length()-1);
			int num = Integer.parseInt(str.substring(str.length()-1));
			
			String choice = "";
			switch(num) {
			case 1:
				choice = "Answer A"; break;
			case 2:
				choice = "Answer B"; break;
			case 3:
				choice = "Answer C"; break;
			default:
				fail(num + "no valid selection"); break;
				
			}
			Thread.sleep(500);
			String address = "//input[@type='checkbox' and @rowvalue ='"+ word +"' and @columnvalue ='"+ choice +"']";
			
			driver.findElement(By.xpath(address)).click();
		}
		
		
	}
	public void radioButton(List<String> button) {
			for (String str : button) {
				String radio = str.substring(0, str.length()-1);
				int number = Integer.parseInt(str.substring(str.length()-1));
				
				String selection = "";
				
				switch(number) {
				case 1:
					selection = "Answer A"; break;
				case 2:
					selection = "Answer B"; break;
				case 3:
					selection = "Answer C"; break;
				default:
					fail(number + "no valid selection"); break;
					
				}
				driver.findElement(By.xpath("//input[@type='radio' and @rowvalue='"+radio+"' and @columnvalue='"+selection+"']")).click();
				
			}
		}

	/*
	 * Homework:
	 * 	Loop through each inputbox and enter random names
	 *  Loop through each dropDown and randomly select by index
	 *  Loop through each checkBoxes and select each one
	 *  Loop through each radioButton and click one by one by waiting one second intervals
	 *  click all buttons
	 */
}

