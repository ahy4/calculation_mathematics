package Test

import java.util.stream.IntStream

/**
 * Created by noko on 2015/09/27.
 */
class SplatoonCalculation {

    static def adjustRate(BigDecimal a, BigDecimal d) {
        def atk = ((0.99 * a) - (0.09 * a)*(0.09 * a)) / 100
        def dfn = ((0.99 * d) - (0.09 * d)*(0.09 * d)) / 100

        def p = atk >= dfn ? 1 + (atk - dfn) : 1 + (atk - dfn) / 1.8

        return p;
    }

    static def searchGoodAtkCount = { BigDecimal attackVal, BigDecimal d ->
        def okCount = 0;
        def dmg = 0
        for (BigDecimal i = 57; i >= 0; i--) {
//            println attackVal * adjustRate(i, d)
            if (attackVal * adjustRate(i, d) >= 100) {
                okCount = i
            }
        }
        okCount
    }

    static def searchGoodDefCount = { BigDecimal attackVal, BigDecimal a ->
        def okCount = 0;
        for (BigDecimal i = 57; i >= 0; i--) if (attackVal * adjustRate(a, i) < 100) {
            okCount = i
        }
        okCount
    }

}

