## Петар Аврамовиќ 233015

### 2. Control Flow Graph
![cfg hw](https://github.com/user-attachments/assets/db425edd-227b-48f9-819e-f970cc33f8fa)

### 3. Цикломатска комплексност
Цикломатската комплексност ја пресметувам со формулата E-N+2, каде Е е бројот на гранки, а N е бројот на јазли. Бидејќи има E=30 гранки и N=23 јазли, цикломатската комплексност изнесува 9.

### 4. Тест случаи според критериумот Every statement
Минималниот број тест случаи за да успее Every statement критериумот се 5. Од нив, 4 се користат за да се покријат сите statements кои водат до завршување на кодот со грешка, и последниот кој што стигнува до return statement-от. При тоа, во рамките на првиот for-loop може да се проверат сите statements користејќи allItems листа од 2 елементи со вредности кои ги носат по различните патеки во графот. Овие тестови ги изведувам на следниот начин: <br>
```java
// Го изведува секој јазел за да заврши со грешка за празна листа
RuntimeException err = assertThrows(RuntimeException.class, 
                      () -> SILab2.checkCart(null, "123"));
assertEquals("allItems list can't be null!", err.getMessage());
```
```java
// Ги изведува јазлите кои завршуваат со грешка за невалиден производ
List<Item> items = List.of(new Item(null, 1, 0, 0));
err = assertThrows(RuntimeException.class,
      () -> SILab2.checkCart(items, "123"));
assertEquals("Invalid item!", err.getMessage());
```
```java
// Се креира листа која ги задоволува сите statements во for циклусот, се поминуваат јазлите кои носат до грешка поради невалидна картичка
List<Item> items2 = List.of(new Item("Shirt", 11, 350, 0.25),
                    new Item("Socks", 5, 200, 0));
err = assertThrows(RuntimeException.class,
      () -> SILab2.checkCart(items2, "123"));
assertEquals("Invalid card number!", err.getMessage());
```
```java
// Се поминуваат јазлите кои водат до грешка поради невалиден карактер во картичката
err = assertThrows(RuntimeException.class,
      () -> SILab2.checkCart(items2, "111122223333@444"));
assertEquals("Invalid character in card number!", err.getMessage());
```
```java
// Се поминуваат јазлите кои водат до успешно враќање на сумата од корпата
assertEquals(3857.5, SILab2.checkCart(items2, "1111222233334444"));
```

### 5. Тест случаи според Multiple Condition критериумот
Минималниот број тест случаи за Multiple Condition критериумот за условот if (item.getPrice() > 300 || item.getDiscount() > 0 || item.getQuantity() > 10) изнесува 4. Тоа е така бидејќи мора да се извршат 4 посебни тестови кои ќе го тестираат резултатот од секој исход од својствата на еден производ, и бидејќи се користи OR, доволно се само 4 за да се поминат на следниот начин:<br>
TXX -> Доволно е едно да е точно за да влезе во if-от.<br>
FTX -> Поради првото што е False мора да го провери второто и влага во if-от.<br>
FFT -> Поради првите две што се False мора да го провери третото и влага во if-от.<br>
FFF -> Последниот критериум, каде не влага во if-от.<br>

```java
// TXX, price=350 > 300
Item item1 = new Item("Pants", 2, 350, 0);
assertEquals(670, SILab2.checkCart(List.of(item1), "1111222233334444"));

// FTX, discount=0.5 > 0
Item item2 = new Item("Shoes", 2, 200, 0.5);
assertEquals(170, SILab2.checkCart(List.of(item2), "1111222233334444"));

// FFT, quantity=12 > 10
Item item3 = new Item("Belt", 12, 200, 0);
assertEquals(2370, SILab2.checkCart(List.of(item3), "1111222233334444"));

// FFF
Item item4 = new Item("Papuchi", 2, 250, 0);
assertEquals(500, SILab2.checkCart(List.of(item4), "1111222233334444"));
```
