# [Sliding Window Sum](https://cses.fi/problemset/task/3220/)

* Time limit: 1.00 s
* Memory limit: 512 MB

---

### Statement :

You are given an array of `n` integers. Your task is to calculate the sum of each window of `k` elements, from left to right.

In this problem the input data is large and it is created using a generator.

---

### Input :

The first line contains two integers `n` and `k`: the number of elements and the size of the window.

The next line contains four integers `x`, `a`, `b` and `c`: the input generator parameters.

The input is generated as follows:

* `x1 = x`
* `xi = (a * x(i-1) + b) % c` for `i = 2, 3, ..., n`

---

### Output :

Print the xor of all window sums.

---

## Constraints

* `1 <= k <= n <= 10^7`
* `0 <= x, a, b <= 10^9`
* `1 <= c <= 10^9`

---

## Example

### Input

```text
8 5
3 7 1 11
```

### Output

```text
12
```
