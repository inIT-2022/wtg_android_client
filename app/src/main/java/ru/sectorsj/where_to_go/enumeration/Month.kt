package ru.sectorsj.where_to_go.enumeration

enum class Month(val id: Int, val monthName: String) {
    JAN(1, "JANUARY"),
    FEB(2, "FEBRUARY"),
    MAR(3, "MARCH"),
    APR(4, "APRIL"),
    MAY(5, "MAY"),
    JUN(6, "JUNE"),
    JUL(7, "JULY"),
    AUG(8, "AUGUST"),
    SEP(9, "SEPTEMBER"),
    OCT(10, "OCTOBER"),
    NOV(11, "NOVEMBER"),
    DEC(12, "DECEMBER")
}

fun calcMonth(monthName: String): String {
    return when (monthName) {
        Month.JAN.monthName -> Month.JAN.name
        Month.FEB.monthName -> Month.FEB.name
        Month.MAR.monthName -> Month.MAR.name
        Month.APR.monthName -> Month.APR.name
        Month.MAY.monthName -> Month.MAY.name
        Month.JUN.monthName -> Month.JUN.name
        Month.JUL.monthName -> Month.JUL.name
        Month.AUG.monthName -> Month.AUG.name
        Month.SEP.monthName -> Month.SEP.name
        Month.OCT.monthName -> Month.OCT.name
        Month.NOV.monthName -> Month.NOV.name
        Month.DEC.monthName -> Month.DEC.name
        else -> throw IllegalArgumentException("Unknown month name")
    }
}