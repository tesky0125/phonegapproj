package mgv;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Media {

	private WebDriver browser;
	
	public Media(WebDriver browser) {
		this.browser = browser;
	}
	
	public void goto_media(){
        this.browser.switchTo().defaultContent();
        this.browser.switchTo().frame("iframeMain");
        this.browser.findElement(By.id("GoToMediaButton")).click();
        this.browser.switchTo().frame("iframeMedia");
	}
	
    public void add_new_folder(String name, String desc){
    	
        this.browser.findElement(By.id("newFolderDiv")).click();
        
        browser.manage().timeouts().pageLoadTimeout(MGV_Global.get_page_load_timeout(), 
				TimeUnit.SECONDS);
        
        this.browser.findElement(By.id("FolderName")).sendKeys(name);
        this.browser.findElement(By.id("FolderDesc")).sendKeys(desc);
        this.browser.findElement(By.id("folderSubmit_create_folder")).click();
    }
    

    
    public void add_new_scrolltext(String name, String desc, String content){
        
    	this.browser.findElement(By.id("newMediaDiv")).click();
    	
    	browser.manage().timeouts().pageLoadTimeout(MGV_Global.get_page_load_timeout(), 
				TimeUnit.SECONDS);
    	
        this.browser.findElement(By.id("scrollTextName")).sendKeys(name);
        this.browser.findElement(By.id("scrollTextDes")).sendKeys(desc);
        this.browser.findElement(By.id("scrollTextContent")).sendKeys(content);

        this.browser.findElement(By.id("scrollTextSubmit")).click();
    }
	
    public String upload_media_file(String file_path){
    	
    	File src_file = new File(file_path);
        
    	String upload_file_name = MGV_Utility.get_random_name("upload_file_") + ".jpg";
        
        String des_file_path = src_file.getPath().substring(0, src_file.getPath().length() - src_file.getName().length()) +
        				upload_file_name;
        
        try {
			FileHandler.copy(src_file, new File(des_file_path));
		} catch (IOException e) {
			des_file_path = file_path;
		}
        
        this.browser.findElement(By.id("newMediaDiv")).click();
        
        browser.manage().timeouts().pageLoadTimeout(MGV_Global.get_page_load_timeout(), 
				TimeUnit.SECONDS);

        input_file_to_dialog(des_file_path);   
    	
		return des_file_path;
    }
	
	private void input_file_to_dialog(String des_file_path) {
        Robot rb;
		try {
			rb = new Robot();
			
			Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
			clipboard.setContents(new StringSelection(des_file_path), null);
			rb.delay(3000);
			rb.keyPress(KeyEvent.VK_CONTROL);
			rb.keyPress(KeyEvent.VK_V);
			rb.keyRelease(KeyEvent.VK_V);
			rb.keyRelease(KeyEvent.VK_CONTROL);
			rb.delay(200);
			rb.keyPress(KeyEvent.VK_ENTER);
			rb.keyRelease(KeyEvent.VK_ENTER);	
			
		} catch (AWTException e) {
		}

	}

	public void switch_to_audit(){
		browser.manage().timeouts().pageLoadTimeout(MGV_Global.get_page_load_timeout(), 
				TimeUnit.SECONDS);
		
        this.browser.findElement(By.id("auditButtonDiv")).click();
    }
    
    public void click_media_tree_title(String type){
        if (type == "media"){
            this.expand_media_tree();
        }else if (type == "scrolltext"){
            this.expand_scrolltext_tree();
        }else if (type == "stream"){
            this.expand_stream_tree();
        }else {  // widget
            this.expand_widget_tree();
        }
    }
	
    public void expand_media_tree(){
    	
    	this.browser.findElement(By.id("multimediaTitle")).click();  
    }      
    
    public void expand_scrolltext_tree(){
    	this.browser.findElement(By.id("scrolltextTitle")).click(); 
    }     
    
    public void expand_stream_tree(){
        this.browser.findElement(By.id("streammediaTitle")).click();
    }
    public void expand_widget_tree(){
        this.browser.findElement(By.id("widgetmediaTitle")).click();
    }  
    
    public void return_to_default(){
        this.browser.switchTo().defaultContent();
    }

	public void close_popup(String popupName) {
		
		try
		{
			WebElement element = new WebDriverWait(this.browser, MGV_Global.get_wait_max_timeout()).until(ExpectedConditions.visibilityOfElementLocated(By.id(popupName)));
	        
	    	if (element.isDisplayed()){
	        	this.browser.findElement(By.id(popupName)).click();
	        }
		}
		catch(Exception e){
			
		}
	}
	
	
	
	public void close_upload_dialog(){
        this.browser.findElement(By.id("close_upload_status")).click();
    }
	
	public void add_new_stream(String type, String name, String desc, String url) throws InterruptedException{
        if (type == "http"){
            this.add_new_stream_http(name, desc, url);
        }else{  // rstp
            this.add_new_stream_rstp(name, desc, url);
        }
    }
	
    public void add_new_stream_http(String name, String desc, String url) throws InterruptedException{
        this.browser.findElement(By.id("newMediaDiv")).click();
        Thread.sleep(1000);
        this.browser.findElement(By.id("streamName")).sendKeys(name);
        Thread.sleep(1000);
        this.browser.findElement(By.id("streamDes")).sendKeys(desc);
        Thread.sleep(1000);
//        # radio
        this.browser.findElement(By.id("typeOfWeb")).click();
        Thread.sleep(1000);
        this.browser.findElement(By.id("streamUrl")).sendKeys(url);
        Thread.sleep(1000);
        this.browser.findElement(By.id("streamSubmit")).click();
    }
    
    public void add_new_stream_rstp(String name, String desc, String url) throws InterruptedException{
        this.browser.findElement(By.id("newMediaDiv")).click();
        Thread.sleep(1000);
        this.browser.findElement(By.id("streamName")).sendKeys(name);
        Thread.sleep(1000);
        this.browser.findElement(By.id("streamDes")).sendKeys(desc);
        Thread.sleep(1000);
//        # radio
        this.browser.findElement(By.id("typeOfStream")).click();
        Thread.sleep(1000);
        this.browser.findElement(By.id("streamUrl")).sendKeys(url);
        Thread.sleep(1000);
        this.browser.findElement(By.id("streamSubmit")).click();
    }
    
    public void add_new_widget(String type, String name) throws InterruptedException{
        if (type == "clock"){
            this.add_new_widget_clock(name);
        }else if (type == "weather"){
            this.add_new_widget_weather(name);
        }else{  // rss
            this.add_new_widget_rss(name);
        }
    }
    
    public void add_new_widget_rss(String name){
//      pass
  }
    
    public void add_new_widget_clock(String name) throws InterruptedException{
        this.browser.findElement(By.id("newMediaDiv")).click();
        Thread.sleep(1000);
        this.browser.findElement(By.cssSelector("#timeTemplate > img")).click();
        Thread.sleep(1000);
        this.browser.findElement(By.id("submit_select_widget")).click();
        Thread.sleep(1000);
        this.browser.findElement(By.id("clockDesignerIdtimeName")).sendKeys(name);
        Thread.sleep(1000);
//        # select and radio
        new Select(this.browser.findElement(By.id("clockDesignerIdselectLanguage"))).selectByIndex(1) ;
        Thread.sleep(1000);
        new Select(this.browser.findElement(By.id("clockDesignerIdclockTypeSelection"))).selectByIndex(0);
        Thread.sleep(1000);
        new Select(this.browser.findElement(By.id("clockDesignerIdtimeTypeSelection"))).selectByIndex(1);
        Thread.sleep(1000);
        this.browser.findElement(By.id("clockDesignerIdclockTransCheckBox")).click();
        Thread.sleep(1000);
        this.browser.findElement(By.id("clockDesignerIddateTimeCheckBox")).click();
        Thread.sleep(1000);
//        # save
        this.browser.findElement(By.id("clockDesignerIdsaveTimeButton")).click();
        Thread.sleep(1000);
        this.browser.findElement(By.id("clockDesignerIdcloseTimeButton")).click();
    }
    
    public void add_new_widget_weather(String name) throws InterruptedException{
        this.browser.findElement(By.id("newMediaDiv")).click();
        Thread.sleep(1000);
        this.browser.findElement(By.cssSelector("#weatherTemplate > img")).click();
        Thread.sleep(1000);
        this.browser.findElement(By.id("submit_select_widget")).click();
        Thread.sleep(1000);
        this.browser.findElement(By.id("weatherDesignerIdweatherName")).sendKeys(name);
        Thread.sleep(1000);
//        # select and radio
        new Select(this.browser.findElement(By.id("weatherDesignerIdweatherLanguageSelection"))).selectByIndex(1);
        Thread.sleep(1000);
        new Select(this.browser.findElement(By.id("weatherDesignerIdweatherTempSelection"))).selectByIndex(0);
        Thread.sleep(1000);
        this.browser.findElement(By.id("weatherDesignerIdweatherTransCheckBox")).click();
        Thread.sleep(1000);
        this.browser.findElement(By.id("weatherDesignerIdweatherProxyCheckBox")).click();
        Thread.sleep(1000);
        this.browser.findElement(By.id("weatherDesignerIdweatherAddress")).sendKeys("www.xxxx.com");
        Thread.sleep(1000);
        this.browser.findElement(By.id("weatherDesignerIdweatherPort")).sendKeys("8080");
        Thread.sleep(1000);
//        # select city
        this.browser.findElement(By.id("weatherDesignerIdotherCityButtonInWeather")).click();
        Thread.sleep(1000);
        this.browser.findElement(By.id("citySelectioncityTextInput")).sendKeys("shanghai");
        Thread.sleep(1000);
        this.browser.findElement(By.id("citySelectioncityInfoSearchButton")).click();
        Thread.sleep(1000);
//        # wait city info
//#         this.browser.implicitly_wait(10)
//#         sel_city_info = this.browser.findElement(By.id("#citySelectioncityInfo_0"))
//#         WaitForElement
        WebElement sel_city_info = new WebDriverWait(this.browser, 20).until(ExpectedConditions.visibilityOfElementLocated(By.id("citySelectioncityInfo_0")));
        if (sel_city_info.isDisplayed()){
            this.browser.findElement(By.id("citySelectioncityInfo_0")).click();
            Thread.sleep(1000);
            this.browser.findElement(By.id("citySelectionok_city_code")).click();
            Thread.sleep(1000);
//            # save
            this.browser.findElement(By.id("weatherDesignerIdsaveWeatherButton")).click();
            Thread.sleep(1000);
            this.browser.findElement(By.id("weatherDesignerIdcloseWeatherButton")).click();
        }
    }
    
    public void approve_all_medias() throws InterruptedException{
        this.browser.findElement(By.id("GridListTable_auditcheckAll")).click();
        Thread.sleep(1000);
        this.browser.findElement(By.id("auditApprovedDiv")).click();
    }
    
    public void delete_folder(String name) throws InterruptedException{
	//      this.browser.find_element_by_xpath("//*[contains(text(), "rylan-test")]").click()
	     this.browser.findElement(By.cssSelector("tr[name='" + name + "']")).click();
	     Thread.sleep(1000);
	     this.browser.findElement(By.id("DeleteButtonDiv")).click();
	     Thread.sleep(1000);
	     this.browser.findElement(By.id("delete_submit")).click();
    } 
}
