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
public class Strategy1Tests extends AbstractSeleniumTest {

    @Autowired
    private IndexPage indexPage;
    
    @Test //Tests three AIs holding on a straight, flush, and full house
    public void AIHoldsFirstThree() {
    	this.indexPage.connect.click();
    	this.delay(1);
    	this.indexPage.open.click();
    	this.delay(1);
    	this.indexPage.rig.click();
    	
    	/************************* Set initial hands ************************/
    	// Player highcard
    	this.waitForAlert();
    	Alert a = this.webDriver.switchTo().alert();
    	a.sendKeys("rank-2 hearts, rank-3 spades, rank-4 clubs, rank-9 spades, rank-k clubs");
    	a.accept();
    	
    	//AI 1 Straight
    	this.waitForAlert();
    	a = this.webDriver.switchTo().alert();
    	a.sendKeys("rank-5 spades, rank-6 diams, rank-7 hearts, rank-8 clubs, rank-9 spades");
    	a.accept();
    	
    	//AI 2 Flush
    	this.waitForAlert();
    	a = this.webDriver.switchTo().alert();
    	a.sendKeys("rank-2 spades, rank-4 spades, rank-6 spades, rank-8 spades, rank-10 spades");
    	a.accept();
    	
    	//AI 3 Full House
    	this.waitForAlert();
    	a = this.webDriver.switchTo().alert();
    	a.sendKeys("rank-8 spades, rank-4 hearts, rank-4 diams, rank-4 clubs, rank-8 diams");
    	a.accept();
    	
    	/****************************** check user options ************************/
    	this.delay(10);
    	this.indexPage.stay.click();
    	
    	/*****************  Check results ******************/
    	this.delay(3);
    	//AI 1 did nothing (stayed)
    	String AI_1_id = this.webDriver.findElement(By.id("otherHandText1")).getText().substring(21, 31);
    	assertThat(this.indexPage.hasText(AI_1_id + " choose to STAY"), is(true));
    	assertThat(this.indexPage.hasText(AI_1_id + " lost with a score of STRAIGHT, ranked 3!"), is(true));
    	
    	//AI 2 did nothing (stayed)
    	String AI_2_id = this.webDriver.findElement(By.id("otherHandText2")).getText().substring(21, 31);
    	assertThat(this.indexPage.hasText(AI_2_id + " choose to STAY"), is(true));
    	assertThat(this.indexPage.hasText(AI_2_id + " lost with a score of FLUSH, ranked 2!"), is(true));
    	
    	//AI 3 did nothing (stayed)
    	String AI_3_id = this.webDriver.findElement(By.id("otherHandText3")).getText().substring(21, 31);
    	assertThat(this.indexPage.hasText(AI_3_id + " choose to STAY"), is(true));
    	assertThat(this.indexPage.hasText(AI_3_id + " won with a score of FULL_HOUSE, ranked 1!"), is(true));

    	this.indexPage.disconnect.click();
    }

    @Test //Tests three AIs holding on a four of a kind, straight flush, and royal flush
    public void AIHoldsLastThree() {
    	this.indexPage.connect.click();
    	this.delay(1);
    	this.indexPage.open.click();
    	this.delay(1);
    	this.indexPage.rig.click();
    	
    	/************************* Set initial hands ************************/
    	// Player highcard
    	this.waitForAlert();
    	Alert a = this.webDriver.switchTo().alert();
    	a.sendKeys("rank-2 hearts, rank-3 spades, rank-4 clubs, rank-9 spades, rank-k clubs");
    	a.accept();
    	
    	//AI 1 Four of a Kind
    	this.waitForAlert();
    	a = this.webDriver.switchTo().alert();
    	a.sendKeys("rank-5 spades, rank-5 diams, rank-5 hearts, rank-5 clubs, rank-9 spades");
    	a.accept();
    	
    	//AI 2 Straight Flush
    	this.waitForAlert();
    	a = this.webDriver.switchTo().alert();
    	a.sendKeys("rank-2 spades, rank-3 spades, rank-4 spades, rank-5 spades, rank-6 spades");
    	a.accept();
    	
    	//AI 3 Royal Flush
    	this.waitForAlert();
    	a = this.webDriver.switchTo().alert();
    	a.sendKeys("rank-10 hearts, rank-j hearts, rank-q hearts, rank-k hearts, rank-a hearts");
    	a.accept();
    	
    	/****************************** check user options ************************/
    	this.delay(10);
    	this.indexPage.stay.click();
    	
    	/*****************  Check results ******************/
    	this.delay(3);
    	//AI 1 did nothing (stayed)
    	String AI_1_id = this.webDriver.findElement(By.id("otherHandText1")).getText().substring(21, 31);
    	assertThat(this.indexPage.hasText(AI_1_id + " choose to STAY"), is(true));
    	assertThat(this.indexPage.hasText(AI_1_id + " lost with a score of FOUR_OF_A_KIND, ranked 3!"), is(true));
    	
    	//AI 2 did nothing (stayed)
    	String AI_2_id = this.webDriver.findElement(By.id("otherHandText2")).getText().substring(21, 31);
    	assertThat(this.indexPage.hasText(AI_2_id + " choose to STAY"), is(true));
    	assertThat(this.indexPage.hasText(AI_2_id + " lost with a score of STRAIGHT_FLUSH, ranked 2!"), is(true));
    	
    	//AI 3 did nothing (stayed)
    	String AI_3_id = this.webDriver.findElement(By.id("otherHandText3")).getText().substring(21, 31);
    	assertThat(this.indexPage.hasText(AI_3_id + " choose to STAY"), is(true));
    	assertThat(this.indexPage.hasText(AI_3_id + " won with a score of ROYAL_FLUSH, ranked 1!"), is(true));

    	this.indexPage.disconnect.click();
    }
    
