# [Restaurant Customers][1]

* Time limit: 1.00 s
* Memory limit: 512 MB

---

### Statement :

You are given the arrival and leaving times of `n` customers in a restaurant.

What was the maximum number of customers in the restaurant at any time?

---

### Input :

The first input line has an integer `n`: the number of customers.

After this, there are `n` lines that describe the customers. Each line has two integers `a` and `b`: the arrival and leaving times of a customer.

You may assume that all arrival and leaving times are distinct.

---

### Output :

Print one integer: the maximum number of customers.

---

## Constraints

* `1 <= n <= 2 * 10^5`
* `1 <= a < b <= 10^9`

---

## Example

### Input

```text id="p4x7m2"
3
5 8
2 4
3 9
```

### Output

```text id="v8q1k5"
2
```

### Explanation

Customers present at different times:

```text id="r2m9w4"
Time 2 -> 1 customer
Time 3 -> 2 customers
Time 5 -> 2 customers
Time 8 -> 1 customer
```

The maximum number of customers present at the same time is:

```text id="t6x3n8"
2
```

([cses.fi][1])

[1]: https://cses.fi/problemset/task/1619/ "Restaurant Customers"
