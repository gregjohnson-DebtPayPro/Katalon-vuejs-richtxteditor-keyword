package com.kms.katalon.core.webui.keyword.builtin

import groovy.transform.CompileStatic

import java.text.MessageFormat
import java.util.concurrent.TimeUnit

import org.apache.commons.io.FileUtils
import org.openqa.selenium.Alert
import org.openqa.selenium.By
import org.openqa.selenium.Dimension
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.NoSuchElementException
import org.openqa.selenium.NoSuchWindowException
import org.openqa.selenium.TimeoutException
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebDriverException
import org.openqa.selenium.WebElement
import org.openqa.selenium.interactions.Actions
import org.openqa.selenium.support.ui.ExpectedCondition
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.FluentWait
import org.openqa.selenium.support.ui.Select
import org.openqa.selenium.support.ui.Wait
import org.openqa.selenium.support.ui.WebDriverWait

import com.google.common.base.Function
import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.annotation.internal.Action
import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.exception.StepFailedException
import com.kms.katalon.core.keyword.BuiltinKeywords
import com.kms.katalon.core.keyword.internal.KeywordExecutor
import com.kms.katalon.core.keyword.internal.SupportLevel
import com.kms.katalon.core.logging.KeywordLogger
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.testobject.TestObjectProperty
import com.kms.katalon.core.util.internal.ExceptionsUtil
import com.kms.katalon.core.util.internal.PathUtil
import com.kms.katalon.core.webui.common.ScreenUtil
import com.kms.katalon.core.webui.common.WebUiCommonHelper
import com.kms.katalon.core.webui.constants.StringConstants
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.driver.WebUIDriverType
import com.kms.katalon.core.webui.exception.BrowserNotOpenedException
import com.kms.katalon.core.webui.exception.WebElementNotFoundException
import com.kms.katalon.core.webui.keyword.internal.WebUIAbstractKeyword
import com.kms.katalon.core.webui.keyword.internal.WebUIKeywordMain
import com.kms.katalon.core.webui.util.FileUtil

@Action(value = "verifyElementVisible")
public class VerifyElementVisibleKeyword extends WebUIAbstractKeyword {

	@CompileStatic
	@Override
	public SupportLevel getSupportLevel(Object ...params) {
		return super.getSupportLevel(params)
	}

	@CompileStatic
	@Override
	public Object execute(Object ...params) {
		TestObject to = getTestObject(params[0])
		FailureHandling flowControl = (FailureHandling)(params.length > 1 && params[1] instanceof FailureHandling ? params[1] : RunConfiguration.getDefaultFailureHandling())
		return verifyElementVisible(to,flowControl)
	}

	@CompileStatic
	public boolean verifyElementVisible(TestObject to, FailureHandling flowControl) throws StepFailedException {
		return WebUIKeywordMain.runKeyword({
			boolean isSwitchIntoFrame = false
			try {
				WebUiCommonHelper.checkTestObjectParameter(to)
				isSwitchIntoFrame = WebUiCommonHelper.switchToParentFrame(to, RunConfiguration.getTimeOut())
				try {
					WebElement foundElement = WebUIAbstractKeyword.findWebElement(to, RunConfiguration.getTimeOut())
					if (foundElement.isDisplayed()) {
						logger.logPassed(MessageFormat.format(StringConstants.KW_LOG_PASSED_OBJ_X_IS_VISIBLE, to.getObjectId()))
						return true
					} else {
						WebUIKeywordMain.stepFailed(MessageFormat.format(StringConstants.KW_LOG_PASSED_OBJ_X_IS_NOT_VISIBLE, to.getObjectId()),
								flowControl, null, true)
						return false
					}
					return true
				} catch (WebElementNotFoundException e) {
					WebUIKeywordMain.stepFailed(e.getMessage(), flowControl, null, true)
					return false
				}
			} finally {
				if (isSwitchIntoFrame) {
					WebUiCommonHelper.switchToDefaultContent()
				}
			}
		}, flowControl, true, (to != null) ? MessageFormat.format(StringConstants.KW_MSG_CANNOT_VERIFY_OBJ_X_TO_BE_VISIBLE, to.getObjectId())
		: StringConstants.KW_MSG_CANNOT_VERIFY_OBJ_TO_BE_VISIBLE)
	}
}
