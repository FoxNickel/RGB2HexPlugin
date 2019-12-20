package action

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import ui.ColorConversionDialog


/**
 * @author huanglingyu <nickelfox@foxmail.com>
 * @date 2019/11/15.
 */
class ColorConversionAction : AnAction() {
    override fun actionPerformed(event: AnActionEvent) {
        ColorConversionDialog().show()
    }
}