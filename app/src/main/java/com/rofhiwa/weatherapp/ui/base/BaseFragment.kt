package com.rofhiwa.weatherapp.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.rofhiwa.weatherapp.ui.main.MainActivity

abstract class BaseFragment<T : ViewDataBinding, V : BaseViewModel> : Fragment() {

  private lateinit var mainActivity: MainActivity
  private lateinit var mRootView: View
  private lateinit var mViewDataBinding: T
  private lateinit var mViewModel: V

  /**
   * Override for set binding variable
   *
   * @return variable id
   */
  abstract fun getBindingVariable(): Int

  /**
   * @return layout resource id
   */
  @LayoutRes
  abstract fun getLayoutId(): Int

  /**
   * Override for set view model
   *
   * @return view model instance
   */
  abstract fun getViewModel(): V

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setHasOptionsMenu(false)
    mainActivity = (requireActivity() as MainActivity)
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    this.mViewModel = getViewModel()
    val v = getLayoutId()
    mViewDataBinding = DataBindingUtil.inflate(inflater, v, container, false)
    mRootView = mViewDataBinding.root
    return mRootView
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    mViewDataBinding.setVariable(getBindingVariable(), mViewModel)
    mViewDataBinding.executePendingBindings()
  }

  fun getViewDataBinding(): T {
    return mViewDataBinding
  }

  fun hideKeyboard() {
    (requireActivity() as MainActivity).hideKeyboard()
  }

  fun isNetworkConnected(): Boolean {
    return mainActivity.isNetworkConnected()
  }
}