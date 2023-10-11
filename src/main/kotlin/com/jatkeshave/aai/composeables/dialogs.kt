package com.jatkeshave.aai.composeables

import com.intellij.openapi.ui.DialogPanel
import com.intellij.ui.dsl.builder.panel

fun AIIDialogMain(): DialogPanel {
    var text1: String = "Default Text1"

    return panel {
        row("Row1") {
            label("Some text")
            textField()
        }
    }
}