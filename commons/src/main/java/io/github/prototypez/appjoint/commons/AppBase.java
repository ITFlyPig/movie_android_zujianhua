package io.github.prototypez.appjoint.commons;

import android.app.Application;
import androidx.annotation.CallSuper;

import com.wangyuelin.utils.PersistenceUtil;

public class AppBase extends Application {
  public static Application INSTANCE;

  @Override @CallSuper public void onCreate() {
    super.onCreate();
    INSTANCE = (Application) getApplicationContext();
    PersistenceUtil.initialize(this);
  }
}
