package mgv;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import test.LoginTest;

public class Playlist {

	private WebDriver browser;

	public Playlist(WebDriver browser) {
		this.browser = browser;
	}

	public void goto_playlist() {
		this.browser.switchTo().defaultContent();
		this.browser.switchTo().frame("iframeMain");
		this.browser.findElement(By.id("GoToPlaylistButton")).click();
		this.browser.switchTo().frame("iframePlaylist");

	}

	public void return_to_default() {
		this.browser.switchTo().defaultContent();

	}

	public void click_playlist_tree_title() {
		this.browser.findElement(By.id("playlistTreeTitle")).click();

	}

	public void add_new_folder(String name, String desc)
			throws InterruptedException {
		this.browser.findElement(By.id("CreateFolderButtonDiv")).click();
		Thread.sleep(1000);
		this.browser.findElement(By.id("FolderName")).sendKeys(name);
		Thread.sleep(1000);
		this.browser.findElement(By.id("FolderDesc")).sendKeys(desc);
		Thread.sleep(1000);
		this.browser.findElement(By.id("folderSubmit_create_folder")).click();

	}

	public void delete_folder(String name) throws InterruptedException {
		this.browser.findElement(By.cssSelector("tr[name='" + name + "']"))
				.click();
		Thread.sleep(1000);
		this.browser.findElement(By.id("DeleteButtonDiv")).click();
		Thread.sleep(1000);
		this.browser.findElement(By.id("deletePromptOk")).click();

	}

	public void add_new_playlist(String name, String desc)
			throws InterruptedException {
		this.browser.findElement(By.id("CreatePlaylistButtonDiv")).click();
		browser.manage().timeouts().pageLoadTimeout(MGV_Global.get_page_load_timeout(), 
				TimeUnit.SECONDS);
		this.browser.findElement(By.id("PlaylistCreatePropertyPlaylistName")).sendKeys(name);
		
		this.browser.findElement(By.id("PlaylistCreatePropertyPlaylistDesc")).sendKeys(desc);
		
		// selection
		new Select(this.browser.findElement(By.id("PlaylistCreatePropertyPlaylistDimensionSelect")))
				.selectByIndex(0);

		// bgm
		this.browser.findElement(By.id("PlaylistCreatePropertysavePlaylistPropertyButton")).click();

	}

	public void enter_playlist_designer(String name) {
		// find by text
//		this.browser.findElement(By.xpath("//*[contains(text(), '" + name + "');]")).click();
		this.browser.findElement(By.cssSelector("tr span.cls_name[title='" + name + "']")).click();

	}

	public void add_new_scene() throws InterruptedException {
//		this.browser.findElement(By.cssSelector("#sceneBoxDivaddSceneButton > div.thumbbox_add"))
//			.click();
		new WebDriverWait(this.browser, 10).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#SceneBoxaddSceneButton > div.thumbbox_add")))
			.click();
		Thread.sleep(1000);
		this.browser.findElement(
						By.xpath("//*[@id='sceneTemplateViewsceneTemplateTypeView']/li[1]"))
				.click();
		Thread.sleep(1000);
		this.browser.findElement(
				By.xpath("//*[@id='sceneTemplateViewsceneTemplateView']/li[1]/img"))
		.click();
		Thread.sleep(1000);
		this.browser.findElement(
				By.id("sceneTemplateViewsceneTempLateOkButton")).click();

	}

	public void add_new_layer(String type) {
		if (type == "media") {
			this.add_new_media_layer();
		} else { //
			this.add_new_scrolltext_layer();
		}
	}
	
	public void drag_media_to_layer(String name) throws InterruptedException, AWTException{
		WebElement to_drag_media = this.browser.findElement(By
				.cssSelector("#MediaTreeBox a[title='"+name+"']"));
		WebElement to_drop_div = this.browser.findElement(By
				.id("filebox"));
		Thread.sleep(1000);
		Point coordinates_from = to_drag_media.getLocation();
		Point coordinates_to = to_drop_div.getLocation();
		Robot robot = new Robot();
		int iframePlaylistHeihgt = 130;//px
//		robot.mouseMove(coordinates.getX(),coordinates.getY()+iframePlaylistHeihgt);
//		Thread.sleep(5000);
		robot.mouseMove(coordinates_from.getX()+10,coordinates_from.getY()+iframePlaylistHeihgt+10);
		robot.mousePress(InputEvent.BUTTON1_MASK);
		Thread.sleep(1000);
		robot.mouseMove(coordinates_to.getX()+10,coordinates_to.getY()+iframePlaylistHeihgt+10);
		robot.mouseRelease(InputEvent.BUTTON1_MASK);
		Thread.sleep(1000);
		this.browser.findElement(By.id("mediaPropertyOkButton")).click();
		
	}

