package com.kms.katalon.webui.keyword.vuejs

import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import org.openqa.selenium.Keys

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.exception.StepFailedException
import com.kms.katalon.core.keyword.BuiltinKeywords
import com.kms.katalon.core.keyword.internal.KeywordExecutor
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.common.WebUiCommonHelper
import com.kms.katalon.core.webui.constants.StringConstants
import com.kms.katalon.core.webui.exception.WebElementNotFoundException
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords
import com.kms.katalon.core.webui.keyword.internal.WebUIAbstractKeyword
import com.kms.katalon.core.util.KeywordUtil

import groovy.transform.CompileStatic

@CompileStatic
class RichTextEditorKeywords extends BuiltinKeywords {

	/**
	 * Get Content Text
	 *
	 * @param toContainer the tip tap web object
	 * return text of object
	 */
	@CompileStatic
	@Keyword
	static String getContentText(TestObject toContainer) {
		boolean isSwitchIntoFrame = false

		String text = ""

		try {
			WebUiCommonHelper.checkTestObjectParameter(toContainer)

			isSwitchIntoFrame = WebUiCommonHelper.switchToParentFrame(toContainer)

			WebElement eleContainer = WebUIAbstractKeyword.findWebElement(toContainer)

			text = eleContainer.getText()
		}
		finally {
			if (isSwitchIntoFrame) {
				WebUiCommonHelper.switchToDefaultContent()
			}
		}

		return text
	}

	/**
	 * Set caret position
	 *
	 * @param toContainer the tip tap web object
	 * @param position
	 */
	@CompileStatic
	@Keyword
	static void setCaretPosition(TestObject toContainer, int position) {
		boolean isSwitchIntoFrame = false

		try {
			WebUiCommonHelper.checkTestObjectParameter(toContainer)

			isSwitchIntoFrame = WebUiCommonHelper.switchToParentFrame(toContainer)

			WebElement eleContainer = WebUIAbstractKeyword.findWebElement(toContainer)

			List<WebElement> childNodes = eleContainer.findElements(By.xpath('*'))

			WebElement focusEle

			if (childNodes.size() > 0) {
				focusEle = childNodes[0]
			} else {
				focusEle = eleContainer
			}

			focusEle.click()
			eleContainer.sendKeys(Keys.HOME)
			for(int i = 0; i < position; i++) {
				eleContainer.sendKeys(Keys.ARROW_RIGHT)
			}
		}
		finally {
			if (isSwitchIntoFrame) {
				WebUiCommonHelper.switchToDefaultContent()
			}
		}
	}

	/**
	 * Select range of text
	 *
	 * @param toContainer the tip tap web object
	 * @param startPos the start position of text to search for
	 * @param charCount the length of text to search for
	 */
	@CompileStatic
	@Keyword
	static void selectRangeOfText(TestObject toContainer, int startPos, int charCount) {
		setCaretPosition(toContainer, startPos)

		boolean isSwitchIntoFrame = false

		try {
			WebUiCommonHelper.checkTestObjectParameter(toContainer)

			isSwitchIntoFrame = WebUiCommonHelper.switchToParentFrame(toContainer)

			WebElement eleContainer = WebUIAbstractKeyword.findWebElement(toContainer)

			for(int i = 0; i < charCount; i++) {
				eleContainer.sendKeys(Keys.LEFT_SHIFT, Keys.ARROW_RIGHT)
			}
		}
		finally {
			if (isSwitchIntoFrame) {
				WebUiCommonHelper.switchToDefaultContent()
			}
		}
	}

	/**
	 * Delete range of text
	 *
	 * @param toContainer the tip tap web object
	 * @param startPos the start position of text to search for
	 * @param charCount the length of text to search for
	 */
	@CompileStatic
	@Keyword
	static void deleteRangeOfText(TestObject toContainer, int startPos, int charCount) {
		selectRangeOfText(toContainer, startPos, charCount)

		boolean isSwitchIntoFrame = false

		try {
			WebUiCommonHelper.checkTestObjectParameter(toContainer)

			isSwitchIntoFrame = WebUiCommonHelper.switchToParentFrame(toContainer)

			WebElement eleContainer = WebUIAbstractKeyword.findWebElement(toContainer)

			eleContainer.sendKeys(Keys.DELETE)
		}
		finally {
			if (isSwitchIntoFrame) {
				WebUiCommonHelper.switchToDefaultContent()
			}
		}
	}

	/**
	 * List format in rich text menu bar, included of Bold, Italic, Strike, Underline, Code,
	 *  CodeBlock, Paragraph, H1, H2, H3, Ul, Ol, Quote, Hr, Undo, Redo
	 */
	enum MenuItem {
		Bold, Italic, Strike, Underline, Code, CodeBlock, Paragraph, H1, H2, H3, Ul, Ol, Quote, Hr, Undo, Redo
	}

	/**
	 * Select text format
	 *
	 * @param toMenuBar test object of rich text editor's menu bar
	 * @param format String format of text in menu bar. Can use enum MenuItem.format.toString()
	 * 			Ex: MenuItem.Bold.toString() or input "Bold"
	 * @param isSelected the format for text is selected or not depended on this boolean param
	 * @Example selectTextFormat(toMenuBar, MenuItem.Bold.toString(), true)
	 */
	@CompileStatic
	@Keyword
	static void selectTextFormat(TestObject toMenuBar, String format, boolean isActive) {
		WebElement menuBar = WebUIAbstractKeyword.findWebElement(toMenuBar)
		String xpath, tagName
		WebElement item
		List <WebElement> codeStyle
		format = format.toLowerCase()
		if(format.equalsIgnoreCase("h1") || format.equalsIgnoreCase("h2") || format.equalsIgnoreCase("h3")) {
			xpath = String.format("/descendant::button[contains(text(), '%s')]", format.toUpperCase())
			item = menuBar.findElement(By.xpath(xpath))
			tagName = item.getAttribute("class")
		} else {
			if(format.contains("code")) {
				xpath = "/descendant::div[contains(@class, 'code')]"
				codeStyle = menuBar.findElements(By.xpath(xpath))
				if(format.equalsIgnoreCase("code")) item = codeStyle.get(0)
				else item = codeStyle.get(1)
			} else {
				xpath = String.format("/descendant::div[contains(@class, '%s')]", format)
				item = menuBar.findElement(By.xpath(xpath))
			}
			tagName = item.findElement(By.xpath("//parent::button")).getAttribute("class")
		}
		if(isActive) {
			if (tagName.contains("is-active")) {
				item.click()
				item.click()
			} else item.click()
		} else {
			if (!tagName.contains("is-active")) return
				item.click()
		}
	}

	/**
	 * Select first text displays
	 *
	 * @param toContainer the tip tap web object
	 * @param text the text to search for
	 */
	static void selectFirstText(TestObject toContainer, String text) {
		String content = getContentText(toContainer)
		int index = content.indexOf(text)
		if (index >= 0)
			selectRangeOfText(toContainer, index, text.length())
		else KeywordUtil.logInfo("String " + text + " not found")
	}
}

