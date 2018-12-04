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
public class RankingTests extends AbstractSeleniumTest {

    @Autowired
    private IndexPage indexPage;
    
    @Test
    public void Rig1(){
    	/* This hand is rigged with :
    	 *  - O1: Royal Flush
    	 *  - O2: two pair
    	 *  - 03: straight
    	 *  - p: HighCard
    	 */
    	
    	this.indexPage.connect.click();
    	this.indexPage.numberPlayers.clear();
    	this.indexPage.numberPlayers.sendKeys("4");
    	this.indexPage.open.click();
    	ChromeDriver user1 = this.quickConnectAnotherUser();
    	ChromeDriver user2 = this.quickConnectAnotherUser();
    	ChromeDriver user3 = this.quickConnectAnotherUser();
    	this.indexPage.rig.click();
    	
    	/************************* Set initial hands ************************/
    	// Player highcard
    	this.waitForAlert();
    	Alert a = this.webDriver.switchTo().alert();
    	a.sendKeys("rank-2 hearts, rank-3 spades, rank-4 clubs, rank-9 spades, rank-k clubs");
    	a.accept();
    	
    	// other 1 Royal Flush
    	this.waitForAlert();
    	a = this.webDriver.switchTo().alert();
    	a.sendKeys("rank-10 hearts, rank-j hearts, rank-q hearts, rank-k hearts, rank-a hearts");
    	a.accept();
    	
    	// other 2 Two Pair
    	this.waitForAlert();
    	a = this.webDriver.switchTo().alert();
    	a.sendKeys("rank-2 clubs, rank-6 hearts, rank-6 clubs, rank-q spades, rank-q diams");
    	a.accept();
    	
    	// other 3 Straight
    	this.waitForAlert();
    	a = this.webDriver.switchTo().alert();
    	a.sendKeys("rank-2 diams, rank-3 spades, rank-4 diams, rank-5 clubs, rank-6 diams");
    	a.accept();
     
    	assertThat(this.indexPage.hasText("Setting all cards"), is(true));
    	assertThat(this.indexPage.hasText("Starting rigged game"), is(true));
    	/****************************** check user options ************************/
    	//16: user 3 chooses to Stay
    	this.delay(3);
    	user3.findElement(By.id("stay")).click();
    	
    	//15: user 2 chooses to hit
    	this.delay(3);
    	user2.findElement(By.id("hit")).click();
    	user2.findElement(By.id("PlayerCard1")).click();
    	user2.findElement(By.id("done")).click();
    	
    	//13: Handle prompt to improve user 2 with "0:rank-a clubs"
    	this.delay(1);
    	a = user2.switchTo().alert();
    	a.sendKeys("0:rank-a clubs");
    	a.accept();
    	
    	//14: user 1 chooses to stay
    	this.delay(3);
    	user1.findElement(By.id("stay")).click(); 
    	    	
    	//17: Player hits and gets “0:rank-q diams, 1:rank-j clubs, 2:rank-4 spades, 3:rank-8 clubs, 4:rank-2 clubs"
    	this.delay(3);
    	this.indexPage.hit.click();
    	this.indexPage.card1.click();
    	this.indexPage.card2.click();
    	this.indexPage.card3.click();
    	this.indexPage.card4.click();
    	this.indexPage.card5.click();
    	this.indexPage.done.click();
    	this.waitForAlert();
    	a = this.webDriver.switchTo().alert();
    	a.sendKeys("0:rank-q diams, 1:rank-j clubs, 2:rank-4 spades, 3:rank-8 clubs, 4:rank-2 clubs");
    	a.accept();
    	 
    	/*****************  Check results ******************/
    	this.delay(3);
    	//18: user 1 wins with a royal flush: ranked 1
    	String user_1_id = user1.findElement(By.id("consoleText")).getText().substring(14, 22);
    	assertThat(this.indexPage.hasText(user_1_id + " won with a score of ROYAL_FLUSH, ranked 1!"), is(true));
    	
    	//19: user 2 loses with two pairs, ranked 3
    	String user_2_id = user2.findElement(By.id("consoleText")).getText().substring(14, 22);
    	assertThat(this.indexPage.hasText(user_2_id + " lost with a score of TWO_PAIR, ranked 3!"), is(true));
    	
    	//20: user 3 loses with straight, ranked 2
    	String user_3_id = user3.findElement(By.id("consoleText")).getText().substring(14, 22);
    	assertThat(this.indexPage.hasText(user_3_id + " lost with a score of STRAIGHT, ranked 2!"), is(true));
    	    	
    	//21: Player loses with high card, ranked 4
    	String player_id = this.indexPage.getPlayerUID();
    	assertThat(this.indexPage.hasText(player_id + " lost with a score of HIGH_CARD, ranked 4!"), is(true));
    	
    	this.disconnectSecondUser(user1);
    	this.disconnectSecondUser(user2);
    	this.disconnectSecondUser(user3);
    	this.indexPage.disconnect.click();
    }

    
    @Test
    public void Rig2(){
    	/*This had is rigged with :
       	 * 	- O1: Four of a Kind
       	 *  - O2: Full House
       	 *  - 03: One pair
       	 *  - p:  flush
       	 */
    	
    	this.indexPage.connect.click();
    	this.indexPage.numberPlayers.clear();
    	this.indexPage.numberPlayers.sendKeys("4");
    	this.indexPage.open.click();
    	ChromeDriver user1 = this.quickConnectAnotherUser();
    	ChromeDriver user2 = this.quickConnectAnotherUser();
    	ChromeDriver user3 = this.quickConnectAnotherUser();
    	this.indexPage.rig.click();
    	
    	/************************* Set initial hands ************************/
    	// Player flush 
    	this.waitForAlert();
    	Alert a = this.webDriver.switchTo().alert();
    	a.sendKeys("rank-2 hearts, rank-4 hearts, rank-7 hearts, rank-9 hearts, rank-k hearts");
    	a.accept();
    	
    	// other 1 Four of a kind
    	this.waitForAlert();
    	a = this.webDriver.switchTo().alert();
    	a.sendKeys("rank-10 hearts, rank-10 clubs, rank-10 diams, rank-10 spades, rank-a hearts");
    	a.accept();
    	
    	// other 2 one full house
    	this.waitForAlert();
    	a = this.webDriver.switchTo().alert();
    	a.sendKeys("rank-j clubs, rank-j spades, rank-3 clubs, rank-3 spades, rank-3 diams");
    	a.accept();
    	
    	// other 3 one Pair
    	this.waitForAlert();
    	a = this.webDriver.switchTo().alert();
    	a.sendKeys("rank-2 clubs, rank-2 spades, rank-6 clubs, rank-q spades, rank-q diams");
    	a.accept();
     
    	assertThat(this.indexPage.hasText("Setting all cards"), is(true));
    	assertThat(this.indexPage.hasText("Starting rigged game"), is(true));
    	/****************************** check user options ************************/
    	//22: Handle prompt to improve user 3 with "2:rank-a clubs,3:rank-q spades,4:rank-5 spades"
    	this.delay(3);
    	user3.findElement(By.id("hit")).click();
    	user3.findElement(By.id("PlayerCard2")).click();
    	user3.findElement(By.id("PlayerCard3")).click();
    	user3.findElement(By.id("PlayerCard4")).click();
    	user3.findElement(By.id("done")).click();
    	
    	this.delay(1);
    	a = user3.switchTo().alert();
    	a.sendKeys("2:rank-a clubs,3:rank-q spades,4:rank-5 spades");
    	a.accept();
    	
    	//23: user 1 stays, user 2 stays, player stays 
    	this.delay(3);
    	user2.findElement(By.id("stay")).click();
    	
    	this.delay(3);
    	user1.findElement(By.id("stay")).click();
    	
    	this.delay(3);
    	this.indexPage.stay.click();
    	
    	/*****************  Check results ******************/
        //24: user 1 wins, 3 others lose
    	this.delay(3);
    	String user_1_id = user1.findElement(By.id("consoleText")).getText().substring(14, 22);
    	assertThat(this.indexPage.hasText(user_1_id + " won"), is(true));
    	
    	String user_2_id = user2.findElement(By.id("consoleText")).getText().substring(14, 22);
    	assertThat(this.indexPage.hasText(user_2_id + " lost"), is(true));
    	
    	String user_3_id = user3.findElement(By.id("consoleText")).getText().substring(14, 22);
    	assertThat(this.indexPage.hasText(user_3_id + " lost"), is(true));
    	    	
    	String player_id = this.indexPage.getPlayerUID();
    	assertThat(this.indexPage.hasText(player_id + " lost"), is(true));
    	
    	this.disconnectSecondUser(user1);
    	this.disconnectSecondUser(user2);
    	this.disconnectSecondUser(user3);
    	this.indexPage.disconnect.click();
    }
    
