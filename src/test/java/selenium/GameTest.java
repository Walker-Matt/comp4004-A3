package selenium;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.beans.factory.annotation.Autowired;

import config.SeleniumTest;
import selenium.page.IndexPage;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import java.util.concurrent.TimeUnit;

/**
 * 
 */
@SeleniumTest
public class GameTest extends AbstractSeleniumTest {

    @Autowired
    private IndexPage indexPage;
    
 

    @Test
    public void processAI(){
    	//connect
    	//9: missing code  including check all AI players have made their choices   
 
                  
    }
 
    

 
	@Test
    public void canUseStayOption(){
	//10: missing code for connect then selecting stay then disconnect
    }


    @Test
    public void canUseHitOption() {
    	//11: missing code similar to 11    
    }
    
    
 
    @Test
    public void canImproveCards(){
    	//12: missing code for connect, improve and disconnect     
    }
    
    
   
  
    
    
    
    
}