    @Test //Test AI exchanges from a high card, pair, and two pair hand
    public void AIExchanges3() {
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
    	
    	//AI 1 highcard
    	this.waitForAlert();
    	a = this.webDriver.switchTo().alert();
    	a.sendKeys("rank-2 clubs, rank-3 diams, rank-4 hearts, rank-9 clubs, rank-k spades");
    	a.accept();
    	
    	//AI 2 pair
    	this.waitForAlert();
    	a = this.webDriver.switchTo().alert();
    	a.sendKeys("rank-2 spades, rank-3 clubs, rank-4 spades, rank-6 diams, rank-6 spades");
    	a.accept();
    	
    	//AI 3 two pair
    	this.waitForAlert();
    	a = this.webDriver.switchTo().alert();
    	a.sendKeys("rank-2 diams, rank-5 hearts, rank-5 diams, rank-7 hearts, rank-7 clubs");
    	a.accept();
    	
    	/****************************** check user options ************************/
    	//AI 1 exchanges
    	this.waitForAlert();
    	a = this.webDriver.switchTo().alert();
    	a.sendKeys("0:rank-8 spades, 1:rank-4 hearts, 2:rank-4 diams, 3:rank-4 clubs, 4:rank-8 diams");
    	a.accept();
    	
    	//AI 2 exchanges
    	this.waitForAlert();
    	a = this.webDriver.switchTo().alert();
    	a.sendKeys("0:rank-3 spades, 1:rank-3 hearts, 2:rank-3 diams");
    	a.accept();
    	
    	//AI 3 exchanges
    	this.waitForAlert();
    	a = this.webDriver.switchTo().alert();
    	a.sendKeys("0:rank-5 spades");
    	a.accept();
    	
    	this.delay(3);
    	this.indexPage.stay.click();
    	
    	/*****************  Check results ******************/
    	this.delay(3);
    	//AI 1 hit and got a full house
    	String AI_1_id = this.webDriver.findElement(By.id("otherHandText1")).getText().substring(21, 31);
    	assertThat(this.indexPage.hasText(AI_1_id + " choose to HIT"), is(true));
    	assertThat(this.indexPage.hasText(AI_1_id + " lost with a score of FULL_HOUSE"), is(true));
    	
    	//AI 2 hit and got a full house
    	String AI_2_id = this.webDriver.findElement(By.id("otherHandText2")).getText().substring(21, 31);
    	assertThat(this.indexPage.hasText(AI_2_id + " choose to HIT"), is(true));
    	assertThat(this.indexPage.hasText(AI_2_id + " lost with a score of FULL_HOUSE"), is(true));
    	
    	//AI 3 hit and got a full house
    	String AI_3_id = this.webDriver.findElement(By.id("otherHandText3")).getText().substring(21, 31);
    	assertThat(this.indexPage.hasText(AI_3_id + " choose to HIT"), is(true));
    	assertThat(this.indexPage.hasText(AI_3_id + " lost with a score of FULL_HOUSE"), is(true));

    	this.indexPage.disconnect.click();
    }
    
    @Test //Test AI exchanges from a three of a kind
    public void AIExchangesOther1() {
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
    	
    	//AI 1 three of a kind
    	this.waitForAlert();
    	a = this.webDriver.switchTo().alert();
    	a.sendKeys("rank-2 spades, rank-3 clubs, rank-6 clubs, rank-6 diams, rank-6 spades");
    	a.accept();
    	
    	//AI 2 straight flush
    	this.waitForAlert();
    	a = this.webDriver.switchTo().alert();
    	a.sendKeys("rank-2 spades, rank-3 spades, rank-4 spades, rank-5 spades, rank-6 spades");
    	a.accept();
    	
    	//AI 3 four of a kind
    	this.waitForAlert();
    	a = this.webDriver.switchTo().alert();
    	a.sendKeys("rank-5 spades, rank-5 diams, rank-5 hearts, rank-5 clubs, rank-9 spades");
    	a.accept();
    	
    	/****************************** check user options ************************/
    	//AI 1 exchanges
    	this.waitForAlert();
    	a = this.webDriver.switchTo().alert();
    	a.sendKeys("0:rank-7 spades, 1:rank-7 hearts");
    	a.accept();
    	
    	this.delay(6);
    	this.indexPage.stay.click();
    	
    	/*****************  Check results ******************/
    	this.delay(3);
    	//AI 1 hit and got a full house
    	String AI_1_id = this.webDriver.findElement(By.id("otherHandText1")).getText().substring(21, 31);
    	assertThat(this.indexPage.hasText(AI_1_id + " choose to HIT"), is(true));
    	assertThat(this.indexPage.hasText(AI_1_id + " lost with a score of FULL_HOUSE"), is(true));

    	this.indexPage.disconnect.click();
    }
}
