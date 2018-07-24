package ai

import const.RANDOM_STRATEGY

class StrategyFactory {
    companion object {
        fun createStrategy(strategyType: String) =
                when (strategyType) {
                    RANDOM_STRATEGY -> RandomStrategy()
                    else -> {
                        throw IllegalArgumentException("Only $RANDOM_STRATEGY supported at the moment")
                    }
                }
    }
}