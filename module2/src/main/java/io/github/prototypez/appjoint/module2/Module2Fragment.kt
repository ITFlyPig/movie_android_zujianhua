package io.github.prototypez.appjoint.module2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class Module2Fragment : androidx.fragment.app.Fragment() {

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?): View? {
    return inflater.inflate(R.layout.fragment_module2, container, false)
  }

  companion object {
    fun newInstance(): Module2Fragment {
      return Module2Fragment();
    }
  }
}