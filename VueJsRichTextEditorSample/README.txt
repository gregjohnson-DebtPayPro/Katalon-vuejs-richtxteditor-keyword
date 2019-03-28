This project is a sample test case of using VueJS Rich Text Editor Plugin. Please follow these steps below to using this plugin: 

1. Create/Open Katalon project.
2. Select Project > Settings > External Libraries
3. Add VueJSRichTextEditorKeywords.jar enclosed then click OK
4. On test case file, import com.kms.katalon.webui.keyword.vuejs.RichTextEditorKeywords then use this plugin as below example.
5. Hover on import com.kms.katalon.webui.keyword.vuejs.RichTextEditorKeywords, right click, then select Open Declaration
6. Select Attach Source on Class File Editor page, add VueJSRichTextEditorKeywords_sources.jar to External location to attach javadoc.

----------------------------
import com.kms.katalon.webui.keyword.vuejs.RichTextEditorKeywords

WebUI.openBrowser('https://tiptap.scrumpy.io/')

TestObject to = new TestObject()

RichTextEditorKeywords.setCaretPosition(to, 20)

String insertedText = 'Inserting new text'

WebUI.sendKeys(to, insertedText)

RichTextEditorKeywords.deleteRangeOfText(to, 20, insertedText.length())

RichTextEditorKeywords.selectFirstText(to, "example of")

TestObject toMenuBar = new TestObject()

toMenuBar.addProperty('tag', ConditionType.EQUALS, 'div')

toMenuBar.addProperty('xpath', ConditionType.EQUALS, '//div[@class="menubar"]')

RichTextEditorKeywords.selectTextFormat(toMenuBar, RichTextEditorKeywords.MenuItem.Bold.toString(), true)
----------------------------