package com.kms.katalon.webui.keyword.vuejs

import org.openqa.selenium.By
import org.openqa.selenium.WebElement

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
import com.kms.katalon.core.webui.keyword.internal.WebUIAbstractKeyword

import groovy.transform.CompileStatic

@CompileStatic
class ModalKeywords extends BuiltinKeywords {

	/**
	 * Get Modal Title Text
	 * 
	 * @param toModalContainer Test Object of modal
	 * @return modal title
	 */
	@CompileStatic
	@Keyword
	static String getModalTitleText(TestObject toModalContainer) {
		boolean isSwitchIntoFrame = false
		String text = ""
		try {
			WebUiCommonHelper.checkTestObjectParameter(toModalContainer)
			isSwitchIntoFrame = WebUiCommonHelper.switchToParentFrame(toModalContainer)
			WebElement eleContainer = WebUIAbstractKeyword.findWebElement(toModalContainer)
			WebElement eleHeader = eleContainer.findElement(By.xpath('div[contains(@class,"header")]'))
			text = eleHeader.getText()
		}
		finally {
			if (isSwitchIntoFrame) {
				WebUiCommonHelper.switchToDefaultContent()
			}
		}
		return text
	}

	/**
	 * Get Modal Content Text
	 * 
	 * @param toModalContainer Test Object of modal
	 * @return Content text of modal
	 */
	@CompileStatic
	@Keyword
	static String getModalContentText(TestObject toModalContainer) {
		boolean isSwitchIntoFrame = false
		String text = ""
		try {
			WebUiCommonHelper.checkTestObjectParameter(toModalContainer)
			isSwitchIntoFrame = WebUiCommonHelper.switchToParentFrame(toModalContainer)
			WebElement eleContainer = WebUIAbstractKeyword.findWebElement(toModalContainer)
			WebElement eleHeader = eleContainer.findElement(By.xpath('div[contains(@class,"body")]'))
			text = eleHeader.getText()
		}
		finally {
			if (isSwitchIntoFrame) {
				WebUiCommonHelper.switchToDefaultContent()
			}
		}
		return text
	}

	/**
	 * Click Confirm Button by Text
	 * 
	 * @param toModalContainer Test Object of modal
	 * @param buttonText Confirmed button text
	 */
	@CompileStatic
	@Keyword
	static void clickConfirmButtonByText(TestObject toModalContainer, String buttonText = "OK") {
		boolean isSwitchIntoFrame = false
		try {
			WebUiCommonHelper.checkTestObjectParameter(toModalContainer)
			isSwitchIntoFrame = WebUiCommonHelper.switchToParentFrame(toModalContainer)
			WebElement eleContainer = WebUIAbstractKeyword.findWebElement(toModalContainer)
			WebElement eleBtnConfirm = eleContainer.findElement(
					By.xpath('div[contains(@class,"footer")]/button[contains(text(),"' + buttonText + '")]'))
			eleBtnConfirm.click()
		}
		finally {
			if (isSwitchIntoFrame) {
				WebUiCommonHelper.switchToDefaultContent()
			}
		}
	}
}