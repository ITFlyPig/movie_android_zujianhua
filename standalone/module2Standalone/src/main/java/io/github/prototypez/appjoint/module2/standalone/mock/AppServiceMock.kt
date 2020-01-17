package io.github.prototypez.appjoint.module2.standalone.mock

import android.content.Context
import androidx.fragment.app.Fragment
import io.github.prototypez.appjoint.commons.T
import io.github.prototypez.service.app.AppService
import io.github.prototypez.service.app.callback.AppCallback
import io.github.prototypez.service.app.entity.AppEntity
import io.reactivex.Observable

class AppServiceMock : AppService {

  override fun callMethodSyncOfApp(): String {
    return "Mock<syncMethodResultApp>"
  }

  override fun observableOfApp(): Observable<AppEntity> {
    return Observable.just(AppEntity("Mock<rxJavaResultApp>"))
  }

  override fun callMethodAsyncOfApp(callback: AppCallback<AppEntity>) {
    Thread { callback.onResult(AppEntity("Mock<asyncMethodResultApp>")) }.start()
  }

  override fun startActivityOfApp(context: Context) {
    T.s("Mock: startActivityOfApp called")
  }

  override fun obtainFragmentOfApp(): androidx.fragment.app.Fragment? {
    T.s("Mock: obtainFragmentOfApp called")
    return androidx.fragment.app.Fragment()
  }
}
