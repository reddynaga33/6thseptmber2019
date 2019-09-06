package framework;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;

public class ElementHighlighter extends AbstractWebDriverEventListener{

	private long interval;
	private final int count;
	private final String color;
	
	/**
	 * This method will help for highlighting the web-element and no.of
	 * splashes.
	 * 
	 * @param color
	 *            Highlighted Color
	 * @param count
	 *            No.of Flashes for the highlighted
	 * @param interval
	 *            Time
	 * @param unit
	 *            MilliSeconds units
	 */
	public ElementHighlighter(String color, int count, long interval, TimeUnit unit) {
		this.color = color;
		this.count = count;
		this.interval = TimeUnit.MILLISECONDS.convert(Math.max(0, interval), unit);
		
	}

	@Override
	public void beforeClickOn(WebElement element, WebDriver driver) {
		flash(element, driver);
	}

	/**
	 * This method have the color high-lighter count information
	 * 
	 * @param element
	 *            WebElement
	 * @param driver
	 *            WebDriver
	 */
	private void flash(WebElement element, WebDriver driver) {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		String bgcolor = element.getCssValue("backgroundColor");
		for (int i = 0; i < count; i++) {
			changeColor(color, element, js);
			changeColor(bgcolor, element, js);
		}
	}

	/**
	 * This method will change the web-element color(highlighted)
	 * 
	 * @param color
	 *            Highlighted Color
	 * @param element
	 *            WebElement
	 * @param js
	 *            JavascriptExecutor reference
	 */
	private void changeColor(String color, WebElement element, JavascriptExecutor js) {
		js.executeScript("arguments[0].style.backgroundColor = '" + color + "'", element);
		try {
			Thread.sleep(interval);
		} catch (InterruptedException e) {
			TestLogger
				.appInfo("Exception thrown at ElementHighlighter class - changeColor() method:" + e.getMessage());
			e.printStackTrace();
		}
	}
}
