package com.rofhiwa.weatherapp.ui.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseFragment<T : ViewDataBinding, V : BaseViewModel> : Fragment() {

    private var mActivity: BaseActivity<*, *>? = null
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

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is BaseActivity<*, *>) {
            val activity = context as BaseActivity<*, *>?
            this.mActivity = activity
            activity!!.onFragmentAttached()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(false)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        this.mViewModel = getViewModel()
        val v = getLayoutId()
        mViewDataBinding = DataBindingUtil.inflate(inflater, v, container, false)
        mRootView = mViewDataBinding.root
        return mRootView
    }

    override fun onDetach() {
        mActivity = null
        super.onDetach()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewDataBinding.setVariable(getBindingVariable(), mViewModel)
        mViewDataBinding.executePendingBindings()
    }

    fun getBaseActivity(): BaseActivity<*, *>? {
        return mActivity
    }

    fun getViewDataBinding(): T {
        return mViewDataBinding
    }

    fun hideKeyboard() {
        if (mActivity != null) {
            mActivity?.hideKeyboard()
        }
    }

    fun isNetworkConnected(): Boolean {
        return mActivity != null && mActivity?.isNetworkConnected() == true
    }

    interface Callback {
        fun onFragmentAttached()
        fun onFragmentDetached(tag: String)
    }

    fun openFragment(containerId: Int, fragment: Fragment) {
        val transaction = fragmentManager?.beginTransaction()
        transaction?.replace(containerId, fragment)
        transaction?.addToBackStack(null)
        transaction?.commit()
    }
}