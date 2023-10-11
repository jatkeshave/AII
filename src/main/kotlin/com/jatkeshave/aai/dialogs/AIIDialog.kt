package com.jatkeshave.aai.dialogs

import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.DialogWrapper
import com.jatkeshave.aai.composeables.AIIDialogMain
import javax.swing.JComponent

class AIIDialog(
    project: Project?
) : DialogWrapper(project, null, true, IdeModalityType.PROJECT, false) {

    init {
        init()
    }

    override fun createCenterPanel(): JComponent? {
//        val result = JBLayeredPane()
//        result.minimumSize = Dimension(400, 300)
//        result.preferredSize = Dimension(800, 600)
//        result.background = JBColor.CYAN
//        return result

//        return panel {
//            row("Labels:") {
//                Label("Some example label")
//            }
//        }.withBackground(JBColor.CYAN)
        return AIIDialogMain()
    }
}