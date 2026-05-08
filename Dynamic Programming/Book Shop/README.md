# [Book Shop][1]

* Time limit: 1.00 s
* Memory limit: 512 MB

--- 
### Statement :
You are in a book shop which sells ( n ) different books. You know the price and number of pages of each book.

You have decided that the total price of your purchases will be at most ( x ). What is the maximum number of pages you can buy? You can buy each book at most once.

---

### Input :

The first input line contains two integers ( n ) and ( x ): the number of books and the maximum total price.

The next line contains ( n ) integers ( h_1, h_2, \ldots, h_n ): the price of each book.

The last line contains ( n ) integers ( s_1, s_2, \ldots, s_n ): the number of pages of each book.

---

### Output :

Print one integer: the maximum number of pages.

---

## Constraints

* ( 1 <= n <= 1000 )
* ( 1 <= x <= 10^5 )
* ( 1 <= h_i, s_i <= 1000 )

---

## Example

### Input

```text id="4w6d8u"
4 10
4 8 5 3
5 12 8 1
```

### Output

```text id="3cxr4x"
13
```

### Explanation

We can buy:

* Book 1 → price = 4, pages = 5
* Book 3 → price = 5, pages = 8

Total price:

```text id="5r0kqp"
4 + 5 = 9
```

Total pages:

```text id="s8x1nq"
5 + 8 = 13
```

No other combination gives more than 13 pages within the budget of 10. ([Cses.fi][1])

[1]: https://cses.fi/problemset/task/1158/ "Book Shop"
