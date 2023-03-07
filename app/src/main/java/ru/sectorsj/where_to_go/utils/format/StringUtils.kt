package ru.sectorsj.where_to_go.utils.format

object StringUtils {
    fun passwordValidation(password: String): Boolean {
        val passwordCheck = "(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9@#$%]).{8,}".toRegex()
        return password.matches(passwordCheck)
    }

    fun emailValidation(email: String): Boolean {
        val emailCheck = "^[a-zA-Z0-9]{1,}" + "((\\.|\\_|-{0,1})[a-zA-Z0-9]{1,})*" + "@" + "[a-zA-Z0-9]{1,}" + "((\\.|\\_|-{0,1})[a-zA-Z0-9]{1,})*" + "\\.[a-zA-Z]{2,3}$"
        return email.matches(emailCheck.toRegex())
    }
}