package com.kaloglu.duels.presentation.interfaces.base.mvp

import android.view.View
import androidx.annotation.ColorRes
import androidx.annotation.StringRes

interface FormContract {
    interface FormView<in T> : ResponseLiveDataView<T> {

        var submitButtonView: View

        fun setSubmitButton(view: View, function: (View) -> Unit = {}) {
            submitButtonView = view
            setSubmitButtonOnClickListener(function)
        }

        private fun setSubmitButtonOnClickListener(function: (View) -> Unit = {}) {
            submitButtonView.setOnClickListener {
                if (submitButtonView.isEnabled) function(it)
            }
        }

        fun getSubmitButton() = submitButtonView

        fun showInfoAlert(@StringRes stringResId: Int, @ColorRes colorResId: Int)


    }

    interface FormPresenter<T, V : FormView<T>> : BasePresenter<V> {

        private fun getSubmitButton() = getView().getSubmitButton()

        fun setSubmitButtonEnabledIfPossible() = setSubmitButtonEnabled(getSubmitButton())

        fun submitForm() {
            if (canSubmitForm() && isFormValid()) onSubmitForm()
        }

        private fun setSubmitButtonEnabled(submitButton: View) {
            submitButton.isEnabled = canSubmitForm()
        }

        fun isFormValid(): Boolean

        fun canSubmitForm(): Boolean

        fun onSubmitForm()

        fun showSuccessDialog()
    }
}
