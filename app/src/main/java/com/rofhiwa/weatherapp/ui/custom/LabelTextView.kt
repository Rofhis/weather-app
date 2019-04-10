package com.rofhiwa.weatherapp.ui.custom

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import com.rofhiwa.weatherapp.R
import com.rofhiwa.weatherapp.databinding.LabelTextViewBinding

class LabelTextView : LinearLayout {

  private lateinit var binding: LabelTextViewBinding

  constructor(context: Context) : super(context) {
    setAttributes(null)
  }

  constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet) {
    setAttributes(attributeSet)
  }

  constructor(context: Context, attributeSet: AttributeSet, defStyleAttr: Int) : super(context, attributeSet, defStyleAttr) {
    setAttributes(attributeSet)
  }

  private fun setAttributes(attributeSet: AttributeSet?) {
    val inflater = LayoutInflater.from(context)
    binding = LabelTextViewBinding.inflate(inflater, this, true)

    if (attributeSet == null) {
      return
    }

    context.theme.obtainStyledAttributes(attributeSet, R.styleable.LabelTextView, 0, 0).apply {
      try {
        setLabel(getText(R.styleable.LabelTextView_label))
        setText(getText(R.styleable.LabelTextView_text))
        binding.label.setTextColor(getColor(R.styleable.LabelTextView_labelColor, ContextCompat.getColor(context, R.color.colorNormalGrey)))
        binding.text.setTextColor(getColor(R.styleable.LabelTextView_textColor, ContextCompat.getColor(context, R.color.colorDarkGrey)))
        binding.label.textSize = getFloat(R.styleable.LabelTextView_labelTextSize, 12f)
        binding.text.textSize = getFloat(R.styleable.LabelTextView_textSize, 22f)
        binding.container.setPadding(12, 12, 12, 6)
      } finally {
        recycle()
      }
    }
  }

  fun setLabel(text: String) {
    binding.label.text = text
    invalidateLayout()
  }

  fun setLabel(text: CharSequence?) {
    if (text != null) {
      this.setLabel(text.toString())
    }
  }

  fun setText(text: String) {
    binding.text.text = text
    invalidateLayout()
  }

  fun setText(text: CharSequence?) {
    if (text != null) {
      this.setText(text.toString())
    }
  }

  fun getLabel(): CharSequence? {
    return binding.label.text
  }

  fun getFieldValue(): CharSequence? {
    return binding.text.text
  }

  private fun invalidateLayout() {
    invalidate()
    requestLayout()
  }
}
