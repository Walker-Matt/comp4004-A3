package selenium;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.beans.factory.annotation.Autowired;

import config.SeleniumTest;
import selenium.page.IndexPage;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertNotNull;

import java.util.concurrent.TimeUnit;

/**
 * 
 */
@SeleniumTest
public class GameTest extends AbstractSeleniumTest {

    @Autowired
    private IndexPage indexPage;
    
    @Test
    public void processAI() {
    	//9:connect AI and check all AI players have made their choices   
    	this.indexPage.connect.click();
    	this.indexPage.open.click();
    	this.indexPage.start.click();
    	assertNotNull(this.indexPage.otherPlayer1Cards);
    	assertNotNull(this.indexPage.otherPlayer2Cards);
    	assertNotNull(this.indexPage.otherPlayer3Cards);
    	this.delay(1);
    	assertThat(this.indexPage.hasText("Processing all AI decisions"), is(true));
    	this.indexPage.disconnect.click();
    }

	@Test
    public void canUseStayOption(){
		//10:connect then selecting stay then disconnect
		this.indexPage.connect.click();
    	this.indexPage.open.click();
    	this.indexPage.start.click();
    	this.indexPage.stay.click();
    	assertThat(this.indexPage.hasText("You decided to STAY."), is(true));
    	this.indexPage.disconnect.click();
    }

    @Test
    public void canUseHitOption() {
    	//11:connect then selecting hit then disconnect
    	this.indexPage.connect.click();
    	this.indexPage.open.click();
    	this.indexPage.start.click();
    	this.indexPage.hit.click();
    	assertThat(this.indexPage.hasText("You decided to HIT."), is(true));
    	this.indexPage.disconnect.click();
    }

    @Test
    public void canImproveOneCard(){
    	//12:connect, improve and disconnect
    	this.indexPage.connect.click();
    	this.indexPage.open.click();
    	this.indexPage.start.click();
    	this.indexPage.hit.click();
    	this.indexPage.card1.findElement(By.tagName("input")).click();
    	this.indexPage.done.click();
    	assertThat(this.indexPage.hasText("selected"), is(true));
    	assertThat(this.indexPage.hasText("You decided to improve"), is(true));
    	this.indexPage.disconnect.click();
    }
    
    @Test
    public void canImproveTwoCards(){
    	//12:connect, improve and disconnect
    	this.indexPage.connect.click();
    	this.indexPage.open.click();
    	this.indexPage.start.click();
    	this.indexPage.hit.click();
    	this.indexPage.card1.findElement(By.tagName("input")).click();
    	this.indexPage.card2.findElement(By.tagName("input")).click();
    	this.indexPage.done.click();
    	assertThat(this.indexPage.hasText("selected"), is(true));
    	assertThat(this.indexPage.hasText("You decided to improve"), is(true));
    	this.indexPage.disconnect.click();
    }
    
    @Test
    public void canImproveThreeCards(){
    	//12:connect, improve and disconnect
    	this.indexPage.connect.click();
    	this.indexPage.open.click();
    	this.indexPage.start.click();
    	this.indexPage.hit.click();
    	this.indexPage.card1.findElement(By.tagName("input")).click();
    	this.indexPage.card2.findElement(By.tagName("input")).click();
    	this.indexPage.card3.findElement(By.tagName("input")).click();
    	this.indexPage.done.click();
    	assertThat(this.indexPage.hasText("selected"), is(true));
    	assertThat(this.indexPage.hasText("You decided to improve"), is(true));
    	this.indexPage.disconnect.click();
    }
    
    @Test
    public void canImproveFourCards(){
    	//12:connect, improve and disconnect
    	this.indexPage.connect.click();
    	this.indexPage.open.click();
    	this.indexPage.start.click();
    	this.indexPage.hit.click();
    	this.indexPage.card1.findElement(By.tagName("input")).click();
    	this.indexPage.card2.findElement(By.tagName("input")).click();
    	this.indexPage.card3.findElement(By.tagName("input")).click();
    	this.indexPage.card4.findElement(By.tagName("input")).click();
    	this.indexPage.done.click();
    	assertThat(this.indexPage.hasText("selected"), is(true));
    	assertThat(this.indexPage.hasText("You decided to improve"), is(true));
    	this.indexPage.disconnect.click();
    }
    
    @Test
    public void canImproveFiveCards(){
    	//12:connect, improve and disconnect
    	this.indexPage.connect.click();
    	this.indexPage.open.click();
    	this.indexPage.start.click();
    	this.indexPage.hit.click();
    	this.indexPage.card1.findElement(By.tagName("input")).click();
    	this.indexPage.card2.findElement(By.tagName("input")).click();
    	this.indexPage.card3.findElement(By.tagName("input")).click();
    	this.indexPage.card4.findElement(By.tagName("input")).click();
    	this.indexPage.card5.findElement(By.tagName("input")).click();
    	this.indexPage.done.click();
    	assertThat(this.indexPage.hasText("selected"), is(true));
    	assertThat(this.indexPage.hasText("You decided to improve"), is(true));
    	this.indexPage.disconnect.click();
    }
}