    @Test
    public void Rig3(){
    	/* This had is rigged with :
    	 *  - O1: One pair
    	 *  - O2: 3 of a kind
    	 *  - 03: straight flush
    	 *  - p:  high card
       	 */
    	
    	this.indexPage.connect.click();
    	this.indexPage.numberPlayers.clear();
    	this.indexPage.numberPlayers.sendKeys("4");
    	this.indexPage.open.click();
    	ChromeDriver user1 = this.quickConnectAnotherUser();
    	ChromeDriver user2 = this.quickConnectAnotherUser();
    	ChromeDriver user3 = this.quickConnectAnotherUser();
    	this.indexPage.rig.click();
    	
    	/************************* Set initial hands ************************/
    	// Player High Card 
    	this.waitForAlert();
    	Alert a = this.webDriver.switchTo().alert();
    	a.sendKeys("rank-2 hearts, rank-4 diams, rank-7 spades, rank-9 diams, rank-k hearts");
    	a.accept();
    	
    	// other 1 one pair
    	this.waitForAlert();
    	a = this.webDriver.switchTo().alert();
    	a.sendKeys("rank-10 hearts, rank-10 clubs, rank-6 diams, rank-2 spades, rank-a hearts");
    	a.accept();
    	
    	// other 2, 3 of a kind
    	this.waitForAlert();
    	a = this.webDriver.switchTo().alert();
    	a.sendKeys("rank-j clubs, rank-j spades, rank-j spades, rank-3 diams, rank-8 spades");
    	a.accept();
    	
    	// other 3 straight flush
    	this.waitForAlert();
    	a = this.webDriver.switchTo().alert();
    	a.sendKeys("rank-2 clubs, rank-3 clubs, rank-4 clubs, rank-5 clubs, rank-6 clubs");
    	a.accept();

    	assertThat(this.indexPage.hasText("Setting all cards"), is(true));
    	assertThat(this.indexPage.hasText("Starting rigged game"), is(true));
    	/****************************** check user options ************************/
    	//27: user 3 stays
    	this.delay(3);
    	user3.findElement(By.id("stay")).click();
    	
    	//26: Handle prompt to improve user 2 with ”0:rank-4 spades,1:rank-a clubs"
    	this.delay(3);
    	user2.findElement(By.id("hit")).click();
    	user2.findElement(By.id("PlayerCard1")).click();
    	user2.findElement(By.id("PlayerCard2")).click();
    	user2.findElement(By.id("done")).click();
    	
    	this.delay(1);
    	a = user2.switchTo().alert();
    	a.sendKeys("0:rank-4 spades,1:rank-a clubs");
    	a.accept();
    	
    	//25: Handle prompt to improve user 1 with “0:rank-7 clubs,1:rank-j clubs,4:rank-5 diams"
    	this.delay(3);
    	user1.findElement(By.id("hit")).click();
    	user1.findElement(By.id("PlayerCard1")).click();
    	user1.findElement(By.id("PlayerCard2")).click();
    	user1.findElement(By.id("PlayerCard4")).click();
    	user1.findElement(By.id("done")).click();
    	
    	this.delay(1);
    	a = user1.switchTo().alert();
    	a.sendKeys("0:rank-7 clubs,1:rank-j clubs,4:rank-5 diams");
    	a.accept();
    	    	
    	//28: Player gets “0:rank-4 spades, 1:rank-7 clubs, 2:rank-a clubs,3:rank-q spades,4:rank-5 spades"
    	this.delay(3);
    	this.indexPage.hit.click();
    	this.indexPage.card1.click();
    	this.indexPage.card2.click();
    	this.indexPage.card3.click();
    	this.indexPage.card4.click();
    	this.indexPage.done.click();
    	this.waitForAlert();
    	a = this.webDriver.switchTo().alert();
    	a.sendKeys("0:rank-4 spades, 1:rank-7 clubs, 2:rank-a clubs,3:rank-q spades,4:rank-5 spades");
    	a.accept();

    	/*****************  Check results ******************/
    	//29: missing code for the outcome
    	this.delay(3);
    	String user_1_id = user1.findElement(By.id("consoleText")).getText().substring(14, 22);
    	assertThat(this.indexPage.hasText(user_1_id + " lost"), is(true));
    	
    	String user_2_id = user2.findElement(By.id("consoleText")).getText().substring(14, 22);
    	assertThat(this.indexPage.hasText(user_2_id + " lost"), is(true));
    	
    	String user_3_id = user3.findElement(By.id("consoleText")).getText().substring(14, 22);
    	assertThat(this.indexPage.hasText(user_3_id + " won"), is(true));
    	    	
    	String player_id = this.indexPage.getPlayerUID();
    	assertThat(this.indexPage.hasText(player_id + " lost"), is(true));
    	
    	this.disconnectSecondUser(user1);
    	this.disconnectSecondUser(user2);
    	this.disconnectSecondUser(user3);
    	this.indexPage.disconnect.click();
    }
}
