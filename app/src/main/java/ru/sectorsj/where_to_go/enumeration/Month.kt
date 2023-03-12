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
    DEC(12, "DECEMBER");

    companion object {
        fun calcMonth(monthName: String): String {
            return when (monthName) {
                JAN.monthName -> JAN.name
                FEB.monthName -> FEB.name
                MAR.monthName -> MAR.name
                APR.monthName -> APR.name
                MAY.monthName -> MAY.name
                JUN.monthName -> JUN.name
                JUL.monthName -> JUL.name
                AUG.monthName -> AUG.name
                SEP.monthName -> SEP.name
                OCT.monthName -> OCT.name
                NOV.monthName -> NOV.name
                DEC.monthName -> DEC.name
                else -> throw IllegalArgumentException("Unknown month name")
            }
        }
    }
}
