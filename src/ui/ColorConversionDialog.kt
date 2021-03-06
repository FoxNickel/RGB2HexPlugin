package ui

import com.intellij.openapi.ui.DialogWrapper
import java.awt.GridLayout
import javax.swing.*


/**
 * @author huanglingyu <nickelfox@foxmail.com>
 * @date 2019/12/20.
 */
class ColorConversionDialog : DialogWrapper(true) {

    init {
        init()
        title = "颜色进制转换器"
        setCancelButtonText("Close")
    }

    override fun createCenterPanel(): JComponent? {
        val dialogPanel = JPanel(GridLayout(7, 5, 3, 3))
        initColorConversionView(dialogPanel, ConversionType.TYPE_NUM_TO_HEX)
        initColorConversionView(dialogPanel, ConversionType.TYPE_HEX_TO_NUM)
        initColorConversionView(dialogPanel, ConversionType.TYPE_RGB_NUM_TO_HEX)
        initColorConversionView(dialogPanel, ConversionType.TYPE_RGB_HEX_TO_NUM)
        initColorConversionView(dialogPanel, ConversionType.TYPE_PERCENT_TO_HEX)
        initColorConversionView(dialogPanel, ConversionType.TYPE_SMALL_TO_UPPER)
        initColorConversionView(dialogPanel, ConversionType.TYPE_UPPER_TO_SMALL)

        return dialogPanel
    }

    private fun initColorConversionView(dialogPanel: JPanel, type: Int) {
        val leftLabelStr: String
        val rightLabelStr: String
        when (type) {
            ConversionType.TYPE_NUM_TO_HEX -> {
                leftLabelStr = "ARGB: "
                rightLabelStr = "HEX IS: "
            }
            ConversionType.TYPE_HEX_TO_NUM -> {
                leftLabelStr = "HEX: "
                rightLabelStr = "ARGB IS: "
            }
            ConversionType.TYPE_RGB_NUM_TO_HEX -> {
                leftLabelStr = "RGB: "
                rightLabelStr = "HEX IS: "
            }
            ConversionType.TYPE_RGB_HEX_TO_NUM -> {
                leftLabelStr = "HEX: "
                rightLabelStr = "RGB IS: "
            }
            ConversionType.TYPE_PERCENT_TO_HEX -> {
                leftLabelStr = "ALPHA IN PERCENT: "
                rightLabelStr = "HEX IS: "
            }
            ConversionType.TYPE_SMALL_TO_UPPER -> {
                leftLabelStr = "SMALL STRING: "
                rightLabelStr = "UPPER IS: "
            }
            ConversionType.TYPE_UPPER_TO_SMALL -> {
                leftLabelStr = "UPPER STRING: "
                rightLabelStr = "SMALL IS: "
            }
            else -> {
                leftLabelStr = "ARGB: "
                rightLabelStr = "HEX IS: "
            }
        }
        val leftLabel = JLabel(leftLabelStr)
        val leftText = JTextField(10)
        val rightLabel = JLabel(rightLabelStr)
        val rightText = JTextField(10)

        val btCalculate = JButton("转换")
        btCalculate.addActionListener {
            rightText.text = when (type) {
                ConversionType.TYPE_NUM_TO_HEX -> {
                    converseNumToHex(leftText.text)
                }
                ConversionType.TYPE_HEX_TO_NUM -> {
                    converseHexToNum(leftText.text)
                }
                ConversionType.TYPE_RGB_NUM_TO_HEX -> {
                    converseRGBNumToHex(leftText.text)
                }
                ConversionType.TYPE_RGB_HEX_TO_NUM -> {
                    converseRGBHexToNum(leftText.text)
                }
                ConversionType.TYPE_PERCENT_TO_HEX -> {
                    conversePercentToHex(leftText.text)
                }
                ConversionType.TYPE_SMALL_TO_UPPER -> {
                    converseSmallToUpper(leftText.text)
                }
                ConversionType.TYPE_UPPER_TO_SMALL -> {
                    converseUpperToSmall(leftText.text)
                }
                else -> {
                    converseNumToHex(leftText.text)
                }
            }
        }

        dialogPanel.add(leftLabel)
        dialogPanel.add(leftText)
        dialogPanel.add(btCalculate)
        dialogPanel.add(rightLabel)
        dialogPanel.add(rightText)
    }

    private fun converseUpperToSmall(text: String?): String? {
        return text?.run {
            toLowerCase()
        } ?: ""
    }

    private fun converseSmallToUpper(text: String?): String? {
        return text?.run {
            toUpperCase()
        } ?: ""
    }

    private fun converseRGBHexToNum(num: String?): String? {
        return num?.run {
            val hexR = num.substring(1, 3)
            val hexG = num.substring(3, 5)
            val hexB = num.substring(5, 7)
            "${hexR.toInt(16)} ${hexG.toInt(16)} ${hexB.toInt(16)}"
        }
    }

    private fun converseRGBNumToHex(num: String?): String? {
        return num?.run {
            val nums = num.split(" ")
            val r = nums[0].toInt()
            val g = nums[1].toInt()
            val b = nums[2].toInt()
            "#${toUpperString(r)}${toUpperString(g)}${toUpperString(b)}"
        }
    }

    private fun converseNumToHex(num: String?): String? {
        return num?.run {
            val nums = num.split(" ")
            val a = nums[0].toInt()
            val r = nums[1].toInt()
            val g = nums[2].toInt()
            val b = nums[3].toInt()
            "#${toUpperString(a)}${toUpperString(r)}${toUpperString(g)}${toUpperString(b)}"
        }
    }

    private fun converseHexToNum(num: String?): String? {
        return num?.run {
            val hexA = num.substring(1, 3)
            val hexR = num.substring(3, 5)
            val hexG = num.substring(5, 7)
            val hexB = num.substring(7, 9)
            "${hexA.toInt(16)} ${hexR.toInt(16)} ${hexG.toInt(16)} ${hexB.toInt(16)}"
        }
    }

    private fun conversePercentToHex(num: String?): String? {
        return num?.run {
            val alphaPercent = toDouble()
            val alphaInt: Int = (alphaPercent * 255).toInt()
            toUpperString(alphaInt)
        }
    }

    private fun toUpperString(num: Int) = num.toString(16).toUpperCase()

    override fun doOKAction() {

    }
}

object ConversionType {
    const val TYPE_NUM_TO_HEX = 0
    const val TYPE_HEX_TO_NUM = 1
    const val TYPE_RGB_NUM_TO_HEX = 2
    const val TYPE_RGB_HEX_TO_NUM = 3
    const val TYPE_PERCENT_TO_HEX = 4
    const val TYPE_SMALL_TO_UPPER = 5
    const val TYPE_UPPER_TO_SMALL = 6
}