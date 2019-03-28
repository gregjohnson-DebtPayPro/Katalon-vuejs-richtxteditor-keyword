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
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.webui.keyword.internal.WebUIAbstractKeyword as WebUIAbstractKeyword
import internal.GlobalVariable as GlobalVariable
import java.lang.String as String

import com.kms.katalon.webui.keyword.vuejs.ModalKeywords

WebUI.openBrowser('https://vuejs.org/v2/examples/modal.html')

WebUI.waitForElementPresent(findTestObject('vuejs/modal/btnShowModal'), 5)

WebUI.click(findTestObject('vuejs/modal/btnShowModal'))

// Modal container Test Object
TestObject to = findTestObject('vuejs/modal/divDefaultModalContainer')

// Verify header text
assert ModalKeywords.getModalTitleText(to).contains('custom header')

// Verify body content text
assert ModalKeywords.getModalContentText(to).contains('default body')

// Close modal
ModalKeywords.clickConfirmButtonByText(to, 'OK')
