package com.jatkeshave.aai.actions

import com.intellij.openapi.actionSystem.ActionUpdateThread
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.PlatformDataKeys
import com.intellij.openapi.diagnostic.Logger
import com.intellij.openapi.ui.Messages
import com.jatkeshave.aai.dialogs.AIIDialog
import com.jatkeshave.aai.ext.orElse
import org.jetbrains.android.facet.AndroidFacet
import org.jetbrains.android.resourceManagers.ModuleResourceManagers
import org.jetbrains.android.util.AndroidUtils

class ImportResAction : AnAction() {
    override fun getActionUpdateThread(): ActionUpdateThread {
        return ActionUpdateThread.BGT
    }

    override fun update(e: AnActionEvent) {
        val handleNullTask = {
            with(e.presentation) {
                isVisible = true
                isEnabled = false
            }
        }

        val project = e.project orElse { handleNullTask(); return }

        val androidUtils = AndroidUtils.getInstance()
        if (!androidUtils.isAndroidProject(project)) {
            e.presentation.isVisible = false
            return
        }

        val psiElement = e.getData(PlatformDataKeys.PSI_ELEMENT) orElse {
            handleNullTask()
            return
        }
        val virtualFile = e.getData(PlatformDataKeys.VIRTUAL_FILE) orElse {
            handleNullTask()
            return
        }
        val androidFacet = AndroidFacet.getInstance(psiElement) orElse {
            handleNullTask()
            return
        }


        val mResManager = ModuleResourceManagers.getInstance(androidFacet)
        val isResourceDir = mResManager.localResourceManager.isResourceDir(virtualFile)
        e.presentation.isEnabled = isResourceDir
    }

    override fun actionPerformed(e: AnActionEvent) {
        logger.info("ImportResAction performed")
        println("ImportResAction performed")
        val project = e.project
        Messages.showMessageDialog(
            project, "Import resources action clicked", "Debug Message", Messages.getInformationIcon()
        )
        AIIDialog(e.project).show()
    }

    companion object {
        private val logger = Logger.getInstance(ImportResAction::class.java)
    }
}