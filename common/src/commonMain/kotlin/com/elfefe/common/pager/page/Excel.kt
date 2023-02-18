package com.elfefe.common.pager.page

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.apache.poi.ss.usermodel.Workbook
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream


class News : PageImpl() {

    private fun editExcel(file: String) {
        val fileInpuString = FileInputStream(File(file))
        val workbook: Workbook = XSSFWorkbook(fileInpuString)

        val sheet = workbook.getSheetAt(0)

        sheet.createRow(2).run {
            createCell(2).run {
                setCellValue("OUI")
            }
        }

        val outputStream = FileOutputStream(file)
        workbook.write(outputStream)
        workbook.close()
    }

    @Composable
    override fun Show() {
        editExcel("Classeur 2.xlsx")
        Column(
            modifier =
            Modifier
                .fillMaxSize()
                .background(Color.Cyan),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(Icons.Default.AccountBox, "", Modifier.size(72.dp))
        }
    }
}