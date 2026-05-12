# [Word Combinations][1]

* Time limit: 1.00 s
* Memory limit: 512 MB

---

### Statement :

You are given a string of length `n` and a dictionary containing `k` words.

In how many ways can you create the string using the words?

---

### Input :

The first input line has a string containing `n` characters between `a-z`.

The second line has an integer `k`: the number of words in the dictionary.

Finally, there are `k` lines describing the words. Each word is unique and consists of characters `a-z`.

---

### Output :

Print the number of ways modulo `10^9 + 7`.

---

## Constraints

* `1 <= n <= 5000`
* `1 <= k <= 10^5`
* The total length of all words is at most `10^6`

---

## Example

### Input

```text id="n4x7p2"
abab
2
ab
abab
```

### Output

```text id="q8m1v5"
2
```

---

### Explanation

The string:

```text id="w3r6k9"
abab
```

can be formed in two ways:

```text id="y7t2c4"
ab + ab
abab
```

So the answer is:

```text id="u5p8n1"
2
```

([cses.fi][1])

[1]: https://cses.fi/problemset/task/1731/ "Word Combinations"
