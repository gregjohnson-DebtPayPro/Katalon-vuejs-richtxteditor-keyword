import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.testobject.ConditionType as ConditionType
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.webui.keyword.internal.WebUIAbstractKeyword as WebUIAbstractKeyword
import internal.GlobalVariable as GlobalVariable
import java.lang.String as String

import com.kms.katalon.webui.keyword.vuejs.ModalKeywords

WebUI.openBrowser('https://mazipan.github.io/vue2-simplert/')

// try to open the modal dialog
TestObject toInformationAlertOpenButton = new TestObject()
toInformationAlertOpenButton.addProperty('tag', ConditionType.EQUALS, 'button')
toInformationAlertOpenButton.addProperty('xpath', ConditionType.EQUALS,
	'//div[text()="Information Alert"]/following-sibling::button[contains(text(),"Click Me!")]')

WebUI.waitForElementPresent(toInformationAlertOpenButton, 5)

WebUI.click(toInformationAlertOpenButton)

// Modal container Test Object
TestObject to = new TestObject()
to.addProperty('tag', ConditionType.EQUALS, 'div')
to.addProperty('xpath', ConditionType.EQUALS, '//div[@role="modal"]/div[contains(@class,"content")]')

// Verify header text
assert ModalKeywords.getModalTitleText(to).contains('Information!')

// Verify body content text
assert ModalKeywords.getModalContentText(to).contains('Hey, I am Opened...')

// Close modal
ModalKeywords.clickConfirmButtonByText(to, 'Close')

