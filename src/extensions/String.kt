package extensions

import java.lang.StringBuilder

infix fun StringBuilder.plus(str: String?) = this.apply {
    append(str)
}