package com.labcorp.e2e.step_definitions;

import com.labcorp.config.ConfigReader;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class LabCorpStepDefs {

    private WebDriver driver;
    private WebDriverWait wait;
    private String originalHandle;
    private String searchPageHandle;

    @Given("user is on the {string} page")
    public void user_is_on_the_page(String url) {

        WebDriverManager.chromedriver().setup();
        Map<String,Integer> prefs = new HashMap();
        prefs.put("profile.default_content_settings.cookies", 2);
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", prefs);

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(25));
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        driver.get(url);
        originalHandle = driver.getWindowHandle();
    }

    @When("user clicks on career link")
    public void user_clicks_on_career_link() {

        driver.findElement(By.xpath("//div[@id='login-container']//a[text()='Careers']")).click();
        Set<String> windowHandles = driver.getWindowHandles();
        for (String handle : windowHandles  ) {
            if (!originalHandle.equals(handle)){
                driver.switchTo().window(handle);
            }
        }
    }

    @When("user searches for {string} job")
    public void user_searches_for_job(String jobKeyWrd) {
        searchPageHandle = driver.getWindowHandle();
        driver.findElement(By.xpath("//button[@data-ph-at-id='cookie-close-link']")).click();
        WebElement searchInput = driver.findElement(By.xpath("(//input[@placeholder='Search job title or location'])[2]"));
        searchInput.
                sendKeys(jobKeyWrd + Keys.ENTER);
    }

    @When("user selects and browses the position")
    public void user_selects_and_browses_the_position() {
        List<WebElement> jobs = driver.findElements(By.xpath("//div[@class='job-title']//span"));
        Assert.assertEquals(10, jobs.size());
        for(WebElement jobElement: jobs) {
            String jobTitle = jobElement.getText();
            System.out.println(jobTitle);
            if (jobTitle.equalsIgnoreCase("QA Test Automation Developer")) {
                jobElement.click();
                break;
            }
        }
    }

    @When("and user verifies job specific details")
    public void and_user_verifies_job_specific_details(List<Map<String,String>> data) {

        WebElement jobTitle = driver.findElement(By.className("job-title"));
        WebElement jobLocation = driver.findElement(By.xpath("//span[contains(@class,'job-location')]"));
        WebElement jobId = driver.findElement(By.xpath("//span[contains(@class,'jobId')]"));
        WebElement jobInfo = driver.findElement(By.xpath("//div[@data-ph-at-id='jobdescription-text']"));

        Map<String,String> map = data.get(0);

        Assert.assertEquals(map.get("jobTitle"), jobTitle.getText().trim());
        Assert.assertEquals(map.get("jobLocation"), jobLocation.getText().trim().replace("Location\n",""));
        Assert.assertEquals(map.get("jobId"), jobId.getText().split(":")[1].trim());
        Assert.assertTrue(jobInfo.getText().contains(map.get("descriptionOne")));
        Assert.assertTrue(jobInfo.getText().contains(map.get("descriptionTwo")));
        Assert.assertTrue(jobInfo.getText().contains(map.get("descriptionThree")));
        Assert.assertTrue(jobInfo.getText().contains(map.get("toolKeyWord")));
    }

    @When("user clicks on on Apply Now  and confirms Job title, Job location, job id and description")
    public void user_clicks_on_on_Apply_Now_and_confirms_Job_title_Job_location_job_id_and_description(List<Map<String,String>> data) throws InterruptedException {
        driver.findElement(By.xpath("(//ppc-content[contains(text(),'Apply Now')])[1]/parent::a")).click();

        Set<String> windowHandles = driver.getWindowHandles();
        String current = driver.getWindowHandle();
        for (String handle: windowHandles) {
            if (!originalHandle.equals(handle) && !current.equals(handle)){
                driver.switchTo().window(handle);
            }
        }

        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button[@data-automation-id='legalNoticeAcceptButton']"))));
        driver.findElement(By.xpath("//button[@data-automation-id='legalNoticeAcceptButton']")).click();
        driver.findElement(By.xpath("//input[@data-automation-id='email']")).sendKeys(ConfigReader.getProperty("username"));
        driver.findElement(By.xpath("//input[@data-automation-id='password']")).sendKeys(ConfigReader.getProperty("password") + Keys.TAB);
        Thread.sleep(1000);

        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.xpath("//button[@data-automation-id='signInSubmitButton']"))).click().perform();

        wait.until(ExpectedConditions.titleIs("Workday"));
        driver.findElement(By.xpath("//span[text()='View My Applications']")).click();
        driver.findElement(By.xpath("(//div[@data-automation-id='promptOption'])[2]")).click();

        WebElement jobTitle = driver.findElement(By.xpath("//h2[@data-automation-id='jobPostingHeader']"));
        WebElement jobLocation = driver.findElement(By.xpath("//div[@data-automation-id='locations']//dd"));
        WebElement jobId = driver.findElement(By.xpath("//div[@data-automation-id='requisitionId']//dd"));
        WebElement jobDescription = driver.findElement(By.xpath("//div[@data-automation-id='jobPostingDescription']"));

        Map<String,String> map = data.get(0);

        Assert.assertEquals(map.get("jobTitle"), jobTitle.getText().trim());
        Assert.assertEquals(map.get("jobLocation"), jobLocation.getText().trim());
        Assert.assertEquals(map.get("jobId"), jobId.getText());
        Assert.assertTrue(jobDescription.getText().contains(map.get("descriptionOne")));
    }

    @Then("user returns to job search")
    public void user_returns_to_job_search() throws InterruptedException {

        for(String handle : driver.getWindowHandles()) {
            if (!handle.equals(searchPageHandle)) {
                driver.switchTo().window(handle);
                driver.close();
            }
        }
        driver.switchTo().window(searchPageHandle);
        driver.navigate().back();

        Thread.sleep(5000);

        driver.quit();
    }

}
