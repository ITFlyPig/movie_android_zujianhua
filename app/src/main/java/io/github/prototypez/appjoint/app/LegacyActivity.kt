package io.github.prototypez.appjoint.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class LegacyActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_legacy)
  }
}