package com.myshop;

import com.ninja_squad.dbsetup.operation.Operation;

import static com.ninja_squad.dbsetup.Operations.deleteAllFrom;
import static com.ninja_squad.dbsetup.Operations.insertInto;
import static com.ninja_squad.dbsetup.operation.CompositeOperation.sequenceOf;

public class DatabaseSetup {

    public static final Operation DELETE_ALL =
            deleteAllFrom("ALCOHOLICDRINKS", "NONALCOHOLICDRINKS", "DRINKS",  "ORDERSDETAILS", "ORDERS");

    public static final Operation INSERT_REFERENCE_DATA =
            sequenceOf(
                    insertInto("DRINKS")
                            .columns("ID", "NAME", "PURCHASEPRICE", "VOLUME", "QUANTITY", "DRINK_TYPE")
                            .values(1, "Вода минеральная хорошо", 9.99, 0.3, 570, "n")
                            .values(2, "Пиво Одесское Новое", 13.25, 0.5, 120, "a")
                            .values(3, "Красная испанка", 80, 0.75, 79, "a")
                            .values(4, "Сок богач", 22, 0.95, 156, "n")
                            .values(5, "Енерджи бум плюс", 24.15, 0.33, 72, "n")
                            .values(6, "Мартини Биссе", 205, 1, 12, "a")
                            .values(7, "Два моря", 195, 0.75, 0, "a")
                            .build(),
                    insertInto("ALCOHOLICDRINKS")
                            .columns("ID", "TYPE", "ABV")
                            .values(2, "BEER", 4)
                            .values(3, "WINE", 14)
                            .values(6, "LIQUOR", 13)
                            .values(7, "WINE", 12)
                            .build(),
                    insertInto("NONALCOHOLICDRINKS")
                            .columns("ID", "TYPE", "COMPOSITION")
                            .values(1, "MINERAL_WATER", "Вода минеральная, лечебно-столовая")
                            .values(4, "JUICE", "Сок носок бросок кусок волосок")
                            .values(5, "OTHER", "Вода, бла-бла-бла")
                            .build(),
                    insertInto("ORDERS")
                            .columns("ID", "DATE", "DAYOFWEEK", "TIME", "CHECK_VALUE", "SHIPPING_INFO")
                            .values(1, "2020-06-18", 4, "20:13:24", 440, null)
                            .values(2, "2020-06-19", 5, "11:06:14", 874.41, "22")
                            .build(),
                    insertInto("ORDERSDETAILS")
                            .columns("ID", "ORDER_ID", "DRINK_ID", "DRINK_QUANTITY", "DRINK_PRICE")
                            .values(1, 1, 3, 5, 88)
                            .values(2, 2, 3, 8, 88)
                            .values(3, 2, 5, 6, 26.57)
                            .values(4, 2, 1, 1, 10.99)
                            .build());
}
