package com.kaloglu.duels.presentation.interfaces.base.mvp

import android.view.View
import com.kaloglu.duels.domain.model.base.BaseModel
import com.kaloglu.duels.utils.extensions.hideKeyboard

interface FormContract {
    interface FormView : MvpView {

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

    }

    interface FormPresenter<T : BaseModel, V : FormView> : MvpPresenter<V> {

        private fun getSubmitButton() = getView().getSubmitButton()

        fun setSubmitButtonEnabledIfPossible() = setSubmitButtonEnabled(getSubmitButton())

        fun submitForm() {
            getSubmitButton().hideKeyboard()
            if (canSubmitForm() && isFormValid()) onSubmitForm()
        }

        private fun setSubmitButtonEnabled(submitButton: View) {
            submitButton.isEnabled = canSubmitForm()
        }

        /**
         * If you need check length, pattern, etc...
         * e.g :
         * - password need minimum length
         * - email need pattern
         *
         * *Example :*
         * <pre>
         * {@code
         *     when {
         *         !isValidField() -> {
         *             showInvalidFieldError()
         *             false
         *         }
         *         !isValidOtherField() -> {
         *             showInvalidOtherFieldError()
         *             false
         *         }
         *     else -> true
         *     }
         * }
         * </pre>
         * */
        fun isFormValid(): Boolean

        /**
         * If you need minimum length or check something for enable submit button.
         * e.g :
         * - password need minimum length
         * - email need pattern
         *
         * Example :
         * <pre>
         * {@code
         *     when {
         *         getView().getFieldValue().isEmpty() -> false
         *         getView().getOtherFieldValue().isEmpty() -> false
         *         else -> true
         * }
         * </pre>
         * */
        fun canSubmitForm(): Boolean

        fun onSubmitForm()

    }
}
