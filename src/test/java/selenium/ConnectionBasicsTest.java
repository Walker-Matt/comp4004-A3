package selenium;
import org.junit.Test;
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
    	 this.indexPage.disconnect.click(); 
    }
 
    @Test
    public void canConenctTwoPlayers() {
    	// connect
     	//2: missing code
    	
    	//second connect
    	//3: missing code

    	// quit
    	//4: missing code     	
    	
    	
    }
    
    @Test
    public void canConenctMultiplePlayers() {
    	// connect 
    	//5: missing code          	
    	// connect second
    	//6: missing code         
    	// connect third then disconnect
    	//7: missing code        
 }
    

    @Test
    public void canStartGame() {
    	this.indexPage.connect.click();
    	this.indexPage.open.click();
    	this.indexPage.start.click();
    	//8: missing assertThat
    	this.indexPage.disconnect.click();
    }
    
   
    
}
