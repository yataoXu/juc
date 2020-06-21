package com.evan.core.base.enumpk;

/**
 * @Description
 * @ClassName Food
 * @Author Evan
 * @date 2020.06.12 01:45
 */
public interface Food {

    enum Appetizer implements Food {

        SALAD, SOUP, SPRING_ROLLS

    }

    enum Coffee implements Food {

        BLACK_COFFEE, DECAF_COFFEE, ESPERSSO, TEA;

    }

    enum Dessert implements Food {

        FRUIT, GELATO, TIRAMISU;

    }

}