import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.PlatformDataKeys
import com.intellij.openapi.ui.Messages


/**
 * @author huanglingyu <nickelfox@foxmail.com>
 * @date 2019/11/15.
 */
class ColorConversionAction : AnAction() {
    override fun actionPerformed(event: AnActionEvent) {
        val project = event.getData(PlatformDataKeys.PROJECT)
        val txt = Messages.showInputDialog(project, "What is alpha percent?", "Input your name", Messages.getQuestionIcon())
        val hex = conversionPercentAlphaToHex(txt)
        Messages.showMessageDialog(project, "Alpha Hex is $hex", "Information", Messages.getInformationIcon())
    }

    private fun conversionPercentAlphaToHex(txt: String?): String? {
        return txt?.run {
            val alphaPercent = toDouble()
            val alphaInt: Int = (alphaPercent * 255).toInt()
            alphaInt.toString(16)
        }
    }
}