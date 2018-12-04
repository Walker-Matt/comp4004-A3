package selenium;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;

import config.SeleniumTest;
import selenium.page.IndexPage;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.concurrent.TimeUnit;

/**
 * Test the ability to connect and disconnect to the game.
 * 
 */
@SeleniumTest
public class ConnectionBasicsTest extends AbstractSeleniumTest{

    @Autowired
    private IndexPage indexPage;
    
    @Test
    public void canConnect() {
    	this.indexPage.connect.click();
        assertThat(this.indexPage.hasText("Successfully connected to the game with unique "), is(true));
        assertThat(this.indexPage.hasText("You have been designated the admin for this game."), is(true));
    
        this.indexPage.disconnect.click();
        assertThat(this.indexPage.hasText("Connection closed"), is(true));
    }
    
    @Test
    public void canOpenLobby() {
    	this.indexPage.connect.click();
    	
    	this.indexPage.open.click();
    	//1: missing assertThat
    	assertThat(this.indexPage.hasText("Opening the lobby with specified settings."), is(true));
    	
    	this.indexPage.disconnect.click(); 
    }
 
    @Test
    public void canConnectTwoPlayers() {
    	//2: connect
    	this.indexPage.connect.click();
    	this.indexPage.numberPlayers.clear();
    	this.indexPage.numberPlayers.sendKeys("2");
    	this.indexPage.open.click();
    	assertThat(this.indexPage.hasText("When the correct number of players have connected, "), is(true));
    	
    	//3: second connect
    	ChromeDriver second = this.quickConnectAnotherUser();
    	assertThat(this.indexPage.hasText(" has connected to the game."), is(true));

    	//4: quit   	
    	this.disconnectSecondUser(second);
    	this.indexPage.disconnect.click();
    }
    
    @Test
    public void canConnectMultiplePlayers() {
    	//5: connect one
    	this.indexPage.connect.click();
    	this.indexPage.numberPlayers.clear();
    	this.indexPage.numberPlayers.sendKeys("3");
    	this.indexPage.open.click();
    	assertThat(this.indexPage.hasText("When the correct number of players have connected, "), is(true));
    	
    	//6: connect second
    	ChromeDriver second = this.quickConnectAnotherUser();
    	assertThat(this.indexPage.hasText(" has connected to the game."), is(true));
    	
    	//7: connect third then disconnect
    	ChromeDriver third = this.quickConnectAnotherUser();
    	assertThat(this.indexPage.hasText(" has connected to the game."), is(true));
    	this.disconnectSecondUser(second);
    	this.disconnectSecondUser(third);
    	this.indexPage.disconnect.click();
 }
    
    @Test
    public void canStartGame() {
    	this.indexPage.connect.click();
    	this.indexPage.open.click();
    	this.indexPage.start.click();
    	//8: missing assertThat
    	assertThat(this.indexPage.hasText("The game has started! "), is(true));
    	this.indexPage.disconnect.click();
    }
}
