package com.example.test.base

import android.content.Context
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleOwner
import com.example.test.R

/**
 *  액티비티에서의 완전히 공통적으로 가질수 있거나 가져야하는 기능을 관리하기 위함.
 */
abstract class BaseActivity<T : ViewDataBinding> : AppCompatActivity(), LifecycleOwner {

    /**
     * @viewDataBinding  : 데이터바인딩
     * @layoutResourceId : 레이아웃 리소스 아이디
     */
    lateinit var viewDataBinding: T
    abstract val layoutResourceId: Int

    /**
     *  @aboutBinding : 바인딩 후 기본적으로 해야할 부분을 담당
     *  @observeData  : 라이브데이터 관찰 후 컨트롤 해야하는 부분 등록하기 위함.
     */
    abstract fun aboutBinding()
    abstract fun observeData()

    override fun onDestroy() {
        super.onDestroy()

        /**
         *  액티비티 onDestroy 로 넘어갔을 때 자원을 해제 하기 위해
         *      => 임시 파일을 사용하는 액티비티에만 구현
         */
        when ( this::class.java ) {
//            GlobalScope.launch { deleteTempImages() }
            else -> {}
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding = DataBindingUtil.setContentView(this, layoutResourceId)
        aboutBinding()
        observeData()
    }

    /**
     *  툴바 세팅 공통 함수
     */
    fun setBaseToolbar(toolbar: Toolbar){
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            setDisplayShowTitleEnabled(false)
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            setHomeAsUpIndicator( ContextCompat.getDrawable(applicationContext, R.drawable.ic_back_arrow) )
//            when (this::class) {
////                MainActivity::class -> { R.drawable.ic_menu }
//                else -> { R.drawable.ic_back_arrow }
//            }.also { menuIcon ->
//                setHomeAsUpIndicator( ContextCompat.getDrawable(applicationContext, menuIcon) )
////                toolbar.navigationIcon = ContextCompat.getDrawable(applicationContext, menuIcon)
//            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> onBackPressed()
        }


        return super.onOptionsItemSelected(item)
    }

    // when hide chagne current destination
    fun hideKeyboard( view : View ) {
        val inputMethodManager: InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    // when hide chagne current destination
    fun showKeyboard( editText: EditText ) {
        editText.requestFocus()
        editText.postDelayed({
            val inputMethodManager: InputMethodManager? = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
            inputMethodManager?.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT)
        },30)
    }

}