package io.github.prototypez.appjoint.app

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class LegacyFragment : androidx.fragment.app.Fragment() {

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?): View? {
    return inflater.inflate(R.layout.fragment_legacy, container, false)
  }

  companion object {
    fun newInstance(): LegacyFragment {
      return LegacyFragment();
    }
  }
}