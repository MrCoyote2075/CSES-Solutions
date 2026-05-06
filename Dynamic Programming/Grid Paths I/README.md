# Grid Paths I

Consider an ( n x n ) grid whose squares may have traps. It is not allowed to move to a square with a trap.

Your task is to calculate the number of paths from the upper-left square to the lower-right square such that you only move right or down.

## Input

The first input line has an integer ( n ): the size of the grid.

After this, there are ( n ) lines that describe the grid. Each line has ( n ) characters: `.` denotes an empty cell, and `*` denotes a trap.

## Output

Print the number of paths modulo ( 10^9 + 7 ).

## Constraints

* ( 1 <= n <= 1000 )

## Example

### Input

```text
4
....
.*..
...*
*...
```

### Output

```text
3
```

---

### Explanation

For the given grid:

```text id="f18nq6"
....
.*..
...*
*...
```

* `.` → free cell
* `*` → blocked cell

We start at the top-left corner `(0,0)` and need to reach the bottom-right corner `(3,3)` by moving only **Right** or **Down**.

After avoiding those blocked cells, only 3 paths are possible.

Any other path will hit a blocked cell (`*`) which wouldn't continue to Reach the corner cell. Finaly, the total number of valid paths is:

```text id="0rjypg"
3
```
