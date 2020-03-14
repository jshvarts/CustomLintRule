package com.example.customlintrule

import java.io.Serializable

sealed class SealedClass : Serializable {
  object MyObject : SealedClass()
  data class DataClass(val data: String) : SealedClass()
}