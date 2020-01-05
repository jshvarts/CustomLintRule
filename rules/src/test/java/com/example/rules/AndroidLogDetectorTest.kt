package com.example.rules

import com.android.tools.lint.checks.infrastructure.LintDetectorTest
import com.example.rules.AndroidLogDetector.Companion.ANDROID_LOG_ISSUE
import org.junit.Test

private val detectAndroidLog = """
    import android.util.Log
    class UseLog {
        fun main() {
          Log.d(TAG, "testing")
        }
    }
  """.trimIndent()

class AndroidLogDetectorTest : LintDetectorTest() {

  override fun getDetector() = AndroidLogDetector()

  override fun getIssues() = listOf(ANDROID_LOG_ISSUE)

  @Test
  fun incorrectMethodUsage_shouldReportAWarning() {
    // obtain Lint analyzer
    lint()
      // give it a list of files to analyze
      .files(kotlin(detectAndroidLog))
      // run analyzer
      .run()
      // make assertions
      .expectErrorCount(1)
  }
}