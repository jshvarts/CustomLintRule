package com.example.rules

import com.android.tools.lint.checks.infrastructure.LintDetectorTest
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

internal class AndroidLogDetectorTest : LintDetectorTest() {
  override fun getDetector() = AndroidLogDetector()

  override fun getIssues() = listOf(AndroidLogDetector.ANDROID_LOG_ISSUE)

  @Nested
  @DisplayName("Given a class that has Android log reports error")
  inner class ClassWithAndroidLog {

    private val source = kotlin(
      """
            import android.util.Log
            
            class MyClass { 
              fun functionThatLogs() {
                Log.d("myTag", "testing") 
              } 
            }
            """.trimIndent()
    )

    @Test
    fun runLintCheckHasSingleError() {
      lint().files(source)
        .allowMissingSdk()
        .run()
        .expectErrorCount(1)
    }
  }

  @Nested
  @DisplayName("Given a class that has Android log reports error")
  inner class ClassWithoutAndroidLog {

    private val source = kotlin(
      """ 
            class MyClass { 
              fun functionThatPrints() {
                print("testing") 
              } 
            }
            """.trimIndent()
    )

    @Test
    fun runLintCheckIsClean() {
      lint().files(source)
        .allowMissingSdk()
        .run()
        .expectClean()
    }
  }
}