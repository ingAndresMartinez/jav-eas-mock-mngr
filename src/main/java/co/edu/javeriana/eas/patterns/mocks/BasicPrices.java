package co.edu.javeriana.eas.patterns.mocks;

import java.util.ArrayList;
import java.util.List;

public class BasicPrices {

    public static List<Item> ferreteria = new ArrayList<>();
    public static List<Item> cable = new ArrayList<>();

    /*
1 1 CELULARES
2 1COMPUTADORES
3 2TELEVISORES
4 2NEVERAS
5 2ESTUFAS
6 3ZAPATOS
7 3ROPA INFANTIL
8 3BOLZOS
9 3MEDIAS
10 4MESAS
11 4CAMAS
12 4SOFAS
13 4ESCRITORIOS
14 4BIBLIOTECA
15 5COMPUTADORES
16 5ELECTRODOMESTICOS
17 6ELECTRICA
18 6GAS
19 7DIRECT TV
20 7CLARO
21 7HVTV
*/

    static {
        ferreteria.add(new Item(1, 2_000_000));
        ferreteria.add(new Item(2, 3_000_000));
        ferreteria.add(new Item(3, 2_500_000));
        ferreteria.add(new Item(4, 1_700_000));
        ferreteria.add(new Item(5, 1_250_000));
        ferreteria.add(new Item(6, 105_000));
        ferreteria.add(new Item(7, 40_000));
        ferreteria.add(new Item(8, 75_000));
        ferreteria.add(new Item(9, 4_000));
        ferreteria.add(new Item(10, 340_000));
        ferreteria.add(new Item(11, 800_000));
        ferreteria.add(new Item(12, 770_000));
        ferreteria.add(new Item(14, 240_000));
        ferreteria.add(new Item(13, 1_230_000));
        ferreteria.add(new Item(15, 80_000));
        ferreteria.add(new Item(16, 60_000));
        ferreteria.add(new Item(17, 120_000));
        ferreteria.add(new Item(18, 110_000));
        ferreteria.add(new Item(19, 90_000));
        ferreteria.add(new Item(20, 90_000));
        ferreteria.add(new Item(21, 90_000));

        cable.add(new Item(1, 1_900_000));
        cable.add(new Item(2, 3_100_000));
        cable.add(new Item(3, 2_600_000));
        cable.add(new Item(4, 1_500_000));
        cable.add(new Item(5, 1_150_000));
        cable.add(new Item(6, 115_000));
        cable.add(new Item(7, 50_000));
        cable.add(new Item(8, 60_000));
        cable.add(new Item(9, 8_000));
        cable.add(new Item(10, 360_000));
        cable.add(new Item(11, 770_000));
        cable.add(new Item(12, 690_000));
        cable.add(new Item(14, 210_000));
        cable.add(new Item(13, 1_150_000));
        cable.add(new Item(15, 100_000));
        cable.add(new Item(16, 90_000));
        cable.add(new Item(17, 140_000));
        cable.add(new Item(18, 140_000));
        cable.add(new Item(19, 80_000));
        cable.add(new Item(20, 80_000));
        cable.add(new Item(21, 80_000));
    }

    static class Item {

        public Item(int itemId, double itemPrice) {
            this.itemId = itemId;
            this.itemPrice = itemPrice;
        }

        private int itemId;
        private double itemPrice;

        public int getItemId() {
            return itemId;
        }

        public void setItemId(int itemId) {
            this.itemId = itemId;
        }

        public double getItemPrice() {
            return itemPrice;
        }

        public void setItemPrice(double itemPrice) {
            this.itemPrice = itemPrice;
        }
    }

}