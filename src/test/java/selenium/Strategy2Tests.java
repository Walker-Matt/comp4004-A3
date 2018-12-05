package selenium;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.beans.factory.annotation.Autowired;

import ca.carleton.poker.game.entity.card.Card;
import ca.carleton.poker.game.entity.card.Hand;
import ca.carleton.poker.game.entity.card.Rank;
import ca.carleton.poker.game.entity.card.Suit;
import config.SeleniumTest;
import selenium.page.IndexPage;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import java.util.concurrent.TimeUnit;

/**
 * 
 */
@SeleniumTest
public class Strategy2Tests extends AbstractSeleniumTest {

    @Autowired
    private IndexPage indexPage;
    
    /* AI 1 is first to decide, uses strategy 1, and stays
     * AI 2 is not first to decide, sees no player with 3 same, uses strategy 1, and hits
     * AI 3 is not first to decide, sees player with 3 same, has no pairs, exchanges all cards
     */
    @Test
    public void S2Test1(){
    	this.indexPage.connect.click();
    	this.delay(1);
    	this.indexPage.open.click();
    	this.delay(1);
    	this.indexPage.rig.click();
    	
    	/************************* Set initial hands ************************/
    	// Player royal flush
    	this.waitForAlert();
    	Alert a = this.webDriver.switchTo().alert();
    	a.sendKeys("rank-10 hearts, rank-j hearts, rank-q hearts, rank-k hearts, rank-a hearts");
    	a.accept();
    	
    	//AI 1 straight flush
    	this.waitForAlert();
    	a = this.webDriver.switchTo().alert();
    	a.sendKeys("rank-2 spades, rank-3 spades, rank-4 spades, rank-5 spades, rank-6 spades");
    	a.accept();
    	
    	//AI 2 pair
    	this.waitForAlert();
    	a = this.webDriver.switchTo().alert();
    	a.sendKeys("rank-2 spades, rank-3 clubs, rank-4 spades, rank-6 diams, rank-6 spades");
    	a.accept();
    	
    	//AI 3 high card
    	this.waitForAlert();
    	a = this.webDriver.switchTo().alert();
    	a.sendKeys("rank-2 hearts, rank-3 spades, rank-4 clubs, rank-9 spades, rank-k clubs");
    	a.accept();
    	
    	/****************************** check user options ************************/
    	//AI 2 exchanges
    	this.waitForAlert();
    	a = this.webDriver.switchTo().alert();
    	a.sendKeys("0:rank-3 spades, 1:rank-3 hearts, 2:rank-3 diams");
    	a.accept();
    	
    	//AI 3 exchanges
    	this.waitForAlert();
    	a = this.webDriver.switchTo().alert();
    	a.sendKeys("0:rank-5 spades, 1:rank-8 clubs, 2:rank-q hearts, 3:rank-3 spades, 4:rank-6 diams");
    	a.accept();
    	
    	this.delay(3);
    	this.indexPage.stay.click();
    	
    	/*****************  Check results ******************/
    	this.delay(3);
    	//AI 1 stayed
    	String AI_1_id = "AI-1243513";
    	assertThat(this.indexPage.hasText(AI_1_id + " choose to STAY"), is(true));
    	
    	//AI 2 hit
    	String AI_2_id = "AI-1243514";
    	assertThat(this.indexPage.hasText(AI_2_id + " choose to HIT"), is(true));
    	
    	//AI 3 hit
    	String AI_3_id = "AI-1243515";
    	assertThat(this.indexPage.hasText(AI_3_id + " choose to HIT"), is(true));

    	this.indexPage.disconnect.click();
    }
    
