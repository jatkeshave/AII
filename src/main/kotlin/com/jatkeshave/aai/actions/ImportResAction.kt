package com.jatkeshave.aai.actions

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.diagnostic.Logger
import com.intellij.openapi.ui.Messages
import com.jatkeshave.aai.dialogs.AIIDialog

class ImportResAction : AnAction() {
    override fun update(e: AnActionEvent) {
        super.update(e)
    }

    override fun actionPerformed(e: AnActionEvent) {
        logger.info("ImportResAction performed")
        val project = e.project
        Messages.showMessageDialog(
            project,
            "Import resources action clicked",
            "Debug Message",
            Messages.getInformationIcon()
        )
        AIIDialog(e.project).show()
    }

    companion object {
        private val logger = Logger.getInstance(ImportResAction::class.java)
    }
}