	public void add_new_media_layer() {
		this.browser.findElement(
				By.cssSelector("#createNormalLayerButton > div")).click();

	}
	
	public void drag_scrolltext_to_layer(String name) throws InterruptedException, AWTException{
		WebElement to_drag_media = this.browser.findElement(By
				.cssSelector("#MediaTreeBox a[title='"+name+"']"));
		WebElement to_drop_div = this.browser.findElement(By
				.id("filebox"));
		Thread.sleep(1000);
		Point coordinates_from = to_drag_media.getLocation();
		Point coordinates_to = to_drop_div.getLocation();
		Robot robot = new Robot();
		int iframePlaylistHeihgt = 130;//px
//		robot.mouseMove(coordinates.getX(),coordinates.getY()+iframePlaylistHeihgt);
//		Thread.sleep(5000);
		robot.mouseMove(coordinates_from.getX()+10,coordinates_from.getY()+iframePlaylistHeihgt+10);
		robot.mousePress(InputEvent.BUTTON1_MASK);
		Thread.sleep(1000);
		robot.mouseMove(coordinates_to.getX()+10,coordinates_to.getY()+iframePlaylistHeihgt+10);
		robot.mouseRelease(InputEvent.BUTTON1_MASK);
		Thread.sleep(1000);
		this.browser.findElement(By.id("mediaPropertyOkButton")).click();	
	}

	public void add_new_scrolltext_layer() {
		this.browser.findElement(
				By.cssSelector("#createScrollTextLayerButton > div")).click();

	}

	public void add_new_media() {
		// pass

	}

	public void add_new_scrolltext() {
		// pass

	}

	public void save_playlist() {
		this.browser.findElement(By.cssSelector("#saveSceneButton > div"))
				.click();
		//wait mask to be display:none
		new WebDriverWait(this.browser, 20).until(ExpectedConditions.invisibilityOfElementLocated(By.id("DivDlgBlackMasker")));
	}

	public void return_to_playlist() {
//		this.browser.findElement(By.id("closeSceneButton")).click();
		new WebDriverWait(this.browser, 20).until(ExpectedConditions.visibilityOfElementLocated(By.id("closeSceneButton")))
		.click();
	}

	public void click_media_tree_title(String type) {
		if (type == "media") {
			this.expand_media_tree();
		} else if (type == "scrolltext") {
			this.expand_scrolltext_tree();
		} else if (type == "stream") {
			this.expand_stream_tree();
		} else { // widget
			this.expand_widget_tree();
		}
	}

	public void expand_media_tree() {
		this.browser.findElement(By.id("multimediaTitle")).click();

	}

	public void expand_scrolltext_tree() {
		this.browser.findElement(By.id("scrolltextTitle")).click();

	}

	public void expand_stream_tree() {
		this.browser.findElement(By.id("streammediaTitle")).click();

	}

	public void expand_widget_tree() {
		this.browser.findElement(By.id("widgetmediaTitle")).click();

	}

	public void switch_to_audit() {
		this.browser.findElement(By.cssSelector("#AuditModeDiv > div"))
				.click();

	}

	public void reject_all_playlists() throws InterruptedException {
		this.browser.findElement(By.id("AuditPlaylistGridViewcheckAll"))
				.click();
		Thread.sleep(1000);
		this.browser.findElement(By.id("auditRejectedDiv")).click();

	}
	
	public void approve_all_playlists() throws InterruptedException{
		this.browser.findElement(By.id("AuditPlaylistGridViewcheckAll"))
				.click();
		Thread.sleep(1000);
		this.browser.findElement(By.id("auditApprovedDiv")).click();

	}

	public void delete_all_rejected_playlists() throws InterruptedException {
		this.browser.findElement(By.id("AuditPlaylistGridViewcheckAll"))
				.click();
		Thread.sleep(1000);
		this.browser.findElement(By.id("DeleteAuditButtonDiv")).click();
		Thread.sleep(1000);
		this.browser.findElement(By.id("deletePromptOk")).click();
		// delete
		// cut
		// saveas

	}
	
	public void search_playlist(String name) throws InterruptedException{
		this.browser.findElement(By.id("searchModeDiv")).click();
		Thread.sleep(1000);
		this.browser.findElement(By.id("searchName")).sendKeys(name);
		Thread.sleep(1000);
		this.browser.findElement(By.id("searchButtonOK")).click();
		
	}
	
	public void switch_to_normal() throws InterruptedException{
		this.browser.findElement(By.cssSelector("PlaylistModeButtonDiv"))
		.click();
	}

	

}