    /*  AI 1 is first to decide, uses strategy 1, and hits
     *  AI 2 is not first to decide, sees no player with 3 same, uses strategy 1, and stays
     *  AI 3 is not first to decide, sees player with 3 same, has one pair, exchanges other three cards
     */
    @Test
    public void S2Test2(){
    	this.indexPage.connect.click();
    	this.delay(1);
    	this.indexPage.open.click();
    	this.delay(1);
    	this.indexPage.rig.click();
    	
    	/************************* Set initial hands ************************/
    	// Player royal flush
    	this.waitForAlert();
    	Alert a = this.webDriver.switchTo().alert();
    	a.sendKeys("rank-10 hearts, rank-j hearts, rank-q hearts, rank-k hearts, rank-a hearts");
    	a.accept();
    	
    	//AI 1 high card
    	this.waitForAlert();
    	a = this.webDriver.switchTo().alert();
    	a.sendKeys("rank-2 hearts, rank-3 spades, rank-4 clubs, rank-9 spades, rank-k clubs");
    	a.accept();
    	
    	//AI 2 straight flush
    	this.waitForAlert();
    	a = this.webDriver.switchTo().alert();
    	a.sendKeys("rank-2 spades, rank-3 spades, rank-4 spades, rank-5 spades, rank-6 spades");
    	a.accept();
    	
    	//AI 3 pair
    	this.waitForAlert();
    	a = this.webDriver.switchTo().alert();
    	a.sendKeys("rank-2 hearts, rank-2 spades, rank-4 clubs, rank-9 spades, rank-k clubs");
    	a.accept();
    	
    	/****************************** check user options ************************/
    	//AI 1 exchanges
    	this.waitForAlert();
    	a = this.webDriver.switchTo().alert();
    	a.sendKeys("0:rank-5 spades, 1:rank-5 clubs, 2:rank-5 hearts, 3:rank-3 spades, 4:rank-6 diams");
    	a.accept();
    	
    	//AI 3 exchanges
    	this.waitForAlert();
    	a = this.webDriver.switchTo().alert();
    	a.sendKeys("2:rank-q hearts, 3:rank-3 clubs, 4:rank-6 clubs");
    	a.accept();
    	
    	this.delay(3);
    	this.indexPage.stay.click();
    	
    	/*****************  Check results ******************/
    	this.delay(3);
    	//AI 1 hit
    	String AI_1_id = "AI-1243516";
    	assertThat(this.indexPage.hasText(AI_1_id + " choose to HIT"), is(true));
    	
    	//AI 2 stayed
    	String AI_2_id = "AI-1243517";
    	assertThat(this.indexPage.hasText(AI_2_id + " choose to STAY"), is(true));
    	
    	//AI 3 hit
    	String AI_3_id = "AI-1243518";
    	assertThat(this.indexPage.hasText(AI_3_id + " choose to HIT"), is(true));

    	this.indexPage.disconnect.click();
    }
    
    //AI 3 is not first to decide, sees player with 3 same, has two pairs, exchanges other card
    @Test 
    public void S2Test3(){
    	this.indexPage.connect.click();
    	this.delay(1);
    	this.indexPage.open.click();
    	this.delay(1);
    	this.indexPage.rig.click();
    	
    	/************************* Set initial hands ************************/
    	// Player royal flush
    	this.waitForAlert();
    	Alert a = this.webDriver.switchTo().alert();
    	a.sendKeys("rank-10 hearts, rank-j hearts, rank-q hearts, rank-k hearts, rank-a hearts");
    	a.accept();
    	
    	//AI 1 high card
    	this.waitForAlert();
    	a = this.webDriver.switchTo().alert();
    	a.sendKeys("rank-2 hearts, rank-3 spades, rank-4 clubs, rank-9 spades, rank-k clubs");
    	a.accept();
    	
    	//AI 2 straight flush
    	this.waitForAlert();
    	a = this.webDriver.switchTo().alert();
    	a.sendKeys("rank-2 spades, rank-3 spades, rank-4 spades, rank-5 spades, rank-6 spades");
    	a.accept();
    	
    	//AI 3 two pair
    	this.waitForAlert();
    	a = this.webDriver.switchTo().alert();
    	a.sendKeys("rank-2 hearts, rank-2 spades, rank-4 clubs, rank-9 spades, rank-9 clubs");
    	a.accept();
    	
    	/****************************** check user options ************************/
    	//AI 1 exchanges
    	this.waitForAlert();
    	a = this.webDriver.switchTo().alert();
    	a.sendKeys("0:rank-5 spades, 1:rank-5 clubs, 2:rank-5 hearts, 3:rank-3 spades, 4:rank-6 diams");
    	a.accept();
    	
    	//AI 3 exchanges
    	this.waitForAlert();
    	a = this.webDriver.switchTo().alert();
    	a.sendKeys("2:rank-3 clubs");
    	a.accept();
    	
    	this.delay(3);
    	this.indexPage.stay.click();
    	
    	/*****************  Check results ******************/
    	this.delay(3);
    	//AI 1 hit
    	String AI_1_id = "AI-1243519";
    	assertThat(this.indexPage.hasText(AI_1_id + " choose to HIT"), is(true));
    	
    	//AI 2 stayed
    	String AI_2_id = "AI-1243520";
    	assertThat(this.indexPage.hasText(AI_2_id + " choose to STAY"), is(true));
    	
    	//AI 3 hit
    	String AI_3_id = "AI-1243521";
    	assertThat(this.indexPage.hasText(AI_3_id + " choose to HIT"), is(true));

    	this.indexPage.disconnect.click();
    }
}
