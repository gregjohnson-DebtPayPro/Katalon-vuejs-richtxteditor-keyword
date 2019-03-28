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

import com.kms.katalon.webui.keyword.vuejs.RichTextEditorKeywords

WebUI.openBrowser('https://tiptap.scrumpy.io/')
WebUI.maximizeWindow()

// editor instance
TestObject to = new TestObject()
to.addProperty('tag', ConditionType.EQUALS, 'div')
to.addProperty('xpath', ConditionType.EQUALS, '//div[@class="editor"]/div[contains(@class,"content")]/div[@contenteditable="true"]')

// get text
String currentText = RichTextEditorKeywords.getContentText(to)
println ('get text:' + currentText)

// set caret postion
RichTextEditorKeywords.setCaretPosition(to, 20)

String insertedText = 'Inserting new text'

// set text
WebUI.sendKeys(to, insertedText)

currentText = RichTextEditorKeywords.getContentText(to)
println ('set text:' + currentText)

// delete text
RichTextEditorKeywords.deleteRangeOfText(to, 20, insertedText.length())

currentText = RichTextEditorKeywords.getContentText(to)
println ('delete text:' + currentText)

//select text "basic example"
RichTextEditorKeywords.selectFirstText(to, "basic example")

// menubar instance
TestObject toMenuBar = new TestObject()
toMenuBar.addProperty('tag', ConditionType.EQUALS, 'div')
toMenuBar.addProperty('xpath', ConditionType.EQUALS, '//div[@class="menubar"]')

//set format for text
RichTextEditorKeywords.selectTextFormat(toMenuBar, RichTextEditorKeywords.MenuItem.Bold.toString(), true)
RichTextEditorKeywords.selectFirstText(to, "example of")
RichTextEditorKeywords.selectTextFormat(toMenuBar, RichTextEditorKeywords.MenuItem.Bold.toString(), true)