package io.github.prototypez.appjoint.module1

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class Module1Activity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_module1)
  }

  companion object {
    fun start(context: Context) {
      val intent = Intent(context, Module1Activity::class.java)
      context.startActivity(intent)
    }
  }
}