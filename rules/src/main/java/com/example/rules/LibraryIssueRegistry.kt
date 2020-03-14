package com.example.rules

import com.android.tools.lint.client.api.IssueRegistry
import com.android.tools.lint.detector.api.CURRENT_API

class LibraryIssueRegistry : IssueRegistry() {
  override val api: Int = CURRENT_API

  override val issues = listOf(
    AndroidLogDetector.ANDROID_LOG_ISSUE,
    SerializableObjectMissingReadResolveDetector.MISSING_READ_RESOLVE_ISSUE
  )
}